package Controller;

import Boundary.CheckinView;
import Boundary.ChooseFlightView;
import Boundary.ConfirmationView;
import Entity.BookingInformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChooseFlightController implements Controller {
    private ChooseFlightView chooseFlightView;


    public ArrayList<BookingInformation> bookingInfoList;


    public ChooseFlightController(ArrayList<BookingInformation> bookingInfos){
        chooseFlightView =new ChooseFlightView();
        bookingInfoList=bookingInfos;
    }

    public void startPage(){
        JFrame frame =new JFrame("British Airways");
        frame.setSize(525,380);
        frame.getContentPane().add(chooseFlightView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
