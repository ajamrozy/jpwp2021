package zadaniaChat.zad2Chat;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket = null;

    private ClientThread clientThread = null;
    private final String mainDir;
    private final String host;
    private final int port;

    public Client(String mainDir, int port, String host){
        this.mainDir = mainDir;
        this.port = port;
        this.host = host;
    }

    public void send(Object obj){
        if(obj != null) {
            clientThread.send(obj);
        }
        else {
            System.out.println("Client.send(): 'obj' is null");
        }
    }

    public void startTransmission(){
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientThread = new ClientThread(socket);
    }

    public static void main(String[] args) {

        Client client = new Client(System.getProperty("user.home") + File.separator + "ClientData",2605,"localhost");
        client.startTransmission();

        //Demo start --------------------------------------------------
        System.out.println("Wysyłam plik 1.png");
        FileContainer fileContainer = new FileContainer();
        fileContainer.setFilename("1.png");
        fileContainer.setCmd("send");
        client.send(fileContainer);

        System.out.println("Wysyłam plik 2.png");
        FileContainer fileContainer2 = new FileContainer();
        fileContainer2.setFilename("2.png");
        fileContainer2.setCmd("send");
        client.send(fileContainer2);
        // Demo end ----------------------------------------------------
        System.out.println("--------------------------------------------");

        Message message = new Message("Zapisz","wiadomosc");
        client.send(message);

        Message message2 = new Message("Czytaj","wiadomosc");
        client.send(message2);

    }
}
