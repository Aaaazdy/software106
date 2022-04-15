package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChooseFlightView extends JPanel {
    public JPanel p1;
    public JPanel p2;
    public JPanel p3;
    public JPanel p4;
    public JPanel p5;
    public JPanel south;
    public JLabel label1;
    public JLabel label2;
    public JTextArea textArea;
    public JComboBox comboBox;
    public JButton button1;//next step button
    public JButton button2;//back button



    private List<ActionListener> listeners;
    public void addActionListener(ActionListener actionlistener){
        listeners.add(actionlistener);
    }

    public ChooseFlightView(){
        listeners=new ArrayList<ActionListener>();
        setLayout(null);
        JPanel p1=new JPanel();
        JPanel p2=new JPanel();
        JPanel p3=new JPanel();
        JPanel p4=new JPanel();
        JPanel p5=new JPanel();

        label1=new JLabel("Please choose the flight");
        label1.setFont(new java.awt.Font("Black", 1, 30));
        p1.setBounds(0, 10, 525, 40);
        p1.add(label1);

        textArea=new JTextArea(12,40);
        p2.setBounds(0, 96, 525, 194);
        p2.add(textArea);
        comboBox=new JComboBox();
        p3.add(comboBox);
        p3.setBounds(0, 63, 525, 30);
        label2=new JLabel("please select a flight to check in!");
        label2.setVisible(false);
        p4.add(label2);

        button1 = new JButton("Back");
        button2 = new JButton("Next");
//        button1.setBounds(54, 257, 97, 23);
//        button2.setBounds(216, 257, 97, 23);
        p5.setBounds(0,290,525,40);
        p5.add(button1);
        p5.add(button2);
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

        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
//        add(button1);
//        add(button2);
    }
}
