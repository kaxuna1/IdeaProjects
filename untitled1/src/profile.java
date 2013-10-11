import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: kakha
 * Date: 10/10/13
 * Time: 8:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class profile {
    public JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JProgressBar progressBar1;
    private JButton button1;

    public profile(String s1,String s2){
      label1.setText(s1);

        label2.setText(s2);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progressBar1.setValue(progressBar1.getValue()+10);
                if(progressBar1.getValue()>100) {

                }
            }

        });
    }





}
