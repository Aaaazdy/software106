package Controller;

import Boundary.ChooseSeatView;
import Entity.BookingInformation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChooseSeatController implements Controller{

    ChooseSeatView chooseSeatView;

    ArrayList<BookingInformation> bookingInformationList;

    BookingInformation bookingInformation;
    public ChooseSeatController(ArrayList<BookingInformation> bookingInformationArrayList,BookingInformation bookingInfo){
        chooseSeatView=new ChooseSeatView();
        bookingInformationList=bookingInformationArrayList;
        bookingInformation=bookingInfo;
    }

    public void startPage(){
        JFrame frame =new JFrame("British Airways");

        frame.setSize(500, 700);
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
                    //check validity,close this page and go to next page
                    if(!chooseSeatView.checkValidity()){
                        JOptionPane.showMessageDialog(null, "MessageDialog", "Error", JOptionPane. ERROR_MESSAGE);
                    }
                    else
                    {
                        //set the seatNo
                        System.out.println(chooseSeatView.getSelectedSeatNo());
                        bookingInformation.setSeat(chooseSeatView.getSelectedSeatNo());
                        startNextPage();
                        //new MealSelect(c1).setVisible(true);
                        frame.setVisible(false);
                        frame.dispose();

                    }

                }
                else{                                                                           //seat button
                    for( int i = 1 ; i <= 60 ; i++ )
                        if( e.getSource() == chooseSeatView.bt[i] )
                        {
                            if( chooseSeatView.bt[i].getIcon() == chooseSeatView.whiteicon )	//select this seat
                            {
                                chooseSeatView.bt[i].setIcon(chooseSeatView.greenicon);

                                chooseSeatView.cnt++;
                            }
                            else if(chooseSeatView.bt[i].getIcon() == chooseSeatView.greenicon)	//unselect this seat
                            {
                                chooseSeatView.bt[i].setIcon(chooseSeatView.whiteicon);
                                chooseSeatView.cnt--;
                            }
                            break;
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
}
