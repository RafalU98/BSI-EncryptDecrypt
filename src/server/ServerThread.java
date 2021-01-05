package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.json.JsonObject;
import javax.json.Json;


public class ServerThread extends Thread{
    private Server server;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    public ServerThread(Socket socket, Server server) throws IOException {
        this.server= server;
        this.bufferedReader= new BufferedReader((new InputStreamReader(socket.getInputStream())));
        this.printWriter = new PrintWriter(socket.getOutputStream(),true);
    }
    public void run(){
        JsonObject jsonObject = null;
        String p = null , alpha = null , pkNameInitial = null , pkValue = null;
        try {
            while ((jsonObject =Json.createReader(bufferedReader).readObject()) != null){
             if(jsonObject.containsKey("initFlag") && jsonObject.getBoolean("initFlag")){
                pkNameInitial =jsonObject.getString("name").substring(0,1).toUpperCase();
                p =jsonObject.getString("p");
                alpha = jsonObject.getString("alpha");
                pkValue = jsonObject.getString("publicKeyValue");
                System.out.println("\n[system]: p = " + p + "& alpha = "+ alpha );
             }else if (json.containsKey("publicKeyValue")){
                 pkNameInitial =jsonObject.getString("name").substring(0,1).toUpperCase();
                 p =jsonObject.getString("p");
                 alpha = jsonObject.getString("alpha");
                 pkValue = jsonObject.getString("publicKeyValue");
                 System.out.println("[system]: "+pkNameInitial + " <congruent> "+pkValue + " mod " +p);
                 System.out.println("[Passive eve] : need to find ==> "+
                         pkNameInitial.toLowerCase()+" = logBaseAlpha("+pkNameInitial+") mod p"+
                         " = logBase"+ alpha+"("+pkValue+") mod "+p);
                 System.out.println("[Passive Eve ]: & use it to calc common key ==> s <congruent> "+
                         pkNameInitial + "^"+pkNameInitial.toLowerCase()+ "mod p = " +
                         pkValue + "^"+pkNameInitial.toLowerCase()+" mod " +p);
             }
             server.forwardMessage(jsonObject.toString(),this);
            }

        }catch (Exception e ){
            server.getServerThreads().remove(this);
        }
    }
    void forwardMessage(String message){
        printWriter.println(message);
    }
}
