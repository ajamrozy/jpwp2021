package zadaniaChat.zad2Chat;
import java.io.*;
import java.net.*;

public class Server extends Thread{
    private int port;
    private boolean isEnabled = false;
    private String mainDir;

    public Server(int port, String mainDir) {
        this.mainDir = mainDir;
        this.port=port;
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

    public static void main(String args[]){

        Server server = new Server(2605,System.getProperty("user.home") + File.separator + "ServerData");
        server.startListening();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String text = null;
            try {
                text = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (text.equals("x"))
                break;
        }

    }



}
