import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ComponentAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: kakha
 * Date: 10/12/13
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class main {
    private JPanel panel1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("main");
        frame.setContentPane(new main().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TableModel tableModel=new DefaultTableModel(new Object[]{"asa","as","asa","asda"},4);
        JTable table2 =new JTable(tableModel);
        table
        frame.add(table2);
        frame.pack();
        frame.setVisible(true);
    }

    public main() {


    }
}
