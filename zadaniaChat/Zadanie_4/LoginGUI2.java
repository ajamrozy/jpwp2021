import javax.swing.*;
import java.io.File;

public class LoginGUI2 {

    public static void main(String[] args){
        String mainDir = System.getProperty("user.home");
        String defaultDir = "ClientDataZad4-1";

        String dir = defaultDir;
        String dataDir = mainDir + File.separator + dir;

        Client client = new Client(dataDir,"localhost",2509);
        JFrame frame = new JFrame(dataDir);
        frame.setContentPane(new LoginGUI(client).getLoginMain());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
