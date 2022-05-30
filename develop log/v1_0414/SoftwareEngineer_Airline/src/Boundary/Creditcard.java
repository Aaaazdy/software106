package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Entity.BookingInformation;

public class Creditcard extends JFrame implements ActionListener
{
    JTextField firstName;//used to record the first name
    JTextField lastName;//used to record the last name
    JTextField creditNum;//used to record the creditcard number
    JTextField securCode;//used to record the security code
    JButton button1;//next step button
    JButton button2;//clear button

    //entity
    BookingInformation customer1;

    public Creditcard(BookingInformation customer)
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
        JLabel label1 = new JLabel("Please enter your credit card information to complete the payment:", JLabel.CENTER);
        label1.setFont(new Font("Black", 1, 20));
        p1.add(label1, BorderLayout.CENTER);// to make sure the title is in the central of the panel.

        //panel 2
        p2.setBorder(BorderFactory.createLoweredBevelBorder());
        p3.setBorder(BorderFactory.createLoweredBevelBorder());
        p4.setBorder(BorderFactory.createLoweredBevelBorder());
        firstName = new JTextField(35);
        lastName = new JTextField(35);
        creditNum = new JTextField(70);
        securCode = new JTextField(70);
        firstName.setBorder(new TitledBorder(new EtchedBorder(), "First Name"));
        lastName.setBorder(new TitledBorder(new EtchedBorder(), "Second Name"));
        creditNum.setBorder(new TitledBorder(new EtchedBorder(), "Credit card number"));
        securCode.setBorder(new TitledBorder(new EtchedBorder(), "Security code"));

        p2.add(firstName);
        p2.add(lastName);
        p3.add(creditNum);
        p4.add(securCode);

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
    public void showMessage1()
    {
        JOptionPane.showMessageDialog(this, "Incorrect credit card information", "Wrong information",
                JOptionPane.ERROR_MESSAGE);
    }

    
    /** 
     * @return boolean
     */
    //
    // When this method is called,it will check that weather all the information has been input
    //@param values all the information that needed to complete
    //@return boolean it will return true if it is qualified to go to the next page,return false if no.
    //
    public boolean checkQualified()
    {
        if (firstName.getText().equals("") || lastName.getText().equals("") ||
                creditNum.getText().equals("") || securCode.getText().equals(""))
        {
            return false;
        }
        //check the information correctness
        //return false;
        return true;
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
        {   //back
            new ServiceAddition(this.customer1).setVisible(true);
            setVisible(false);
            dispose();
            //hid and dispose the page
        } else
        {//Next button
            if (checkQualified())
            {
                //if the credit card information is correct

                //update the value of customer1
                this.customer1.setCredNumber(creditNum.getText());
                this.customer1.setSecurCode(securCode.getText());
                this.customer1.setFirstName(firstName.getText());
                this.customer1.setLastName(lastName.getText());

                //go to next page:
                new Confirmation(this.customer1).setVisible(true);
                setVisible(false);//hid and dispose the first page
                dispose();
            } else
            {
                showMessage1();
                creditNum.setText("");
                securCode.setText("");
                firstName.setText("");
                lastName.setText("");
            }
        }
    }
}





