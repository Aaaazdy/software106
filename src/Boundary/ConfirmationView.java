package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ConfirmationView extends JPanel {
    public JButton button1;//next step button
    public JButton button2;//clear button
    public JCheckBox checkbox1;

    public JLabel label1;

    public JPanel p1;

    public JPanel p2;

    public JTable table;

    private List<ActionListener> listeners;

    public void addActionListener(ActionListener actionlistener){
        listeners.add(actionlistener);
    }


    public ConfirmationView(){
        listeners=new ArrayList<ActionListener>();

        setLayout(new BorderLayout());
        p1 = new JPanel(new BorderLayout());
        p2 = new JPanel();
        label1 = new JLabel("Please confirm your information", JLabel.CENTER);
        label1.setFont(new java.awt.Font("Black", 1, 30));
        checkbox1 = new JCheckBox("I CONFIRM ALL THE INFORMATION ABOVE");

        p1.setBorder(BorderFactory.createLoweredBevelBorder());
        p1.add(checkbox1, BorderLayout.SOUTH);

        button1 = new JButton("Back");
        button2 = new JButton("Confirm");
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
        p2.add(button1);//add the button to last JPanel
        p2.add(button2);//add the button to last JPanel

        //add the modules to the page and set the layout
        add(BorderLayout.NORTH, label1);
        add(BorderLayout.CENTER, p1);
        add(BorderLayout.SOUTH, p2);
    }
}
