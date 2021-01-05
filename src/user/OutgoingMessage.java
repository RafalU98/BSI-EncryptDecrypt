package user;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;

public class OutgoingMessage extends Thread{
    private PrintWriter printWriter;
    private Client client;
    public OutgoingMessage(Socket socket,Client client )throws IOException{
        this.printWriter = new PrintWriter(socket.getOutputStream(), true);
        this.client = client;
    }


    public void run(){
try{
String nameInitial = null;
BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
boolean flag =true;
while (flag){
    StringWriter stringWriter = new StringWriter();
    if(client.getName() == null)nameInitial = initDomainParams(stringWriter,bufferedReader);
    else if (client.getPrivateKey() == null) calcPublicKey(stringWriter,bufferedReader,nameInitial);
    else if (bufferedReader.ready()){
        calcCommonKey(stringWriter,bufferedReader,nameInitial);
        break;
    }
}
}catch (IOException e){System.out.println(e.getMessage());}

    }
    private String initDomainParams(StringWriter stringWriter, BufferedReader bufferedReader) throws IOException{
        System.out.println("[system] enter name (agreed on ) p , (agreed on) alpha");
        String[] values =bufferedReader.readLine().split(" ");
        client.setName(values[0]);
        String nameInitial = values[0].substring(0,1).toLowerCase();
        boolean initFlag = false;
        if(client.getP() == null){
            client.setP(new BigInteger(values[1]));
            client.setAlpha(new BigInteger(values[2]));
            initFlag = true;
        }
        Json.createWriter(stringWriter).writeObject(Json.createObjectBuilder()
        .add("name",client.getName())
        .add("p",client.getP().toString())
        .add("alpha",client.getAlpha().toString())
        .add("initFlag",initFlag)
        .build());
        printWriter.println(stringWriter);
        return nameInitial;
    }
    private void calcPublicKey(StringWriter stringWriter , BufferedReader bufferedReader , String initial) throws IOException{
        System.out.println("[system]: pick secret # '" + initial+"'from set {2,3,...,"+client.getP().add(new BigInteger("-2"))+"}");
        client.setPrivateKey(new BigInteger(bufferedReader.readLine()));
        BigInteger pkValue = client.getAlpha().modPow(client.getPrivateKey(),client.getP());
        System.out.println("["+client.getName()+"]: " +"p="+client.getP()+", alpha="+client.getAlpha()+ ","+initial+"="+client.getPrivateKey());
        System.out.println("["+client.getName()+"]: "+initial.toUpperCase()+"<congruent> "+ "alpha^"+initial.toLowerCase()+" mod p = "+client.getAlpha()+"^"+client.getPrivateKey()+" mod" +client.getP()+" = "+pkValue+" mod "+client.getP());
        Json.createWriter(stringWriter).writeObject(Json.createObjectBuilder()
        .add("name",client.getName())
                .add("p",client.getP())
                .add("alpha",client.getAlpha())
                .add("publicKeyValue",client.getPrivateKey())
                .build());
        printWriter.println(stringWriter);
    }
    private void calcCommonKey(StringWriter stringWriter, BufferedReader bufferedReader,String initial) throws  IOException{
        bufferedReader.read();
        BigInteger s = client.getPublickey().modPow(client.getPrivateKey(),client.getP());
        System.out.println("["+client.getName()+"]: "+"s <congruent> "+client.getOtherPartyPublicKey()+"^"+
                initial.toLowerCase()+ " mod p"+" = "+client.getPublickey()+"^"+
                client.getPrivateKey()+" mod "+client.getP()+" = "+s +" mod "+client.getP());
        Json.createWriter(stringWriter).writeObject(Json.createObjectBuilder()
        .add("name",client.getName())
        .add("ready",true)
        .build());
        if(!client.isCommonKeyFlag()) printWriter.println(stringWriter);
    }
}
