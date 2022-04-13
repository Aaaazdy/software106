package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Entity.BookingInformation;



public class Confirmation extends JFrame implements ActionListener
{
    JButton button1;//next step button
    JButton button2;//clear button
    JCheckBox checkbox1;
    JTable table;

    //entity
    BookingInformation customer1;

    /**
     * The constructor of the second page
     */
    public Confirmation(BookingInformation customer)
    {
        this.customer1 = customer;

        //Initialize page setup
        setSize(1280, 720);
        setTitle("British Airways");
        //initialize JPanels of this page
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel();


        //First part,LABEL
        JLabel label1 = new JLabel("Please confirm your information", JLabel.CENTER);
        label1.setFont(new java.awt.Font("Black", 1, 40));

        //Second part,chart,display the information
        String[] columnName = {"Information", " "};
        String[][] rowData = {
                {"Name", this.customer1.getFirstName() + " " + this.customer1.getLastName()},
                {"Flight number", this.customer1.getFlightNumber()},
                {"Boarding gate", this.customer1.getBoardingGate()},
                {"Seat", this.customer1.getSeat()},
                {"Primary food", this.customer1.getPrimaryFood()},
                {"Extra services", this.customer1.getExtraService()}
        };
        table = new JTable(rowData, columnName);
        checkbox1 = new JCheckBox("I CONFIRM ALL THE INFORMATION ABOVE");

        p1.setBorder(BorderFactory.createLoweredBevelBorder());
        p1.add(table.getTableHeader(), BorderLayout.NORTH);//add the JTextArea to the JPanel
        p1.add(table, BorderLayout.CENTER);
        p1.add(checkbox1, BorderLayout.SOUTH);


        //third part,the "finish" button
        JButton button1 = new JButton("Back");
        JButton button2 = new JButton("Confirm");
        button1.addActionListener(this);
        button2.addActionListener(this);
        p2.add(button1);//add the button to last JPanel
        p2.add(button2);//add the button to last JPanel

        //add the modules to the page and set the layout
        add(BorderLayout.NORTH, label1);
        add(BorderLayout.CENTER, p1);
        add(BorderLayout.SOUTH, p2);

    }

    /**
     * when user click finish button,this method will called to exit the page.
     *
     * @param e action event
     */
    public void actionPerformed(ActionEvent e)
    {
        JButton eventSource = (JButton) e.getSource();

        if (eventSource.equals(button1))            //back
        {
            new Creditcard(this.customer1).setVisible(true);
            setVisible(false);
            dispose();
            //hid and dispose the page
        }else if(eventSource.equals(button2))
        {
            System.exit(0);
        }
    }
}
