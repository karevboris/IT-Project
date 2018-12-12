package UIElements;

import javax.swing.*;
import java.awt.*;

public class EditPanel extends JPanel {
    public EditPanel(LayoutManager layout) {
        super(layout);
    }

    public void create(JTextField name, JTextField mi, JTextField oi, JTextField pi, JTextField count) {
        add(new JLabel("Name of project component:"));
        add(name);

        add(new JLabel("Index Mi:"));
        add(mi);

        add(new JLabel("Index Oi:"));
        add(oi);

        add(new JLabel("Index Pi:"));
        add(pi);

        add(new JLabel("Count:"));
        add(count);
    }
}
