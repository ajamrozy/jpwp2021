package zadaniaChat.zad2Chat;
import java.net.Socket;

public class ClientThread extends FileTransferManager{

    public ClientThread(Socket socket) {
        super.socket = socket;
        startReading();
    }

    @Override
    public void takeAction(Object obj){
        switch (obj.getClass().getName()){
            case "FileContainer": {
                takeAction((FileContainer) obj);
                break;
            }
            case "Message": {
                takeAction((Message) obj);
                break;
            }
        }
    }
    public void takeAction(FileContainer fileContainer){
        switch (fileContainer.getCmd()) {
            case "Received 1.png": {
                System.out.println("Serwer odebrał plik 1.png");
                break;
            }
            case "Not received 1.png": {
                System.out.println("Serwer nie odebrał pliku 1.png");
                break;
            }
        }
    }

    public void takeAction(Message message){
        System.out.println("------------------------------------------------");
        message.print();
    }
}



