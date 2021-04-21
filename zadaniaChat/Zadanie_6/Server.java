import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.KeyStore;

public class Server extends Thread{
    private final int port;
    private boolean isEnabled = false;
    private final String mainDir;

    public Server(int port, String mainDir) {
        this.mainDir = mainDir;
        this.port=port;
    }

    public void startListening(){

        Runnable listener = () -> {
            try {
                System.setProperty("javax.net.ssl.keyStore", "myKEYSTORE");
                System.setProperty("javax.net.ssl.keyStorePassword", "veryGoodPass");
                System.setProperty("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol");

                //TODO ----------
                ServerSocket serverSocket = SSLServerSocketFactory.getDefault().createServerSocket(port);

                // --------------
                System.out.println("Server listens at: " + port);
                isEnabled = true;

                while(isEnabled){
                    SSLSocket clientSocket = (SSLSocket) serverSocket.accept();
                    System.out.println("Connected to new client");
                    new ServerThread(clientSocket);
                }
            } catch (IOException e) {
                System.out.println("Error in startListening(): " + e.getMessage());
                e.printStackTrace();
            }
        };

        new Thread(listener).start();
    }

    public static void main(String[] args){
        int port = 1234;
        Server server = new Server(port,System.getProperty("user.home") + File.separator + "ServerData");
        server.startListening();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String p = null;
            try {
                p = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (p.equals("x"))
                break;
        }
    }
}
