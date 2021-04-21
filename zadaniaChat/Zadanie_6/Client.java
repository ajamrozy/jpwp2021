import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.File;
import java.io.IOException;
import java.net.Socket;


public class Client {
    private SSLSocket socket = null;

    private ClientThread clientThread = null;
    private String mainDir;
    private final String host;
    private final int port;

    public Client(String mainDir,int port, String host){
            this.mainDir = mainDir;
            this.port=port;
            this.host=host;
    }
    public void startTransmission(){
        try {
            System.setProperty("javax.net.ssl.trustStore", "myKEYSTORE");
            System.setProperty("javax.net.ssl.trustStorePassword", "veryGoodPass");
            // TODO ------
            socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket("localhost", port);

			// -----------

        } catch (IOException e) {
            e.printStackTrace();
        }
        clientThread = new ClientThread(socket);
    }

    public void send(Object obj){
        if(obj != null) clientThread.send(obj);
        else System.out.println("Client.send(): 'obj' is null");
    }


    public static void main(String[] args) {

        Client client = new Client(System.getProperty("user.home") + File.separator + "ClientData",1234,"localhost");
        client.startTransmission();

        Message message = new Message("Zapisz","3.png");
        client.send(message);

    }
}
