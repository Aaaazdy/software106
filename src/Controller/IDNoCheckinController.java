package Controller;

import Boundary.IDNoCheckinView;
import Entity.BookingInformation;
import Tool.FileReaderWriter;
import Tool.JsonTool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * The controller of use id number to check in page
 */
public class IDNoCheckinController implements Controller{
    IDNoCheckinView idNoCheckinView;

    public ArrayList<BookingInformation> bookingInfoList;

    /**
     * Constructor of page controller
     */
    public IDNoCheckinController(){
        idNoCheckinView=new IDNoCheckinView();
        bookingInfoList=new ArrayList<BookingInformation>();
    }

    /**
     * lunch page method
     */
    public void startPage(){
        JFrame frame =new JFrame("British Airways");
        frame.setSize(525,380);
        frame.getContentPane().add(idNoCheckinView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        idNoCheckinView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton eventSource = (JButton) e.getSource();

                if (eventSource.equals(idNoCheckinView.button1))            //back
                {

                    //close this page and go to last page
                    frame.setVisible(false);
                    frame.dispose();
                    startLastPage();

                }else if(eventSource.equals(idNoCheckinView.button2))
                {
                    String inputSurname=idNoCheckinView.field1.getText();
                    String inputIDNumber=idNoCheckinView.field2.getText();
                    boolean valid=false;
                    try {
                        //valid=searchBooingInfo(inputSurname,inputIDNumber);
                        valid= FileReaderWriter.searchBooingInfo(inputSurname,inputIDNumber,bookingInfoList);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    if(!valid){
                        idNoCheckinView.label4.setVisible(true);
                        return ;
                    }
                    else{
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
        new ChooseFlightController(bookingInfoList).startPage();
    }
    @Override
    public void startLastPage() {
        new CheckinController().startPage();
    }

}
