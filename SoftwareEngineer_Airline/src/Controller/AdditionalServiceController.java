package Controller;

import Boundary.AdditionalServiceView;
import Entity.BookingInformation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdditionalServiceController implements Controller{

    AdditionalServiceView additionalServiceView;

    ArrayList<BookingInformation> bookingInformationList;

    BookingInformation bookingInformation;

    public AdditionalServiceController(ArrayList<BookingInformation> bookingInformationArrayList, BookingInformation bookingInfo){
        additionalServiceView=new AdditionalServiceView();
        bookingInformationList=bookingInformationArrayList;
        bookingInformation=bookingInfo;
    }

    public void startPage(){
        JFrame frame =new JFrame("British Airways");
        frame.setSize(1280,720);
        frame.getContentPane().add(additionalServiceView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        additionalServiceView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton eventSource = (JButton) e.getSource();

                if (eventSource.equals(additionalServiceView.button1))            //back
                {

                    //close this page and go to last page
                    frame.setVisible(false);
                    frame.dispose();
                    startLastPage();

                }else if(eventSource.equals(additionalServiceView.button2))
                {
                    //close this page and go to next page
                    additionalServiceView.checkCount = 0;
                    additionalServiceView.check();
                    additionalServiceView.count();
                    //set entity
                    bookingInformation.setExtraServiceFee(additionalServiceView.checkCount);
                    for (String a : additionalServiceView.content){
                        bookingInformation.setExtraService(a);
                    }
                    //go to next page:
                    if(additionalServiceView.checkCount==0){
                        startNextPage();//confirmation pagg
                    }
                    else{
                        startCreditcardPage();
                    }

                    //hid and dispose the page
                    frame.setVisible(false);
                    frame.dispose();



                }else if (eventSource.equals(additionalServiceView.button3))     //count button
                {
                    additionalServiceView.check();
                    additionalServiceView.count();

                } else if (eventSource.equals(additionalServiceView.button4))     //reset button
                {
                    additionalServiceView.reset();
                    additionalServiceView.count();
                }
            }
        });

    }
    @Override
    public void startNextPage() {
        new ConfirmationController(bookingInformationList,bookingInformation).startPage();
    }
    @Override
    public void startLastPage() {
        new ChooseMealController(bookingInformationList,bookingInformation).startPage();
    }
    public void startCreditcardPage(){
        new CreditCardController(bookingInformationList,bookingInformation).startPage();
    }
}
