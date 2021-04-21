import javax.net.ssl.SSLSocket;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;

public class ServerThread extends FileTransferManager{
    public ServerThread(SSLSocket socket) {
        this.socket = socket;
        startReading();
    }

    public void takeAction(Object obj){
        switch (obj.getClass().getName()){
            case "Message": takeAction((Message) obj);
                break;
        }
    }


    public void takeAction(Message message){
        switch (message.getCmd()){
            case "Zapisz":{
                Functions.save(message,message.getFilename());
                System.out.println("ServerThread: Zapisano");
                send(message);
            }
            break;
        }
    }


    @Override
    public void stop(){
        try {
            isRunning = false;
            output.close();
            socket.close();
            System.out.println("Socket is closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}