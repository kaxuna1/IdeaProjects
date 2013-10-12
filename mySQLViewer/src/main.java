import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.io.File;


/**
 * Created with IntelliJ IDEA.
 * User: kakha
 * Date: 10/12/13
 * Time: 12:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class main {
    private void createUIComponents() {

    }


    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    protected JList<Object> list1;
    private JButton openTableButton;
    private JTable table1;
    private JButton button2;
    private JScrollPane scr;
    static Settings settings;
    private static DefaultTableModel tableModel;




    public main() {

        settings = new Settings("ka","ka","kax");
        try{
            FileInputStream fileInputStream=new FileInputStream("settings.st");//ვქმნით ფაილის წასაკითხ კანალს
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);//ვქმნით ობიექტის შემომტან კანალს
            Object one=objectInputStream.readObject();//ვკითხულობთ ობიექტს
            settings=(Settings)one;//settings ცვლადს ვანიჭებთ წაკითხულ ობიექტს ისე, თან ვუთითებთ რომ Settings ტიპად მიენიჭოს
            textField1.setText(settings.getHost());
            textField2.setText(settings.getUsername());
            textField3.setText(settings.getPassword());

        }catch (Exception s){

        }
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://"+settings.getHost(),settings.getUsername(),settings.getPassword());
            DatabaseMetaData metaData=connection.getMetaData();
            ResultSet resultSet=metaData.getTables(null,null,"%",null);
            ArrayList<String> tables=new ArrayList<String>();
            while (resultSet.next()){
                           tables.add(resultSet.getString(3));
            }
            list1.setListData(tables.toArray());

        }catch (Exception ss){

        }




        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textField1.getText().isEmpty()&&!textField2.getText().isEmpty()){

                   setS(textField1.getText(),textField2.getText(),textField3.getText());
                try{
                    FileOutputStream fileOutputStream=new FileOutputStream("settings.st");
                    ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(settings);
                    objectOutputStream.close();

                }catch (IOException l){

                }
            }else{
                    if(textField1.getText().isEmpty()){
                        textField1.setText("empty");
                    }
                    if(textField2.getText().isEmpty()){
                        textField2.setText("empty");
                    }




                }

            }
        });
        openTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String tableName=list1.getSelectedValue().toString();
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection=DriverManager.getConnection("jdbc:mysql://"+settings.getHost(),settings.getUsername(),settings.getPassword());
                    PreparedStatement preparedStatement=connection.prepareStatement(String.format("select * from %s", tableName));
                    final ResultSet resultSet=preparedStatement.executeQuery();
                    final ResultSetMetaData metadata = resultSet.getMetaData();
                    final int columnCount = metadata.getColumnCount();

                    tableModel = new DefaultTableModel(new Object[]{},0);

                    JFrame frameTable=new JFrame("table "+ tableName);

                    for(int i=1;i<=columnCount;i++){
                        String name=metadata.getColumnName(i);

                        tableModel.addColumn(name);

                    }
                    while (resultSet.next()){
                        String[] ss=new String[columnCount];
                        for (int i=1;i<=columnCount;i++){
                            ss[i-1]=resultSet.getString(i);
                        }
                        tableModel.addRow(ss);
                    }
                    JTable table = new JTable(tableModel);

                    frameTable.setSize(400,400);
                    JButton btn1=new JButton("export as text");
                    JPanel panelT=new JPanel();
                    panelT.add(new JScrollPane(table));
                    JPanel panelT2 =new JPanel();
                    panelT2.add(btn1);
                    JButton btn2=new JButton("click");
                    panelT2.add(btn2);
                    panelT.add(panelT2);


                    panelT.setLayout(new BoxLayout(panelT, BoxLayout.Y_AXIS));
                    frameTable.getContentPane().add(panelT);


                    frameTable.setVisible(true);
                    btn1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            String export="";
                            try {
                                for(int i=0;i<metadata.getColumnCount();i++){


                                }
                            } catch (SQLException e1) {
                                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                        }
                    });



                }catch (Exception ee){

                }




            }
        });


    }
    public void setS(String s1,String s2,String s3){
        settings.setHost(s1);
        settings.setUsername(s2);
        settings.setPassword(s3);

    }


    public static void main(String[] args) throws InterruptedException, InvocationTargetException {


        JFrame frame = new JFrame("main");
        frame.setContentPane(new main().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);










    }
}
