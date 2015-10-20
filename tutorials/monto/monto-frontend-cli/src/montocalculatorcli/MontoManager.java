/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montocalculatorcli;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.zeromq.ZMQ;

/**
 *
 * @author charlie
 */
public class MontoManager {
    private boolean isConnected;
    private static ZMQ.Context context;
    private static ZMQ.Socket fromMonto;
    private static ZMQ.Socket toMonto;
    
    public MontoManager() {
        isConnected = false;
    }
    
    public boolean connect() {
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
    
    public boolean disconnect() {
        fromMonto.close();
        toMonto.close();
        context.term();
        isConnected = false;
        return true;
    }
    
    public final void sendMessage(String contents) {
        toMonto.send(
                ("{\"source\":\"java-frontend\", "
                    + "\"language\":\"calc\", "
                    + "\"contents\":\"" + contents + "\", "
                    + "\"selections\":\"\"}")
                .getBytes());
        toMonto.recv();
    }
    
    public String receiveProduct() {
        if(isConnected) {
            String rawMessage = new String(fromMonto.recv());
            JSONObject message = (JSONObject)JSONValue.parse(rawMessage);
            return (String)message.get("contents");
        } else {
            return "Error: Connection to Monto was closed";
        }
    }
}
