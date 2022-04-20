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
        setLayout(new BorderLayout());
        JPanel p1=new JPanel();
        JPanel p2=new JPanel();
        JPanel p3=new JPanel();
        JPanel p4=new JPanel();
        JPanel p5=new JPanel();
        JPanel south=new JPanel(new GridLayout(3,1));

        label1=new JLabel("Please choose the flight to checkin");
        label1.setFont(new java.awt.Font("Black", 1, 40));
        p1.add(label1);

        textArea=new JTextArea(300,100);
        p2.add(textArea);

        comboBox=new JComboBox();
        p3.add(comboBox);

        label2=new JLabel("please select a flight to check in!");
        label2.setVisible(false);
        p4.add(label2);

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
        p5.add(button1);//add the button to last JPanel
        p5.add(button2);//add the button to last JPanel

        add(BorderLayout.NORTH,p1);
        add(BorderLayout.CENTER,p2);
        south.add(p3);
        south.add(p4);
        south.add(p5);
        add(BorderLayout.SOUTH,south);
    }
}
