package zadaniaChat.zad3Chat;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Server extends Thread{
    private final int port;
    private boolean isEnabled = false;
    private final String mainDir;

    public Server(int port, String mainDir) {
        this.mainDir = mainDir;
        this.port=port;
        try {
            Files.createDirectories(Paths.get(mainDir));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void startListening(){

        Runnable listener = new Runnable() {
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    System.out.println("Server listens at: " + port);
                    isEnabled = true;

                    while(isEnabled){
                        Socket clientSocket = serverSocket.accept();
                        System.out.println("Connected to new client");
                        new ServerThread(clientSocket);
                    }
                } catch (IOException e) {
                    System.out.println("Error in startListening(): " + e.getMessage());
                    e.printStackTrace();
                }
            }
        };

        new Thread(listener).start();
    }

    public static void main(String[] args){

        Server server = new Server(2950,System.getProperty("user.home") + File.separator + "ServerData");
        server.startListening();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String input = null;
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.equals("x"))
                break;
        }

    }



}
