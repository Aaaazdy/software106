package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FlightInformationView extends JPanel{
    public JButton button1;//next step button

    public JButton button2;//back button

    private List<ActionListener> listeners;

    public void addActionListener(ActionListener actionlistener){
        listeners.add(actionlistener);
    }

    public FlightInformationView(){
        listeners=new ArrayList<ActionListener>();
        setLayout(new GridLayout(5, 1));

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
    }
}
