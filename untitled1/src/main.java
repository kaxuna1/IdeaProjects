import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;


public class main {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel main;
    private JButton button1;
    private JTextField textField1;
    private JList list1;
    private JButton readMysqlButton;
    private JTextField textField2;
    private JButton emailButton;
    private JButton mobileButton;
    private JButton newPageButton;
    private JTextField textField3;
    public ArrayList<String> emails=new ArrayList<String>();
    public ArrayList<String> mob=new ArrayList<String>();
    ArrayList<String> s=new ArrayList<String>();
    public main() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://db4free.net/kaxajava","kaxuna1","dwrstn11");
            PreparedStatement statement=connection.prepareStatement("select * from users");
            ResultSet result=statement.executeQuery();
            ArrayList<String> ss=new ArrayList<String>();
            emails.clear();
            mob.clear();
            while (result.next()){
                         ss.add(result.getString(1)+" "+result.getString(2));
                emails.add(result.getString(3));
                mob.add(result.getString(4));
            }
            list1.setListData(ss.toArray());
        } catch (ClassNotFoundException e) {

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        list1.setVisibleRowCount(8);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent kkk) {
                if(!!textField1.getText().isEmpty()&&!!textField2.getText().isEmpty()&&!!textField3.getText().isEmpty()){
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection=DriverManager.getConnection("jdbc:mysql://db4free.net/kaxajava","kaxuna1","dwrstn11");
                    String s1=textField1.getText();
                    String s2=textField2.getText();
                    String s3=textField3.getText();
                    PreparedStatement statement=connection.prepareStatement(String.format("INSERT INTO users (name, email, mobile)VALUES ('%s','%s','%s')", s1, s2, s3));
                    statement.executeUpdate();
                } catch (ClassNotFoundException e) {

                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                textField1.setText("");
                textField3.setText("");
                textField2.setText("");
                readMysqlButton.doClick();

                }
            }
        });

        readMysqlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection=DriverManager.getConnection("jdbc:mysql://db4free.net/kaxajava","kaxuna1","dwrstn11");
                    PreparedStatement statement=connection.prepareStatement("select * from users");
                    ResultSet result=statement.executeQuery();
                    ArrayList<String> ss=new ArrayList<String>();
                    emails.clear();
                    mob.clear();

                    while (result.next()){
                        ss.add(result.getString(1)+" "+result.getString(2));
                        emails.add(result.getString(3));
                        mob.add(result.getString(4));
                    }
                    list1.setListData(ss.toArray());
                } catch (ClassNotFoundException e) {

                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(list1.getSelectedIndex()>=0){
                    JOptionPane.showMessageDialog(null,emails.get(list1.getSelectedIndex()));
                }
            }
        });
        mobileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(list1.getSelectedIndex()>=0){
                    JOptionPane.showMessageDialog(null,mob.get(list1.getSelectedIndex()));
                }
            }
        });
        newPageButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                if(list1.getSelectedIndex()>=0){
                JFrame frame2 = new JFrame("profile");
                frame2.setContentPane(new profile(emails.get(list1.getSelectedIndex()),mob.get(list1.getSelectedIndex())).panel1);
                frame2.pack();
                frame2.setVisible(true);  }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("main");

        frame.setContentPane(new main().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable( false );
        frame.setSize(450,400);
        frame.setVisible(true);
    }

}
