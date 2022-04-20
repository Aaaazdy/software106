package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Entity.BookingInformation;

public class ServiceAddition extends JFrame implements ActionListener
{
    LinkedList<JCheckBox> options = new LinkedList<JCheckBox>();
    LinkedList<String> content = new LinkedList<String>();

    JButton button1;//next step button
    JButton button2;//clear button
    JButton button3;//count button
    JButton button4;//reset button

    private int checkCount = 0;//used to count the total money
    //entity
    BookingInformation customer1;

    public ServiceAddition(BookingInformation customer)
    {
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
        this.customer1 = customer;
        //initialize the first page setup
        setLayout(new GridLayout(5, 1));
        setSize(1280, 720);
        setTitle("British Airways");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //9 panels of the first page
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
        button3.addActionListener(this);
        button3.setSize(160, 150);
        p4.add(button3);
        button4 = new JButton("reset");
        button4.addActionListener(this);
        button4.setSize(160, 150);
        p4.add(button4);

        //panel 5
        button1 = new JButton("Back");
        button2 = new JButton("Next");
        button1.addActionListener(this);
        button2.addActionListener(this);
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

    public void showMessage4(int count)
    {
        JOptionPane.showMessageDialog(this, "The total money is :  " + count + "$", "the choosed services",
                JOptionPane.INFORMATION_MESSAGE);
    }


    //function check() is used to check whether the checkbox is selected, if true,
    //the select items will be stored into LinkList<String> content
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

    //function count() is used to count and illustrate the overall money
    //of the additional service on the screen
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

    /**
     * when user click any button,this method will called to specify which button that user has clicked
     * and then perform respond action.
     *
     * @param e action event
     */
    public void actionPerformed(ActionEvent e)
    {
        //to record which button that user has clicked
        JButton eventSource = (JButton) e.getSource();

        if (eventSource.equals(button1))            //back
        {
            new MealSelect(this.customer1).setVisible(true);
            setVisible(false);
            dispose();
            //hid and dispose the page
        } else if (eventSource.equals(button2))     //Next button
        {
            checkCount = 0;
            check();
            count();
            customer1.setExtraServiceFee(checkCount);
            for (String a : content)
            {
                customer1.setExtraService(a);
            }
            //go to next page:
            new Creditcard(this.customer1).setVisible(true);
            setVisible(false);
            dispose();
            //hid and dispose the page

        } else if (eventSource.equals(button3))     //count button
        {
            check();
            count();

        } else if (eventSource.equals(button4))     //reset button
        {
            reset();
            count();
        }
    }
}





