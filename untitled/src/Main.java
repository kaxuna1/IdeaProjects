import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Created with IntelliJ IDEA.
 * User: kakha
 * Date: 10/10/13
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    private JButton button1;
    private JTextField textField1;


    public Main() {

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);    //To change body of overridden methods use File | Settings | File Templates.
            }
        });
    }
}
