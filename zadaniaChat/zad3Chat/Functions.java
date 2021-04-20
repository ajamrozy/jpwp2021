package zadaniaChat.zad3Chat;
import java.io.*;


public class Functions {
    public static void save(Object object, String filepath){
        System.out.print("Saving "+ object.getClass().getName() + " to " + filepath + " ...");
        try {
            FileOutputStream file = new FileOutputStream(filepath);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(object);
            output.close();
            System.out.println("Saved!");
        } catch (Exception e) {
            System.out.println("In Functions.save() error occurred: "+ e.getMessage());
        }
    }

    public static Object getObject(String filePath){
        FileInputStream fileStream;
        Object object = null;

        try {
            fileStream = new FileInputStream(filePath);
            ObjectInputStream objStream = new ObjectInputStream(fileStream);
            object = objStream.readObject();
            objStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return object;
    }

}




