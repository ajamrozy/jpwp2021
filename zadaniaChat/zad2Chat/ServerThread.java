package zadaniaChat.zad2Chat;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;

public class ServerThread extends FileTransferManager{
    public ServerThread(Socket socket) {
        this.socket = socket;
        startReading();
    }

    public void takeAction(Object obj){
        switch (obj.getClass().getName()){
            case "FileContainer": takeAction((FileContainer) obj);
                break;
            // ...
            case "Message": takeAction((Message) obj);
                break;
        }
    }

    public void takeAction(FileContainer fileContainer){
        switch (fileContainer.getCmd()) {
            case "send": {
                System.out.println("Serwer odebral FileContainer");

                if(fileContainer.getFilename().equals("1.png")) {
                    System.out.println("Serwer odebrał 1.png");
                    fileContainer.setCmd("Received 1.png");
                }
                else{
                    System.out.println("Serwer nie odebrał 1.png");
                    fileContainer.setCmd("Not received 1.png");
                }
                send(fileContainer);
            }
        }
    }

    public void takeAction(Message message){
        switch (message.getCmd()){
            case "Zapisz":{
                Functions.save(message,message.getFilename());
                System.out.println("ServerThread: Zapisano");
				System.out.println("--------------------------------------------");
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