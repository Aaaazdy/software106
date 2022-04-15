package Controller;

import Boundary.ChooseSeatView;
import Entity.BookingInformation;
import Tool.FileReaderWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ChooseSeatController implements Controller{

    int seatcount; // to record whether a seat is selected

    int planemodel;// record the plane model
    ChooseSeatView chooseSeatView;

    ArrayList<BookingInformation> bookingInformationList;

    BookingInformation bookingInformation;
    public ChooseSeatController(ArrayList<BookingInformation> bookingInformationArrayList,BookingInformation bookingInfo){
        bookingInformationList=bookingInformationArrayList;
        bookingInformation=bookingInfo;

        int[] seatStatus=new int[100];
        readSeatStatus(seatStatus);
        chooseSeatView=new ChooseSeatView(seatStatus,planemodel);

    }

    public void startPage(){
        JFrame frame =new JFrame("British Airways");

        if (planemodel == 1) {
            frame.setSize(500, 700);
        } else if (planemodel == 2) {
            frame.setSize(650, 875);
        }
        frame.getContentPane().add(chooseSeatView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        chooseSeatView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton eventSource = (JButton) e.getSource();

                if (eventSource.equals(chooseSeatView.button1))            //back
                {

                    //close this page and go to last page
                    frame.setVisible(false);
                    frame.dispose();
                    startLastPage();

                }else if(eventSource.equals(chooseSeatView.button2))      //next
                {
                    // check validity,close this page and go to next page
                    if (!chooseSeatView.checkValidity()) {
                        JOptionPane.showMessageDialog(null, "MessageDialog", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // set the seatNo
                        System.out.println(chooseSeatView.getSelectedSeatNo(planemodel));
                        bookingInformation.setSeat(chooseSeatView.getSelectedSeatNo(planemodel));
                        startNextPage();
                        // new MealSelect(c1).setVisible(true);
                        frame.setVisible(false);
                        frame.dispose();
                    }

                }
                else{//seat button
                    if(planemodel==1){
                        for( int i = 1 ; i <= 60 ; i++ )
                            if( e.getSource() == chooseSeatView.bt[i] )
                            {
                                if( chooseSeatView.bt[i].getIcon() == chooseSeatView.whiteicon && seatcount==0)	//select this seat
                                {
                                    chooseSeatView.bt[i].setIcon(chooseSeatView.greenicon);
                                    seatcount++;
                                    chooseSeatView.cnt++;
                                }
                                else if(chooseSeatView.bt[i].getIcon() == chooseSeatView.whiteicon && seatcount!=0)
                                {
                                    for(int x=1;x<=60;x++){
                                        if(chooseSeatView.bt[x].getIcon()==chooseSeatView.greenicon){
                                            chooseSeatView.bt[x].setIcon(chooseSeatView.whiteicon);
                                        }
                                    }
                                    chooseSeatView.bt[i].setIcon(chooseSeatView.greenicon);
                                }
                                else if(chooseSeatView.bt[i].getIcon() == chooseSeatView.greenicon)	//unselect this seat
                                {
                                    chooseSeatView.bt[i].setIcon(chooseSeatView.whiteicon);
                                    seatcount--;
                                    chooseSeatView.cnt--;
                                }
                                break;
                            }
                    }
                    else if(planemodel==2){
                        for( int i = 1 ; i <= 96 ; i++ )
                            if( e.getSource() == chooseSeatView.bt[i] )
                            {
                                if( chooseSeatView.bt[i].getIcon() == chooseSeatView.whiteicon && seatcount==0)	//select this seat
                                {
                                    chooseSeatView.bt[i].setIcon(chooseSeatView.greenicon);
                                    seatcount++;
                                    chooseSeatView.cnt++;
                                }
                                else if(chooseSeatView.bt[i].getIcon() == chooseSeatView.whiteicon && seatcount!=0)
                                {
                                    for(int x=1;x<=96;x++){
                                        if(chooseSeatView.bt[x].getIcon()==chooseSeatView.greenicon){
                                            chooseSeatView.bt[x].setIcon(chooseSeatView.whiteicon);
                                        }
                                    }
                                    chooseSeatView.bt[i].setIcon(chooseSeatView.greenicon);
                                }
                                else if(chooseSeatView.bt[i].getIcon() == chooseSeatView.greenicon)	//unselect this seat
                                {
                                    chooseSeatView.bt[i].setIcon(chooseSeatView.whiteicon);
                                    seatcount--;
                                    chooseSeatView.cnt--;
                                }
                                break;
                            }
                    }

                }

            }
        });

    }
    public void startNextPage() {
        new ChooseMealController(bookingInformationList,bookingInformation).startPage();
    }

    public void startLastPage() {
        new ChooseFlightController(bookingInformationList).startPage();
    }

    /**
     * read and set the seat status from the data base ,if it is selected set a[i]=1, else set a[i]=0
     * read the plane model,save it in planemodel
     */
    private void readSeatStatus(int[] seatStatus){
        for(int x=0;x<seatStatus.length;x++){
            seatStatus[x]=0;
        }
        try{
            new FileReaderWriter().searchFlightInfo(bookingInformation.getFlightNumber(),seatStatus);
            planemodel=new FileReaderWriter().getPlaneModel(bookingInformation.getFlightNumber());
        }
        catch (IOException e){
            System.out.println("can not find the flight information!");
        }
    }
}
