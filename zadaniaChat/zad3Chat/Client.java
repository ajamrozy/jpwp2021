package zadaniaChat.zad3Chat;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client {
    private Socket socket = null;

    private ClientThread clientThread = null;
    private String mainDir = null;
    private final String host;
    private final int port;

    public Client(String mainDir,int port, String host){
        this.mainDir = mainDir;
        this.port=port;
        this.host=host;
        try {
            Files.createDirectories(Paths.get(mainDir));
        }catch (IOException e){
            e.printStackTrace();
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

    public void send(Object obj){
        if(obj != null) clientThread.send(obj);
        else System.out.println("Client.send(): 'obj' is null");
    }

    public static void main(String[] args) {
        String dir = System.getProperty("user.home") + File.separator + "ClientData";
        Client client = new Client(dir,2950,"localhost");
        client.startTransmission();

        // wysyłanie pliku
        String fileDir = System.getProperty("user.dir");
        String fileName = fileDir + File.separator + "src" + File.separator + "image.png";
        FileContainer fileContainer = new FileContainer(fileName);
        fileContainer.setCmd("save");
        System.out.println("Sending image.png to server ...");
        client.send(fileContainer);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // pobieranie pliku
        System.out.println("Trying to download image.png from server ...");
        FileContainer fileMetadata = (FileContainer) Functions.getObject(dir + File.separator+ "image.png" + ".metadata");
        fileMetadata.setCmd("getFile");
        client.send(fileMetadata);
    }
}
