package Controller;

import Boundary.ChooseMealView;
import Entity.BookingInformation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChooseMealController implements Controller{
    ChooseMealView chooseMealView;

    BookingInformation bookingInformation;

    ArrayList<BookingInformation> bookingInformationList;
    public ChooseMealController(ArrayList<BookingInformation> bookingInformationArrayList, BookingInformation bookingInfo){
        chooseMealView=new ChooseMealView();
        bookingInformationList=bookingInformationArrayList;
        bookingInformation=bookingInfo;
    }

    public void startPage(){
        JFrame frame =new JFrame("British Airways");
        frame.setSize(1280,720);
        frame.getContentPane().add(chooseMealView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        /**
         * when user click any button,this method will called to specify which button that user has clicked
         * and then perform respond action.
         *
         * @param e action event
         */
        chooseMealView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton eventSource = (JButton) e.getSource();

                if (eventSource.equals(chooseMealView.button1))            //back
                {

                    //close this page and go to last page
                    frame.setVisible(false);
                    frame.dispose();
                    startLastPage();

                }else if(eventSource.equals(chooseMealView.button2)) {

                    chooseMealView.check();
                    if (chooseMealView.checkCount == 0){//if the select is empty
                        chooseMealView.showMessage2();
                        chooseMealView.reset();
                    }
                    else if (chooseMealView.checkCount >= 2){
                        chooseMealView.showMessage3();
                        chooseMealView.reset();
                    }
                    else{
                        bookingInformation.setPrimaryFood(chooseMealView.selectedItem);
                        //close this page and go to next page
                        frame.setVisible(false);
                        frame.dispose();
                        startNextPage();
                    }



                }
            }
        });
    }
    @Override
    public void startNextPage() {
        new AdditionalServiceController(bookingInformationList,bookingInformation).startPage();
    }
    @Override
    public void startLastPage() {
        bookingInformation.setSeat("");
        new ChooseSeatController(bookingInformationList,bookingInformation).startPage();
    }
}
