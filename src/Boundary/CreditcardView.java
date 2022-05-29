package Boundary;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CreditcardView extends JPanel{

    public JTextField firstName;//used to record the first name

    public JTextField lastName;//used to record the last name

    public JTextField creditNum;//used to record the creditcard number

    public JTextField securCode;//used to record the security code
    public JButton button1;//next step button

    public JButton button2;//back button

    private List<ActionListener> listeners;

    public void addActionListener(ActionListener actionlistener){
        listeners.add(actionlistener);
    }

    public CreditcardView(){
        listeners=new ArrayList<ActionListener>();

        //initialize the first page setup
        setLayout(null);

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        //panel 1
        JLabel label1 = new JLabel("Please enter your credit card information :");
        label1.setFont(new Font("Black", 1, 20));
        p1.add(label1);// to make sure the title is in the central of the panel.
        p1.setBounds(0,20,525,40);
        //panel 2
//        p2.setBorder(BorderFactory.createLoweredBevelBorder());
//        p3.setBorder(BorderFactory.createLoweredBevelBorder());
//        p4.setBorder(BorderFactory.createLoweredBevelBorder());
        firstName = new JTextField(25);
        lastName = new JTextField(25);
        creditNum = new JTextField(25);
        securCode = new JTextField(25);
        firstName.setBorder(new TitledBorder(new EtchedBorder(), "First Name"));
        lastName.setBorder(new TitledBorder(new EtchedBorder(), "Second Name"));
        creditNum.setBorder(new TitledBorder(new EtchedBorder(), "Credit card number"));
        securCode.setBorder(new TitledBorder(new EtchedBorder(), "Security code"));

        p2.add(firstName);
        p3.add(lastName);
        p4.add(creditNum);
        p6.add(securCode);
        p2.setBounds(0,65,525,50);
        p3.setBounds(0,125,525,50);
        p4.setBounds(0,185,525,50);
        p6.setBounds(0,245,525,50);
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
//        button1.setSize(180, 160);
//        button2.setSize(180, 160);
        p5.add(button1);
        p5.add(button2);
        p5.setBounds(0,305,525,30);
        //add the panels to the page(frame)
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(p6);
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
     *  When this method is called,it will check that weather all the information has been input
     * @return boolean it will return true if it is qualified to go to the next page,return false if no.
     */
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
}

