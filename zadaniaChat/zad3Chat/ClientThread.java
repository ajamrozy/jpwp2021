package zadaniaChat.zad3Chat;
import java.io.File;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClientThread extends FileTransferManager{
    private String clientDir = System.getProperty("user.home") + File.separator + "ClientData";
    private String serverDir = System.getProperty("user.home") + File.separator + "ServerData";
    public ClientThread(Socket socket) {
        this.socket = socket;
        startReading();
    }

    @Override
    public void takeAction(Object obj){
        switch (obj.getClass().getName()){
            case "FileContainer": takeAction((FileContainer) obj);
                break;
        }
    }
    public void takeAction(FileContainer fileContainer){
        switch (fileContainer.getCmd()) {
            case "getFile:true":{
                fileContainer.setFilename("downloaded-" + fileContainer.getFilename());
                fileContainer.saveFileData();
                // -------------------------

                stop();
                int counter = 0;
                String filePath = serverDir + File.separator + "image.png";
                if(Files.exists(Paths.get(filePath))
                        && Files.exists(Paths.get(filePath + ".metadata"))) {
                    counter++;
                }
                String filePath2 = clientDir + File.separator + "downloaded-image.png";
                if(Files.exists(Paths.get(filePath2))
                        && Files.exists(Paths.get(clientDir + File.separator + "image.png.metadata"))) {
                    counter++;
                }
                if (counter == 2) System.out.println("Congrats ! Seems that everything was done properly");
                break;
            }

            case "save:false": {
                System.out.println("! ERROR ! : File and metadata weren't successfully saved on server");
                break;
            }

            case "save:true": {
                System.out.println("File and metadata were successfully saved on server");
                fileContainer.setDestinationDirectory(clientDir);
                fileContainer.saveFileMetadata();
                System.out.println("Metadata was saved to : " + fileContainer.getDestinationDirectory() +
                        File.separator + fileContainer.getFilename() + ".metadata");
                break;
            }
        }
    }

}







