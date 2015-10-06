package monto;

import org.json.simple.*;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import common.*;

/*****
 * This class contains all the methods for sending and receiving messages to a Monto broker as a server.
 * It is important that these methods be so Silver is able to call them through the back door.
 * @author Charles Hofer
 * Dependencies: This class must be compiled against the jeromq, json-simple, and core Silver libraries.
 * 				 This was lass tested with jeromq v0.3.4, json-simple v1.1.1.
 *****/

public class MontoConnection {
	private boolean isConnected = false;
	private NodeFactory<ConsCell> eval;
	private String inConnection;
	private String outConnection;
	private Context context;
	private Socket fromMonto;
	private Socket toMonto;
	
	/**
	 * Creates a new connection
	 */
	public MontoConnection(NodeFactory<ConsCell> _eval, String _inConnection, String _outConnection) {
		eval = _eval;
		inConnection = _inConnection;
		outConnection = _outConnection;
	}

	/**
	 * Runs the Monto server
	 */
	public int run() {
		if(connect(inConnection, outConnection)) {
			while(isConnected) {
				ConsCell message = nextMessage();
				ConsCell result = eval.invoke(new Object[] { message }, new Object[0]);
				sendProduct(result);
			}
		}
		return 1;
	}
	
	/*****
	 * Connects to the Monto broker at the address specified by _inConnection and _outConnection
	 * Note: With the current implementation of MontoConnection in Silver, there is no way to 
	 * 		 call this function directly from Silver. It is called on the first run of 
	 * 		 nextMessage().
	 * @param _inConnection
	 * @param _outConnection
	 * @return
	 *****/
	private final boolean connect(String _inConnection, String _outConnection) {
		//Connect to monto
		inConnection = _inConnection;
		outConnection = _outConnection;
		try {
			context = ZMQ.context(1);
		} catch(Exception e) {
			e.printStackTrace();
		}
		fromMonto = context.socket(ZMQ.SUB);
		toMonto = context.socket(ZMQ.REQ);
		fromMonto.connect(inConnection);
		fromMonto.subscribe(new byte[0]);
		toMonto.connect(outConnection);
		isConnected = true;
		return true;
	}
	
	/*****
	 * Terminates the connection. CURRENTLY NOT USED!
	 * @return
	 *****/
	private final boolean disconnect() {
		fromMonto.close();
		toMonto.close();
		context.term();
		isConnected = false;
		return true;
	}
	
	/*****
	 * Waits for the next Monto published version (message) from the broker. This function will
	 * block until the next message is received.
	 * @param inUrl
	 * @param outUrl
	 * @return
	 *****/
	private ConsCell nextMessage() {
		String rawMessage = new String(fromMonto.recv());
		//Decode the raw message and print it out
		JSONObject message = (JSONObject)JSONValue.parse(rawMessage);
		//Put the message into a Silver list
		ConsCell out = ConsCell.nil;
		out = new ConsCell(new StringCatter((String)message.get("selections")), out);
		out = new ConsCell(new StringCatter((String)message.get("contents")), out);
		out = new ConsCell(new StringCatter((String)message.get("language")), out);
		out = new ConsCell(new StringCatter((String)message.get("source")), out);
		return out;
	}
	
	/*****
	 * Sends a new product to the Monto broker for distrubution to sinks.
	 * @param responseCons
	 * @return
	 ******/
	public void sendProduct(ConsCell responseCons) {
		String source = responseCons.head().toString();
		String product = responseCons.tail().head().toString();
		String language = responseCons.tail().tail().head().toString();
		String contents = responseCons.tail().tail().tail().head().toString();
		MontoResponse response = new MontoResponse(source, product, language, contents);
		//Send the response
		toMonto.send(response.toString().getBytes());
		//Wait for acknowledgment
		toMonto.recv();
	}
}
