package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CheckinView extends JPanel {
    public JButton button1;//next step button
    public JButton button2;//back button
    public JButton button3;//use id number to checkin
    public JButton button4;//finish scan id card
    public JLabel label1;
    public JLabel label2;
    public JLabel label3;

    public JLabel label4;
    public JTextField textField;
    public JPanel p1;
    public JPanel p2;
    public JPanel p3;
    public JPanel p4;
    public JPanel p5;

    public JPanel p6;
    private List<ActionListener> listeners;
    public void addActionListener(ActionListener actionlistener){
        listeners.add(actionlistener);
    }

    /**
     * The constructor of the second page
     */
    public CheckinView(){
        listeners=new ArrayList<ActionListener>();
        setLayout(new GridLayout(6, 1));

        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        p6 = new JPanel();

        label1 = new JLabel("Please input your booking number to checkin");
        label1.setFont(new java.awt.Font("Black", 1, 40));
        p1.add(label1);

        textField=new JTextField();
        textField.setPreferredSize(new Dimension(200,30));

        p2.add(textField);

        label2=new JLabel("Or you can scan your ID card ont this machine instead");
        button4=new JButton("Finish scan");
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<listeners.size();++i){
                    listeners.get(i).actionPerformed(e);
                }
            }
        });
        p3.add(label2);
        p3.add(button4);

        label3=new JLabel("If you forget your booking number please click");
        button3=new JButton("here");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<listeners.size();++i){
                    listeners.get(i).actionPerformed(e);
                }
            }
        });
        p4.add(label3);
        p4.add(button3);


        label4=new JLabel("Invalid input,please try again!");
        label4.setVisible(false);
        p5.add(label4);


        button1 = new JButton("Back");
        button2 = new JButton("Next");
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
        p6.add(button1);//add the button to last JPanel
        p6.add(button2);//add the button to last JPanel


        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(p6);
    }
}
