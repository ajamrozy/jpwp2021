package zadaniaChat.zad1Chat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Demo {
    public static void main(String[] args){
        Message message = new Message("zapisz","wiadomosc");
        String dir = System.getProperty("user.home") + File.separator + "Demo";
        String filePath = dir + File.separator + "savedMessage";
        try {
            Files.createDirectories(Paths.get(dir));
            System.out.printf("Serialized data is saved in /tmp/employee.ser");
        }catch (IOException e){
            e.getMessage();
        }

        Functions.save(message,filePath);
        Message message1 = (Message) Functions.getObject(filePath);
        if(message1 != null) message1.print();
    }
}
