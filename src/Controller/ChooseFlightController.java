package Controller;

import Boundary.ChooseFlightView;
import Entity.BookingInformation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The controller of choose flight page
 */
public class ChooseFlightController implements Controller {
    private ChooseFlightView chooseFlightView;


    public ArrayList<BookingInformation> bookingInfoList;

    /**
     * Constructor of page controller
     */
    public ChooseFlightController(ArrayList<BookingInformation> bookingInfos){
        chooseFlightView =new ChooseFlightView();
        bookingInfoList=bookingInfos;
    }
    /**
     * lunch page method
     */
    public void startPage(){
        JFrame frame =new JFrame("British Airways");
        frame.setSize(525,380);
        frame.getContentPane().add(chooseFlightView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        addFlightInfo();
        frame.setVisible(true);
        chooseFlightView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton eventSource =(JButton) e.getSource();
                if(eventSource.equals(chooseFlightView.button1)){
                    //close and start next page
                    frame.setVisible(false);
                    frame.dispose();
                    startLastPage();
                }
                else{
                    frame.setVisible(false);
                    frame.dispose();
                    int selected=chooseFlightView.comboBox.getSelectedIndex();
                    startNextPage(bookingInfoList.get(selected));
                }
            }
        });

    }

    @Override
    public void startLastPage() {
        new CheckinController().startPage();
    }

    public void startNextPage(BookingInformation bookingInfo) {
        new ChooseSeatController(bookingInfoList,bookingInfo).startPage();
    }

    /**
     * Add the information of booked flights to the page
     */
    private void addFlightInfo(){
        int cnt=1;
        for(BookingInformation x:bookingInfoList){
            chooseFlightView.textArea.append("Flight"+cnt+":\n");
            chooseFlightView.textArea.append("FlightNo:"+x.getFlightNumber()+"\n"+
                                            "Start:"+x.getStartWhere()+"\n"+
                                            "Destination:"+x.getDestWhere()+"\n"+
                                            "DuringTime:"+x.getDuringTime()+"\n");
            chooseFlightView.comboBox.addItem("Flight"+cnt);
            cnt++;
        }
    }
}
