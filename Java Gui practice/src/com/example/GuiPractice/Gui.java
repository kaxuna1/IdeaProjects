
    package com.example.GuiPractice;

    import javax.swing.*;

    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.MouseEvent;
    import java.awt.event.MouseMotionListener;

    import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

    class Gui extends JFrame implements ActionListener, MouseMotionListener {
        public TextField text = new TextField(20);
        JButton b;
        private int numClicks = 0;
    public static void main(String[] args) {
               Gui frame=new Gui("kaxa");
        frame.setSize(300,300);
                frame.setVisible(true);


    }
     public Gui(String title){
               super(title);
                setLayout(new FlowLayout());
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                b=new JButton("click");
                add(b);
                add(text);
                b.addActionListener(this);
                b.addMouseMotionListener(this);
         addMouseMotionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            numClicks++;
                     text.setText(numClicks+"");
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            numClicks++;
            text.setText(numClicks+"");
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
            numClicks++;
            text.setText(e.getX()+"");
        }
    }
