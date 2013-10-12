import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: kakha
 * Date: 10/12/13
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class main {
    private JPanel panel1;
    private JButton button1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("main");
        frame.setContentPane(new main().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
