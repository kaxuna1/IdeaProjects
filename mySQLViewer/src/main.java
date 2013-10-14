import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.io.Serializable;



public class main {
    private DefaultTableModel tableModel1;
    private JTable table;
    private JPanel panelT;
    private static JFrame frame;
    private void createUIComponents() {

    }


    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JList<Object> list1;
    private JButton openTableButton;
    private JTable table1;
    private JButton createTableButton;
    private JComboBox comboBox1;
    private JButton refreshListButton;
    private JButton delateTableButton;
    private JScrollPane scr;
    private static Settings settings;
    private static DefaultTableModel tableModel;
    private JFrame createTableFrame;
    private JScrollPane createTableScrollPane;
    private JComboBox<String> ctCombo;
    private JTextField newTableName;




    public main() {

        settings = new Settings("ka","ka","kax");

        try{

            final FileInputStream fileInputStream=new FileInputStream("settings.st");//ვქმნით ფაილის წასაკითხ კანალს
            final ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);//ვქმნით ობიექტის შემომტან კანალს
            final Object one=objectInputStream.readObject();//ვკითხულობთ ობიექტს
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
                        final FileOutputStream fileOutputStream=new FileOutputStream("settings.st");
                        final ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
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
                    final JButton openChangeStructure=new JButton("Change Structure");
                    final String tableName=list1.getSelectedValue().toString();
                    Class.forName("com.mysql.jdbc.Driver");
                    final Connection connection=DriverManager.getConnection("jdbc:mysql://"+settings.getHost(),settings.getUsername(),settings.getPassword());
                    final PreparedStatement preparedStatement=connection.prepareStatement(String.format("select * from %s", tableName));
                    final ResultSet resultSet=preparedStatement.executeQuery();
                    final ResultSetMetaData metadata = resultSet.getMetaData();
                    final int columnCount;
                    final JButton btnInsert=new JButton("Insert Into");
                    columnCount = metadata.getColumnCount();
                    final JCheckBox checkBoxQuery;
                    checkBoxQuery=new JCheckBox("serch the whole word");

                    tableModel = new DefaultTableModel(new Object[]{},0);
                    final JFrame frameTable=new JFrame("table "+ tableName);
                    final Vector<String> v=new Vector<String>();

                    for(int i=1;i<=columnCount;i++){
                        final String name=metadata.getColumnName(i);
                        final String colType= metadata.getColumnTypeName(i);

                        tableModel.addColumn(String.format("%s(%s)", name, colType));
                        v.add(name);

                    }
                    while (resultSet.next()){
                        final String[] ss=new String[columnCount];
                        for (int i=1;i<=columnCount;i++){
                            ss[i-1]=resultSet.getString(i);
                        }
                        tableModel.addRow(ss);
                    }

                    table = new JTable(tableModel);

                    frameTable.setSize(800,400);
                    final JTextField searchField;
                    searchField = new JTextField();
                    final JComboBox comboBox=new JComboBox(v);



                    final JButton btn1=new JButton("Search");

                    panelT = new JPanel();
                    panelT.add(new JScrollPane(table));
                    final JPanel panelT2 =new JPanel();


                    searchField.setColumns(15);
                    panelT2.add(comboBox);
                    panelT2.add(checkBoxQuery);
                    panelT2.add(searchField);
                    panelT2.add(btn1);
                    panelT2.add(btnInsert);
                    panelT2.add(openChangeStructure);
                    panelT.add(panelT2);


                    panelT.setLayout(new BoxLayout(panelT, BoxLayout.Y_AXIS));
                    frameTable.getContentPane().add(panelT);

                    frameTable.setLocationRelativeTo(frame);
                    frameTable.setVisible(true);

                    btnInsert.addActionListener(new ActionListener() {

                        @Override

                        public void actionPerformed(ActionEvent e) {
                            final JFrame insertFrame=new JFrame("insert into "+tableName);

                            final JPanel inserFramePanel=new JPanel();
                            inserFramePanel.setLayout(new GridBagLayout());
                            final JButton insertFinishButton=new JButton("Insert Values");
                            final ArrayList<JTextField>insertFields=new ArrayList<JTextField>();
                            final ArrayList<JLabel>insertLabels=new ArrayList<JLabel>();
                            for (int i=0;i<columnCount;i++){
                                insertFields.add(new JTextField());
                                insertLabels.add(new JLabel(tableModel.getColumnName(i)));
                            }
                            for (int i=0;i<columnCount;i++){
                                JPanel kk=new JPanel();
                                kk.add(insertLabels.get(i));
                                insertFields.get(i).setColumns(10);
                                kk.add(insertFields.get(i));
                                inserFramePanel.add(kk);
                            }
                            inserFramePanel.add(insertFinishButton);
                            insertFrame.getContentPane().add(new JScrollPane(inserFramePanel));

                            insertFrame.setResizable(false);
                            insertFrame.setSize(800,150);
                            insertFrame.setVisible(true);

                            insertFinishButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String query="";
                                    query+=String.format("INSERT INTO %s VALUES(",tableName);
                                    for (int i=0;i<columnCount;i++){
                                        if(insertFields.get(i).getText().isEmpty()){
                                            query+="NULL ";
                                        }else{
                                               query+=String.format("'%s'", insertFields.get(i).getText());
                                        }
                                        if (i<columnCount-1){
                                            query+=",";
                                        }
                                    }
                                    query+=")";
                                    System.out.println(query);
                                    final String[] all = settings.getHost().split("/");
                                    final MysqlDataSource ds = new MysqlConnectionPoolDataSource();
                                    ds.setServerName(all[0]);
                                    ds.setPort(3306);
                                    ds.setUser(settings.getUsername());
                                    ds.setPassword(settings.getPassword());
                                    Connection connection = null ;
                                    try {
                                        connection =  ds.getConnection();
                                        Statement statement = connection.createStatement();
                                        statement.executeUpdate("USE "+all[1]) ;

                                        statement.executeUpdate(query);
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                                    }


                                }
                            });


                        }
                    });
                    openChangeStructure.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                        }
                    });
                    btn1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String searchString=searchField.getText().toString();
                            try {
                                Class.forName("com.mysql.jdbc.Driver");
                                String col=comboBox.getSelectedItem().toString();
                                Connection connection=DriverManager.getConnection("jdbc:mysql://"+settings.getHost(),settings.getUsername(),settings.getPassword());
                                StringBuilder sql=new StringBuilder();
                                final String query;
                                if(!checkBoxQuery.isSelected()){

                                    query = String.format("select * from %s where %s like '%s'", tableName,col,searchString);
                                } else{
                                    query= String.format("select * from %s where %s='%s'", tableName, col, searchString);
                                }
                                PreparedStatement statement2=connection.prepareStatement(query);
                                final ResultSet resultSet2=statement2.executeQuery();
                                final ResultSetMetaData metadata2 = resultSet2.getMetaData();
                                tableModel1 = new DefaultTableModel(new Object[]{},0);
                                for(int i=1;i<=columnCount;i++){
                                    String name=metadata2.getColumnName(i);
                                    tableModel1.addColumn(name);
                                }


                                while (resultSet2.next()){
                                    String[] ss=new String[columnCount];
                                    for (int i=1;i<=columnCount;i++){
                                        ss[i-1]=resultSet2.getString(i);
                                    }

                                    tableModel1.addRow(ss);
                                }
                                final JFrame searchFrame=new JFrame("search frame");
                                final JPanel searchPanel=new JPanel();
                                final JTable searchTable=new JTable(tableModel1);
                                searchPanel.add(new JScrollPane(searchTable));
                                searchFrame.add(searchPanel);
                                searchFrame.setSize(600,200);
                                searchFrame.setVisible(true);
                                searchFrame.setLocationRelativeTo(frameTable);

                            } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                            } catch (SQLException e1) {
                                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                        }
                    });
                }catch (Exception ee){}
            }
        });
        createTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(comboBox1.getSelectedItem().toString()!="Number Of Fields"){
                    final Vector<String> dataTypes=new Vector<String>();
                    dataTypes.add("INT ( 11 )");
                    dataTypes.add("VARCHAR( 50 )");
                    dataTypes.add("DATE");
                    dataTypes.add("TEXT");
                    dataTypes.add("TIME");
                    dataTypes.add("BLOB");
                    dataTypes.add("LONGBLOB");
                    dataTypes.add("ENUM");
                    dataTypes.add("BOOL");
                    dataTypes.add("SET");
                    dataTypes.add("DECIMAL");
                    dataTypes.add("CHAR");
                    final JCheckBox dropTableCheck=new JCheckBox("Drop table if existes");


                    createTableFrame = new JFrame("Create Table");
                    final int numberOfFields;
                    numberOfFields = Integer.valueOf(comboBox1.getSelectedItem().toString());
                    final ArrayList<JComboBox> comboBoxes;
                    comboBoxes = new ArrayList<JComboBox>();
                    final ArrayList<JTextField> textFields;
                    textFields = new ArrayList<JTextField>();
                    final ArrayList<JRadioButton> radioButtons;
                    radioButtons = new ArrayList<JRadioButton>();
                    final ArrayList<JRadioButton> radioButtonsAutoIncriment;
                    radioButtonsAutoIncriment = new ArrayList<JRadioButton>();
                    final ButtonGroup group;
                    group = new ButtonGroup();
                    for(int i=0;i<numberOfFields;i++){
                        textFields.add(new JTextField());
                        comboBoxes.add(new JComboBox(dataTypes));
                        radioButtons.add(new JRadioButton("primary"));
                        radioButtonsAutoIncriment.add(new JRadioButton("auto inc"));
                        }
                    for (int i=0;i<numberOfFields;i++){
                        group.add(radioButtons.get(i));
                    }

                    final JPanel panelc;
                    panelc = new JPanel();
                    panelc.setLayout(new BoxLayout(panelc, BoxLayout.Y_AXIS));

                    for (int i=0;i<numberOfFields;i++){
                        final JPanel panelInner=new JPanel();
                        panelInner.add(textFields.get(i));
                        textFields.get(i).setColumns(15);
                        panelInner.add(comboBoxes.get(i));
                        panelInner.add(radioButtonsAutoIncriment.get(i));
                        panelInner.add(radioButtons.get(i));
                        panelc.add(panelInner);

                        }
                    panelc.add(dropTableCheck);

                    JPanel pp=new JPanel();
                    JButton btnCreate=new JButton("Create Table");

                    newTableName = new JTextField();
                    newTableName.setColumns(15);
                    pp.add(new JScrollPane(panelc));

                    pp.add(newTableName);
                    pp.add(btnCreate);

                    createTableFrame.add(pp);

                    createTableFrame.setResizable(false);
                    createTableFrame.setSize(400,450);


                    createTableFrame.setLocationRelativeTo(frame);
                    createTableFrame.setVisible(true);
                    btnCreate.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String query="";
                            query+="CREATE TABLE ";
                            query+=newTableName.getText().toString();
                            query+=" (";
                            for(int i=0;i<numberOfFields;i++){
                                    query+=String.format("%s %s",textFields.get(i).getText().toString(),comboBoxes.get(i).getSelectedItem().toString());
                                if(radioButtonsAutoIncriment.get(i).isSelected()){
                                    query+=" AUTO_INCREMENT ";
                                }
                                if(i<numberOfFields-1){
                                    query+=",";
                                }
                            }
                            for (int i=0;i<numberOfFields;i++){
                                if(radioButtons.get(i).isSelected()){
                                    query+=String.format(", PRIMARY KEY (%s) ",textFields.get(i).getText().toString());
                                }
                            }
                            query+=")";

                            System.out.println(query);

                            final String[] all = settings.getHost().split("/");
                            final MysqlDataSource ds = new MysqlConnectionPoolDataSource();
                            ds.setServerName(all[0]);
                            ds.setPort(3306);
                            ds.setUser(settings.getUsername());
                            ds.setPassword(settings.getPassword());
                            Connection connection = null ;
                            try {
                                connection =  ds.getConnection();
                                Statement statement = connection.createStatement();
                                statement.executeUpdate("USE "+all[1]) ;
                                if(dropTableCheck.isSelected()){
                                    statement.execute(String.format("DROP TABLE IF EXISTS %s", newTableName.getText().toString()));
                                }
                                statement.executeUpdate(query);
                            } catch (SQLException e1) {
                                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                        }
                    });

            } }
        });

        refreshListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        delateTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Yes Delete",
                        "No don't Delete"};
                int deleteConfim;
                deleteConfim = JOptionPane.showOptionDialog(frame, "delete table " + list1.getSelectedValue().toString(), "message",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
                if (deleteConfim==JOptionPane.OK_OPTION){
                    String[] all = settings.getHost().split("/");
                    MysqlDataSource ds = new MysqlConnectionPoolDataSource();
                    ds.setServerName(all[0]);
                    ds.setPort(3306);
                    ds.setUser(settings.getUsername());
                    ds.setPassword(settings.getPassword());
                    Connection connection = null ;
                    try {
                        connection =  ds.getConnection();
                        Statement statement = connection.createStatement();
                        statement.executeUpdate("USE "+all[1]) ;

                            statement.execute(String.format("DROP TABLE %s", list1.getSelectedValue().toString()));
                        refreshListButton.doClick();

                    } catch (SQLException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }


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
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        frame = new JFrame("mySQLViewer");
        frame.setContentPane(new main().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
    }


}