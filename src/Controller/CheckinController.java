package Controller;

import Boundary.CheckinView;
import Entity.BookingInformation;
import Tool.FileReaderWriter;
import Tool.JsonTool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
/**
 * The controller of checkin  page
 */
public class CheckinController implements Controller{
    private CheckinView checkinView;

    public  ArrayList<BookingInformation> bookingInfoList;


    /**
     * Constructor of page controller
     */
    public CheckinController(){
        checkinView=new CheckinView();
        bookingInfoList=new ArrayList<BookingInformation>();
    }

    /**
     * lunch page method
     */
    public void startPage(){
        JFrame frame =new JFrame("British Airways");
        frame.setSize(525,380);
        frame.getContentPane().add(checkinView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.requestFocus();
        checkinView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton eventSource = (JButton) e.getSource();

                if (eventSource.equals(checkinView.button1))            //back
                {

                    //close this page and go to last page
                    frame.setVisible(false);
                    frame.dispose();
                    startLastPage();

                }else if(eventSource.equals(checkinView.button2))       // next
                {
                    //close this page and go to next page
                    boolean valid=true;

                    String input=checkinView.getInput;
                    System.out.println(input);
                    int inputNo=0;
                    try {
                        inputNo=Integer.parseInt(input);
                    } catch (NumberFormatException ee) {
                       valid=false;
                    }
                    if(!valid){
                        checkinView.label4.setVisible(true);
                        return;
                    }
                    else{
                        try {
                            //valid=searchBookingNo(inputNo);
                            valid= FileReaderWriter.searchBookingNo(inputNo,bookingInfoList);
                        } catch (IOException ex) {
                            System.out.println("error in search inputNO!");
                        }
                        if(valid){ //if found the bookingNo
                            frame.setVisible(false);
                            frame.dispose();
                            startNextPage();
                        }
                        else{ // if Does not found the bookingNo
                            checkinView.label4.setVisible(true);
                            return;
                        }
                    }
                }
                else if(eventSource.equals(checkinView.button3))
                {
                    //close this page and go to ID card number checkin page
                    frame.setVisible(false);
                    frame.dispose();
                    startIDCardCheckPage();

                }
                else if(eventSource.equals(checkinView.button4))   //use scan ID card method
                {
                    //close this page and go to next page
                    int inputNo=123456;
                    boolean valid=false;
                    try {
                        //valid=searchBookingNo(inputNo);
                        valid= FileReaderWriter.searchBookingNo(inputNo,bookingInfoList);
                    } catch (IOException ex) {
                        System.out.println("error in search inputNO! ID card Scan");
                    }
                    if(valid){ //if found the bookingNo
                        frame.setVisible(false);
                        frame.dispose();
                        startNextPage();
                    }
                    else{ // if Does not found the bookingNo
                        checkinView.label4.setText("Can not identify you ID card!");
                        checkinView.label4.setVisible(true);
                        return;
                    }
                }
            }
        });
    }

    @Override
    public void startNextPage() {
        new ChooseFlightController(bookingInfoList).startPage();
    }
    @Override
    public void startLastPage() {
        new WelcomeController().startPage();
    }

    /**
     * start use id card number to check in page
     */
    public void startIDCardCheckPage(){
        new IDNoCheckinController().startPage();
    }

}
