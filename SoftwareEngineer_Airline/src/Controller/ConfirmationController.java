package Controller;

import Boundary.CheckinView;
import Boundary.ConfirmationView;
import Entity.BookingInformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConfirmationController {
    private ConfirmationView confirmationView;
    public BookingInformation bookingInformation;

    public ConfirmationController(ArrayList<BookingInformation> bookingInformationArrayList, BookingInformation bookingInfo){

        bookingInformation=bookingInfo;
        confirmationView  =new ConfirmationView();
        setTable();

    }

    public void startPage(){
        JFrame frame =new JFrame("British Airways");
        frame.setSize(1280,720);
        frame.getContentPane().add(confirmationView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        confirmationView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton eventSource = (JButton) e.getSource();

                if (eventSource.equals(confirmationView.button1))            //back
                {
                    //跳转到下一页面

                    //hid and dispose the page
                    frame.setVisible(false);
                    frame.dispose();
                }else if(eventSource.equals(confirmationView.button2))
                {
                    if(confirmationView.checkbox1.isSelected()){
                        frame.setVisible(false);
                        frame.dispose();
                    }
                    else{

                    }

;
                }
            }
        });
    }

    private void setTable(){
        String[] columnName = {"Information", " "};
        String[][] rowData = {
                {"Name", this.bookingInformation.getFirstName() + " " + this.bookingInformation.getLastName()},
                {"Flight number", this.bookingInformation.getFlightNumber()},
                {"Boarding gate", this.bookingInformation.getBoardingGate()},
                {"Seat", this.bookingInformation.getSeat()},
                {"Primary food", this.bookingInformation.getPrimaryFood()},
                {"Extra services", this.bookingInformation.getExtraService()}
        };
        confirmationView.table = new JTable(rowData, columnName);
        confirmationView.p1.add(confirmationView.table.getTableHeader(), BorderLayout.NORTH);//add the JTextArea to the JPanel
        confirmationView.p1.add(confirmationView.table, BorderLayout.CENTER);
    }
}
