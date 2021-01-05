package user;


import java.math.BigInteger;
import java.net.Socket;


public class Client {
private  boolean commonKeyFlag = false;
private BigInteger p = null;
private BigInteger alpha = null;
private BigInteger privateKey = null;
private String name = null;
private String publickeyName = null;
private BigInteger publickeyValue = null;
public static void main(String[] args) throws Exception
{
    Client client = new Client();
    Socket socket = new Socket("localhost",4444);
    new IncomingMessage(socket,client).start();
    new OutgoingMessage(socket,client).start();
}
    public BigInteger getP() { return p; }
    public void setP(BigInteger p) { this.p = p; }

    public BigInteger getAlpha() { return alpha; }
    public void setAlpha(BigInteger alpha) { this.alpha = alpha; }

    public BigInteger getPrivateKey() { return privateKey; }
    public void setPrivateKey(BigInteger privateKey) { this.privateKey = privateKey; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigInteger getPublickey(){return publickeyValue;}
    public void setPublickeyValue(BigInteger publickeyValue){this.publickeyValue = publickeyValue;}

    public String getOtherPartyPublicKey(){return publickeyName;}
    public void setPublickeyName(String publickeyName){this.publickeyName=publickeyName;}

    public boolean isCommonKeyFlag(){return commonKeyFlag;}
    public  void setCommonKeyFlag(boolean commonKeyFlag){this.commonKeyFlag = commonKeyFlag;}
}
