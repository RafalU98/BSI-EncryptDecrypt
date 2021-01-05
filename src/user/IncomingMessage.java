package user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.Socket;
import javax.json.JsonObject;
import javax.json.Json;

public class IncomingMessage extends Thread {
    private BufferedReader reader;
    private Client client;
    public IncomingMessage(Socket socket, Client client) throws IOException{
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.client = client;
    }
    public void run(){
    while(true){
        JsonObject jsonObject = Json.createReader(reader).readObject();
        if(jsonObject.containsKey("p") && client.getP() == null){
            client.setP(new BigInteger(jsonObject.getString(("p"))));
            client.setAlpha(new BigInteger(jsonObject.getString(("alpha"))));
            System.out.println("[System] enter name ");
        }else if(jsonObject.containsKey("publicKeyValue")){
            if(client.getPrivateKey() != null)
                System.out.println("[system] press enter to generate common key");
            client.setPublickeyValue(new BigInteger(jsonObject.getString(("publicKeyValue"))));
            client.setPublickeyName(jsonObject.getString("name").substring(0, 1).toUpperCase());
        }else if(jsonObject.containsKey("ready") && !client.isCommonKeyFlag()){
            System.out.println("[system] press enter to generate common key");
            client.setCommonKeyFlag(true);
        }
    }
    }
}
