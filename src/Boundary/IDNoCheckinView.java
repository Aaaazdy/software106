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

    
    /** 
     * @param actionlistener
     */
    public void addActionListener(ActionListener actionlistener){
        listeners.add(actionlistener);
    }

    public IDNoCheckinView(){
        listeners=new ArrayList<ActionListener>();
        setLayout(null);
        Font font = new Font("Black",1,18);
        p1=new JPanel();
        p2=new JPanel();
        p3=new JPanel();
        p4=new JPanel();
        p5=new JPanel();

        label1=new JLabel("Check In");
        p1.add(label1);
        label1.setFont(new java.awt.Font("Black", 1, 40));
        p1.setBounds(0,21,525,70);
        p2.setLayout(null);
        p3.setLayout(null);
        label2=new JLabel("Surname:");
        label2.setFont(font);
        field1=new JTextField();
        label2.setBounds(80,0,100,40);
        field1.setBounds(220,0,250,40);
        p2.add(label2);
        p2.add(field1);
        p2.setBounds(0,96,525,40);
        label3=new JLabel("ID number:");
        label3.setFont(font);
        field2=new JTextField();
        label3.setBounds(80,0,150,40);
        field2.setBounds(220,0,250,40);
        p3.add(label3);
        p3.add(field2);
        p3.setBounds(0,180,525,40);
        label4=new JLabel("Can not find your booking information!");
        label4.setFont(new Font("Black",1,15));
        label4.setForeground(Color.red);
        label4.setVisible(false);
        p4.add(label4);
        p4.setBounds(0,230,525,30);
        button1=new JButton("Back");
        button1.setPreferredSize(new Dimension(84,30));
        button2=new JButton("Next");
        button2.setPreferredSize(new Dimension(84,30));
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
        p5.setBounds(0,270,525,30);
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
    }
}
