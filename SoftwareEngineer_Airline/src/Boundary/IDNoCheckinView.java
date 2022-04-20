package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class IDNoCheckinView extends JPanel{
    public JButton button1;//next step button
    public JButton button2;//back button

    public JLabel label1;

    public JLabel label2;

    public JLabel label3;

    public JLabel label4;

    public JTextField field1;

    public JTextField field2;

    public JPanel p1;

    public JPanel p2;

    public JPanel p3;

    public JPanel p4;

    public JPanel p5;

    private List<ActionListener> listeners;

    public void addActionListener(ActionListener actionlistener){
        listeners.add(actionlistener);
    }

    public IDNoCheckinView(){
        listeners=new ArrayList<ActionListener>();
        setLayout(new GridLayout(5, 1));

        p1=new JPanel();
        p2=new JPanel();
        p3=new JPanel();
        p4=new JPanel();
        p5=new JPanel();

        label1=new JLabel("Please enter the following information to complete the checkin");
        p1.add(label1);

        label2=new JLabel("Surname:");
        field1=new JTextField();
        field1.setPreferredSize(new Dimension(200,30));
        p2.add(label2);
        p2.add(field1);

        label3=new JLabel("ID numboer:");
        field2=new JTextField();
        field2.setPreferredSize(new Dimension(200,30));
        p3.add(label3);
        p3.add(field2);

        label4=new JLabel("Can not find your booking information!");
        label4.setVisible(false);
        p4.add(label4);

        button1=new JButton("Back");
        button2=new JButton("Next");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<listeners.size();++i){
                    listeners.get(i).actionPerformed(e);
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<listeners.size();++i){
                    listeners.get(i).actionPerformed(e);
                }
            }
        });

        p5.add(button1);
        p5.add(button2);

        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
    }
}
