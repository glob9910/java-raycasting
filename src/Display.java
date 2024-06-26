import javax.swing.*;
import java.awt.*;

public class Display extends Canvas {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "3D graphics test";

    public Display() {
        JFrame frame = new JFrame();
        frame.add(this);
        //frame.pack();
        frame.setTitle(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        //frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        System.out.println("Running...");
    }
}
