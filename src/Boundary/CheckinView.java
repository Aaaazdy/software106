package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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

    public String getInput;
    private List<ActionListener> listeners;
    
    /** 
     * @param actionlistener
     */
    public void addActionListener(ActionListener actionlistener){
        listeners.add(actionlistener);
    }

    /**
     * The constructor of the second page
     */
    public CheckinView(){
        listeners=new ArrayList<ActionListener>();
        setLayout(null);

        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        p6 = new JPanel();

        Font font = new Font("Black",0,15);
        label1 = new JLabel("Check In");
        label1.setFont(new java.awt.Font("Black", 1, 40));
        p1.add(label1);
        p1.setBounds(0,21,525,50);

        textField=new JTextField(35);
        textField.setText("Please input your booking number");
        textField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                textField.setText("");
            }
            @Override
            public void focusLost(FocusEvent e)
            {
                getInput=textField.getText();
                textField.setText("Please input your booking number");
            }
        });
        textField.setBounds(20,19,424,23);
        p2.setLayout(null);
        p2.add(textField);
        p2.setBounds(0,76,525,61);

        label2=new JLabel("You can scan your ID card on this machine instead");
        label2.setFont(font);
        button4=new JButton("Finish scan");
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<listeners.size();++i){
                    listeners.get(i).actionPerformed(e);
                }
            }
        });
        p3.add(label2);
        p3.setBounds(0,137,525,61);
        p3.add(button4);

        label3=new JLabel("If you forget your booking number please click");
        label3.setFont(font);
        label3.setBounds(20,0,375,20);
        button3=new JButton("here");
        button3.setBounds(362,0,80,25);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<listeners.size();++i){
                    listeners.get(i).actionPerformed(e);
                }
            }
        });
        p4.add(label3);
        p4.add(button3);
        p4.setBounds(0,198,525,46);

        label4=new JLabel("Invalid input,please try again!");
        label4.setFont(new Font("Black",1,15));
        label4.setForeground(Color.red);
        label4.setVisible(false);
        p5.add(label4);
        p5.setBounds(0,250,525,39);

        button1 = new JButton("Back");
        button2 = new JButton("Next");
        button1.setPreferredSize(new Dimension(73,30));
        button2.setPreferredSize(new Dimension(73,30));
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
        p6.setBounds(0,289,525,61);

        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(p6);
    }

}
