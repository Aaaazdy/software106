package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AdditionalServiceView extends JPanel{

    public LinkedList<JCheckBox> options = new LinkedList<JCheckBox>();

    public LinkedList<String> content = new LinkedList<String>();
    public JButton button1;//next step button
    public JButton button2;//back button

    public JButton button3;//count button

    public JButton button4;//reset button

    public int checkCount = 0;//used to count the total money
    private List<ActionListener> listeners;

    
    /** 
     * @param actionlistener
     */
    public void addActionListener(ActionListener actionlistener){
        listeners.add(actionlistener);
    }

    public AdditionalServiceView(){
        listeners=new ArrayList<ActionListener>();
        setLayout(new GridLayout(5, 1));
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();

        options.add(new JCheckBox("extraLegroom"));
        options.add(new JCheckBox("moviePlayer"));
        options.add(new JCheckBox("blanket"));
        options.add(new JCheckBox("headphoneJacket"));
        options.add(new JCheckBox("chicken"));
        options.add(new JCheckBox("bread"));
        options.add(new JCheckBox("cola"));
        options.add(new JCheckBox("coffee"));
        options.add(new JCheckBox("steak"));
        options.add(new JCheckBox("salad"));

        //panel 1
        JLabel label1 = new JLabel("Please choose your meal during flight:", JLabel.CENTER);
        label1.setFont(new Font("Black", 1, 20));
        p1.add(label1, BorderLayout.CENTER);// to make sure the title is in the central of the panel.

        //panel 2
        p2.setBorder(BorderFactory.createLoweredBevelBorder());
        Iterator<JCheckBox> iter = options.iterator();
        for (int i = 0; i < 4; i++)
        {
            p2.add(iter.next());

        }

        //panel 3
        p3.setBorder(BorderFactory.createLoweredBevelBorder());
        for (int i = 0; i < 6; i++)
        {
            p3.add(iter.next());

        }

        //panel 4
        button3 = new JButton("count");
        button3.setSize(160, 150);
        button4 = new JButton("reset");
        button4.setSize(160, 150);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<listeners.size();++i){
                    listeners.get(i).actionPerformed(e);
                }
            }
        });
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<listeners.size();++i){
                    listeners.get(i).actionPerformed(e);
                }
            }
        });

        p4.add(button3);
        p4.add(button4);

        //panel 5
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
        button1.setSize(180, 160);
        button2.setSize(180, 160);
        p5.add(button1);
        p5.add(button2);

        //add the panels to the page(frame)
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);

    }
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

    
    /** 
     * @param count
     */
    public void showMessage4(int count)
    {
        JOptionPane.showMessageDialog(this, "The total money is :  " + "$" + count , "the choosed services",
                JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * function check() is used to check whether the checkbox is selected, if true,
     * the select items will be stored into LinkList content
     */
    public void check()
    {
        Iterator<String> iter=content.iterator();
        while(iter.hasNext())
        {
            iter.next();
            iter.remove();
        }
        for (JCheckBox item : options)
        {
            if (item.isSelected())
            {
                String a = item.getText();
                content.add(a);
            }
        }
    }

    /**
     *     function count() is used to count and illustrate the overall money
     *     of the additional service on the screen
     */

    public void count()
    {
        checkCount = 0;
        for (String a : content)
        {
            switch (a)
            {
                case "extraLegroom":
                    checkCount += 5;
                    break;
                case "moviePlayer":
                    checkCount += 10;
                    break;
                case "blanket":
                    checkCount += 1;
                    break;
                case "headphoneJacket":
                    checkCount += 2;
                    break;
                case "chicken":
                    checkCount += 10;
                    break;
                case "bread":
                    checkCount += 3;
                    break;
                case "cola":
                    checkCount += 2;
                    break;
                case "coffee":
                    checkCount += 3;
                    break;
                case "steak":
                    checkCount += 50;
                    break;
                case "salad":
                    checkCount += 10;
                    break;
            }

        }
        showMessage4(checkCount);
    }

    public void reset()
    {
        for (JCheckBox item : options)
        {
            item.setSelected(false);
        }
        Iterator<String> iter = content.iterator();
        while (iter.hasNext())
        {
            iter.next();
            iter.remove();
        }
    }
}
