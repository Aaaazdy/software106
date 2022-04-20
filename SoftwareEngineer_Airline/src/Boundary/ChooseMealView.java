package Boundary;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChooseMealView extends JPanel{

    JCheckBox Beefnoodles;
    JCheckBox Boxlunch;
    JCheckBox Sandwich;
    public JButton button1;//next step button
    public JButton button2;//back button

    public int checkCount = 0;//used to count the number of the items that are selected

    public String selectedItem;//used to store the item of the checkbox
    private List<ActionListener> listeners;

    public void addActionListener(ActionListener actionlistener){
        listeners.add(actionlistener);
    }

    public ChooseMealView(){
        listeners=new ArrayList<ActionListener>();
        setLayout(new GridLayout(5, 1));
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();

        //panel 1
        JLabel label1 = new JLabel("Please choose your meal during flight:", JLabel.CENTER);
        label1.setFont(new Font("Black", 1, 20));
        p1.add(label1, BorderLayout.CENTER);// to make sure the title is in the central of the panel.

        //panel 2
        p2.setBorder(BorderFactory.createLoweredBevelBorder());
        p3.setBorder(BorderFactory.createLoweredBevelBorder());
        p4.setBorder(BorderFactory.createLoweredBevelBorder());
        Beefnoodles = new JCheckBox("Beef noodles");
        Boxlunch = new JCheckBox("Box lunch");
        Sandwich = new JCheckBox("Sandwich");
        Beefnoodles.setBorder(new TitledBorder(new EtchedBorder(), "First Name"));
        Boxlunch.setBorder(new TitledBorder(new EtchedBorder(), "Second Name"));
        Sandwich.setBorder(new TitledBorder(new EtchedBorder(), "Credit card number"));

        p2.add(Beefnoodles);
        p3.add(Boxlunch);
        p4.add(Sandwich);

        //panel 5
        button1 = new JButton("Back");
        button2 = new JButton("Next");
        button1.setSize(180, 160);
        button2.setSize(180, 160);
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

        //add the panels to the page(frame)
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
    }
    public void check()
    {
        if (Beefnoodles.isSelected()){
            checkCount++;
            selectedItem = "Beef noodles";
        }
        if (Boxlunch.isSelected()){
            checkCount++;
            selectedItem = "Box lunch";
        }
        if (Sandwich.isSelected()){
            checkCount++;
            selectedItem = "Sandwich";
        }
        //check the number of the checkbox and record the selected item
    }

    public void reset()
    {
        Sandwich.setSelected(false);
        Beefnoodles.setSelected(false);
        Boxlunch.setSelected(false);
        checkCount = 0;
    }
    /**
     * when there are missing parts of the needed information and user click next,this method will be called
     * to create a pop-up window to tell the user.
     */
    public void showMessage2()
    {
        JOptionPane.showMessageDialog(this, "Please choose an item", "Wrong information",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage3()
    {
        JOptionPane.showMessageDialog(this, "You can choose no more than one item", "Wrong information",
                JOptionPane.ERROR_MESSAGE);
    }
}
