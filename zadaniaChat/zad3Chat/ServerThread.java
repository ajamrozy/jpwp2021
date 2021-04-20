package zadaniaChat.zad3Chat;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;

public class ServerThread extends FileTransferManager{
    private String serverDir = System.getProperty("user.home") + File.separator + "ServerData";
    public ServerThread(Socket socket) {
        this.socket = socket;
        startReading();
    }

    public void takeAction(Object obj){
        switch (obj.getClass().getName()){
            case "FileContainer": takeAction((FileContainer) obj);
                break;
        }
    }

    public void takeAction(FileContainer fileContainer){
        switch (fileContainer.getCmd()) {
            case "save": {
                System.out.println("Saving file: " + fileContainer.getOriginalFileName());
                fileContainer.setDestinationDirectory(serverDir);
                fileContainer.saveFileMetadata();
                fileContainer.saveFileData();

                // check if file was saved successfully -------------
                String filePath = serverDir + File.separator + fileContainer.getFilename();
                if (Files.exists(Paths.get(filePath))
                        && Files.exists(Paths.get(filePath + ".metadata"))) {
                    fileContainer.setCmd("save:true");
                } else {
                    fileContainer.setCmd("save:false");
                }
                // --------------------------------------------------

                System.out.println("Sending metadata to client");
                send(fileContainer);
                break;
            }

            case "getFile":{
                System.out.println("Prepare to send file: " + fileContainer.getOriginalFileName());
                fileContainer.writeFileData(serverDir + File.separator + fileContainer.getOriginalFileName());
                if(fileContainer.getFileData() != null) {
                    fileContainer.setCmd("getFile:true");
                    System.out.println("Requested file was sent");
                    send(fileContainer);
                }
                else System.out.println("Can't find a file");
            }
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