package UIElements;

import javax.swing.*;
import java.awt.*;

public class TextDialog extends JDialog {
    public void showDialog (String msg, int width, int height, boolean visible, int x, int y){
        setSize(width, height);
        setLocation(x, y);
        add(new JLabel(msg));
        setModal(true);
        setVisible(visible);
    }
}
