package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Entity.BookingInformation;

public class MealSelect extends JFrame implements ActionListener
{
    JCheckBox Beefnoodles;//used to record the first name
    JCheckBox Boxlunch;//used to record the last name
    JCheckBox Sandwich;//used to record the creditcard number
    JButton button1;//next step button
    JButton button2;//clear button

    private int checkCount = 0;//used to count the number of the items that are selected
    private String selectedItem;//used to store the item of the checkbox
    //entity
    BookingInformation customer1;

    public MealSelect(BookingInformation customer)
    {
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

    //
    // When this method is called,it will check that weather all the information has been input
    //@param values all the information that needed to complete
    //@return boolean it will return true if it is qualified to go to the next page,return false if no.
    //
    public void check()
    {
        if (Beefnoodles.isSelected())
        {
            checkCount++;
            selectedItem = "Beef noodles";
        }
        if (Boxlunch.isSelected())
        {
            checkCount++;
            selectedItem = "Box lunch";
        }
        if (Sandwich.isSelected())
        {
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
     * when user click any button,this method will called to specify which button that user has clicked
     * and then perform respond action.
     *
     * @param e action event
     */
    public void actionPerformed(ActionEvent e)
    {
        //to record which button that user has clicked
        JButton eventSource = (JButton) e.getSource();

        if (eventSource.equals(button1))
        {//back
            System.exit(0);
        } else
        {//Next button
            check();
            if (checkCount == 0)
            {//if the select is empty
                showMessage2();
                reset();

            } else if (checkCount >= 2)
            {
                showMessage3();
                reset();
            } else
            {
                this.customer1.setPrimaryFood(selectedItem);
                //go to next page:
                new ServiceAddition(this.customer1).setVisible(true);
                setVisible(false);
                dispose();                //hid and dispose the first page
            }
        }
    }
}





