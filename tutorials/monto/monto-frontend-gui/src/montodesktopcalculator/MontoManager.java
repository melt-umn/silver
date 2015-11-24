/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montodesktopcalculator;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.zeromq.ZMQ;

/**
 *
 * @author charlie
 */
public class MontoManager {
    private static boolean isConnected = false;
	private static ZMQ.Context context;
	private static ZMQ.Socket fromMonto;
	private static ZMQ.Socket toMonto;
        private Thread listenerThread;
        private MontoListener listener;
        
        public MontoManager(MontoListener _listener) {
            listenerThread = null;
            listener = _listener;
        }
        
        public final void start() {
            connect();
            listenForProducts();
        }
        
        public final boolean connect() {
            //Connect to monto
            String inConnection = "tcp://127.0.0.1:5003";
            String outConnection = "tcp://127.0.0.1:5000";
            try {
                    context = ZMQ.context(1);
            } catch(Exception e) {
                    e.printStackTrace();
                    return false;
            }
            fromMonto = context.socket(ZMQ.SUB);
            toMonto = context.socket(ZMQ.REQ);
            fromMonto.connect(inConnection);
            fromMonto.subscribe(new byte[0]);
            toMonto.connect(outConnection);
            isConnected = true;
            return true;
	}
        
        public final boolean disconnect() {
            fromMonto.close();
            toMonto.close();
            context.term();
            isConnected = false;
            return true;
	}
        
        public final void sendMessage(String contents) {
            System.out.println("Sending " + contents);
            JSONObject obj = new JSONObject();
            obj.put("source", "java-frontend");
            obj.put("language", "calc");
            obj.put("contents", contents);
            obj.put("selections", "");
            toMonto.send(obj.toJSONString().getBytes());
            toMonto.recv();
        }
        
        public final void listenForProducts() {
            if(null != listenerThread) {
                return;
            }
            listenerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(isConnected) {
                        System.out.println("Waiting for messages...");
                        if(isConnected) { //Check to make sure we're connected
                           String rawMessage = new String(fromMonto.recv());
                           System.out.println("Got message" + rawMessage);
                           JSONObject message = (JSONObject)JSONValue.parse(rawMessage);
                           listener.onProductRecieved(message.get("contents").toString());
                        } else {
                            System.out.println("Not connected yet.");
                        }
                        try {
                            Thread.sleep(10);
                        } catch(Exception e) {} //Intentionally left blank
                    }
                }
            });
            listenerThread.start();
        }
}
