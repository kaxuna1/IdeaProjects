import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: kakha
 * Date: 10/12/13
 * Time: 3:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main extends JFrame {
    JButton btn1;
    JPanel panel;
    DefaultTableModel model;
    public static void main(String[] args) {
        Main k=new Main();


    }
    Main(){

        panel = new JPanel();

        model = new DefaultTableModel(new Object[]{"kaxa","kaxa"},2);
        String[] ss=new String[2];
        ss[0]="kaxa";
        ss[1]="gelashvili";
        model.addRow(ss);

        btn1 = new JButton("kaxa");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                model.addRow(new String[]{"kaxa","kaxa"});
                model.removeRow(1);
                revalidate();
                repaint();
            }
        });
        JTable table=new JTable(model);
        panel.add(table);
        panel.add(btn1);
        add(panel);
        setVisible(true);

    }
}
