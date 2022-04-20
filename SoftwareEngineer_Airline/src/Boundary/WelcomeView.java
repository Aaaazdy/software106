package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class WelcomeView extends JPanel{
    public JPanel p1 ;
    public JPanel p2 ;
    public JButton button;//start check in
    public JLabel lable1;

    private List<ActionListener> listeners;
    public void addActionListener(ActionListener actionlistener){
        listeners.add(actionlistener);
    }
    public WelcomeView(){
        listeners=new ArrayList<ActionListener>();
        setLayout(new GridLayout(2, 1));

        p1= new JPanel();
        p2 = new JPanel();
        button=new JButton();
        lable1=new JLabel();
        lable1.setText("Welcome");
        lable1.setFont(new java.awt.Font("Black", 1, 40));
        button.setText("Start checkin");
        button.setSize(30,40);
        p1.add(lable1);
        p2.add(button);
        add(p1);
        add(p2);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<listeners.size();++i){
                    listeners.get(i).actionPerformed(e);
                }
            }
        });

    }
}
