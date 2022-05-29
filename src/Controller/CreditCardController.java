package Controller;

import Boundary.CreditcardView;
import Entity.BookingInformation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The controller of credit card information input page
 */
public class CreditCardController implements Controller{

    CreditcardView creditcardView;

    ArrayList<BookingInformation> bookingInformationList;

    BookingInformation bookingInformation;

    /**
     * Constructor of page controller
     * @param bookingInformationArrayList all the booked flight information
     * @param bookingInfo the flight information that is under checkin
     */
    public CreditCardController(ArrayList<BookingInformation> bookingInformationArrayList, BookingInformation bookingInfo){
        creditcardView=new CreditcardView();
        bookingInformationList=bookingInformationArrayList;
        bookingInformation=bookingInfo;
    }

    /**
     * lunch page method
     */
    public void startPage(){
        JFrame frame =new JFrame("British Airways");
        frame.setSize(525,380);
        frame.getContentPane().add(creditcardView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        creditcardView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton eventSource = (JButton) e.getSource();

                if (eventSource.equals(creditcardView.button1))            //back
                {

                    //close this page and go to last page
                    frame.setVisible(false);
                    frame.dispose();
                    startLastPage();

                }else if(eventSource.equals(creditcardView.button2))
                {
                    //close this page and go to next page
                    if (creditcardView.checkQualified())
                    {
                        //if the credit card information is correct

                        //update the value of customer1

                        bookingInformation.setCredNumber(creditcardView.creditNum.getText());
                        bookingInformation.setSecurCode(creditcardView.securCode.getText());
                        bookingInformation.setCredFirst(creditcardView.firstName.getText());
                        bookingInformation.setCredSecond(creditcardView.lastName.getText());

                        //go to next page:
                        frame.setVisible(false);//hid and dispose the first page
                        frame.dispose();
                        //new ConfirmationController(this.customer1).startPage();

                    } else
                    {
                        creditcardView.showMessage1();
                        creditcardView.creditNum.setText("");
                        creditcardView.securCode.setText("");
                        creditcardView.firstName.setText("");
                        creditcardView.lastName.setText("");
                    }
                    frame.setVisible(false);
                    frame.dispose();
                    startNextPage();
                }

            }
        });
    }
    @Override
    public void startNextPage() {
        new ConfirmationController(bookingInformationList,  bookingInformation).startPage();

    }
    @Override
    public void startLastPage() {
            new AdditionalServiceController( bookingInformationList,  bookingInformation).startPage();
    }
}
