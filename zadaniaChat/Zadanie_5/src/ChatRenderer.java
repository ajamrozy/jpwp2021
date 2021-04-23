package zadaniaChat.Zadanie_5.src;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ChatRenderer extends JLabel implements ListCellRenderer<Chat>{

    public ChatRenderer(){
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList<? extends Chat> list, Chat chat, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        String text;
        String avatarSrc = null;
        if(chat != null){
            avatarSrc = chat.getAvatarSrc();
            text = chat.getName();
        }
        else {
            text = "Error 404: Chat not found";
        }
        if (avatarSrc == null) avatarSrc = "src" + File.separator + "Icons" + File.separator + "eagle.jpg";

        ImageIcon imageIcon = new ImageIcon(new ImageIcon(avatarSrc).getImage());
        // src https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
        setIcon(imageIcon);
        setText(text);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }

}
