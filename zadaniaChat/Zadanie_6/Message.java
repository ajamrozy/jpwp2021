import java.io.Serializable;

public class Message implements Serializable {
    private String cmd;
    private String filename;

    public Message(String cmd, String filename) {
        this.cmd = cmd;
        this.filename=filename;
    }
    // ---------------- getters & setters -----------------

    public String getCmd() {
        return cmd;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public void print(){
        System.out.println("Cmd: "+getCmd());
        System.out.println("Filename: "+getFilename());
    }

}
