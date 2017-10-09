package monto;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import common.ConsCell;
import common.IOToken;
import common.NodeFactory;
import common.StringCatter;
import core.NIOVal;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import silver.support.monto.NService;

/**
 * The HTTP server for a Monto3 service.
 */
public class Server {
	private static final Gson gson = new Gson();

	private HttpServer server;
	private NService service;

	public Server(NService service, int port) throws IOException {
		this.server = HttpServer.create(new InetSocketAddress(port), 0);
		this.server.createContext("/monto/version", new NegotiationHandler());
		this.server.createContext("/monto/service", new RequestHandler());
		this.service = service;
	}

	public static void run(NService service, int port) {
		try {
			new Server(service, port).start();
		} catch(IOException ioex) {
			throw new RuntimeException(ioex);
		}
	}

	public void start() {
		this.server.start();
	}

	private class NegotiationHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			InputStreamReader isr = new InputStreamReader(t.getRequestBody());
			ServiceBrokerNegotiation sbn = gson.fromJson(isr, ServiceBrokerNegotiation.class);
			Pair<String, Boolean> p = FFI.doNegotiation(service, sbn);
			int responseCode = 200;
			if(!p.second) responseCode = 409;
			String response = p.first;
			t.sendResponseHeaders(responseCode, response.length());
			OutputStream out = t.getResponseBody();
			out.write(response.getBytes());
			out.close();
			t.close();
		}
	}

	private class RequestHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			InputStreamReader isr = new InputStreamReader(t.getRequestBody());
			BrokerRequest br = gson.fromJson(isr, BrokerRequest.class);
			Pair<String, Integer> p = FFI.onRequest(service, br.request, br.products);
			t.sendResponseHeaders(p.second, p.first.length());
			OutputStream out = t.getResponseBody();
			out.write(p.first.getBytes());
			out.close();
			t.close();
		}
	}
}
