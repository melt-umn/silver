package monto2;

import com.google.gson.Gson;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

public class RegisterServiceResponse {
	public String response;
	public short bind_on_port;

	public Socket connect(ZContext ctx, String brokerIP) throws RegistrationFailedException {
		if(!this.response.equals("ok"))
			throw new RegistrationFailedException(this);
		String addr = String.format("tcp://%s:%d", brokerIP, this.bind_on_port);
		Socket socket = ctx.createSocket(ZMQ.PAIR);
		socket.connect(addr);
		return socket;
	}

	public String toString() {
		return new Gson().toJson(this);
	}
}
