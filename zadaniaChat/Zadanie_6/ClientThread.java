import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends FileTransferManager{

    public ClientThread(SSLSocket socket) {
        this.socket = socket;
        startReading();
    }
    public ClientThread(){}

    @Override
    public void takeAction(Object obj){
        switch (obj.getClass().getName()){
            case "Message": takeAction((Message) obj);
                break;
        }
    }

    public void takeAction(Message message){
        message.print();
        System.out.println("SUCCESS");
    }
}



