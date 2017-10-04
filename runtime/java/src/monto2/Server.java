package monto;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import common.ConsCell;
import common.IOToken;
import common.NodeFactory;
import common.StringCatter;
import core.NIOVal;
import java.util.List;
import lib.monto.NConfig;
import lib.monto.NProduct;
import lib.monto.NRequest;
import org.zeromq.ZContext;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZMQ;

public class Server {
	private Config config;
	private Gson gson;
	private NodeFactory<NIOVal> callback;
	private Socket registerSock;
	private Socket sock;
	private ZContext ctx;

	public static int run(NConfig config, NodeFactory<NIOVal> callback) {
		while(true) {
			Server server = new Server(FFI.NConfigToConfig(config), callback);
			try {
				server.register();
				server.loop();
			} finally {
				server.deregister();
			}
		}
	}

	public Server(Config config, NodeFactory<NIOVal> callback) {
		this.callback = callback;
		this.config = config;
		this.gson = new Gson();
		this.ctx = new ZContext(1);

		System.out.print("Using config: ");
		System.out.println(this.gson.toJson(config));
	}

	public void register() throws RegistrationFailedException {
		if(this.sock != null)
			return;

		RegisterServiceRequest rsReq = new RegisterServiceRequest();
		rsReq.service_id = this.config.getServiceId();
		rsReq.label = this.config.getServiceLabel();
		rsReq.description = this.config.getServiceDescription();
		rsReq.products = this.config.getProducts();
		rsReq.dependencies = this.config.getDependencies();

		if(this.registerSock == null) {
			this.registerSock = this.ctx.createSocket(ZMQ.REQ);
			this.registerSock.connect(String.format("tcp://%s:5002", this.config.getBrokerAddr()));
		}
		this.registerSock.send(this.gson.toJson(rsReq));

		String registerResponse = new String(this.registerSock.recv());
		RegisterServiceResponse rsRes = this.gson.fromJson(registerResponse, RegisterServiceResponse.class);
		this.sock = rsRes.connect(this.ctx, this.config.getBrokerAddr());
	}
	public void loop() {
		while(true) {
			String msgStr = new String(this.sock.recv());
			Message msg = this.gson.fromJson(msgStr, Message.class);
			this.handle(msg);
		}
	}
	public void deregister() {
		this.sock.close();
		this.registerSock.send(this.gson.toJson(new DeregisterService(this.config.getServiceId())));
		this.registerSock.close();
	}

	public void handle(Message msg) {
		System.out.print("Got message: " + this.gson.toJson(msg));

		String tag = msg.getTag();
		switch(tag) {
		case "request":
			this.handleRequest(this.gson.fromJson(msg.getContents(), Request.class));
			break;
		default:
			System.err.println("Unknown request tag: \"" + tag + "\"");
			break;
		}
	}
	public void handleRequest(Request req) {
		for(String message : FFI.runCallback(this.callback, req)) {
			System.out.println("Sending message: " + message);
			this.sock.send(message);
		}
	}
}
