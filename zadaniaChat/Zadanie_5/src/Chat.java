package zadaniaChat.Zadanie_5.src;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Chat implements Serializable {
    protected String name;
    protected String bio;
    protected String avatarSrc;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Chat(String name, String bio, String avatarSrc){
        this.name = name;
        this.bio = bio;
        this.avatarSrc=avatarSrc;
    }
    public String getAvatarSrc() {
        return avatarSrc;
    }
    public void setAvatarSrc(String avatarSrc) {
        this.avatarSrc = avatarSrc;
    }
}
