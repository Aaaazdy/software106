package Controller;

import Boundary.ConfirmationView;
import Entity.BookingInformation;
import Tool.FileReaderWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The controller of confirmation page
 */
public class ConfirmationController implements Controller{
    private ConfirmationView confirmationView;
    public BookingInformation bookingInformation;

    public ArrayList<BookingInformation> bookingInformationList;

    /**
     * Constructor of page controller
     * @param bookingInformationArrayList all the booked flight information
     * @param bookingInfo the flight information that is under checkin
     */
    public ConfirmationController(ArrayList<BookingInformation> bookingInformationArrayList, BookingInformation bookingInfo){
        bookingInformationList=bookingInformationArrayList;
        bookingInformation=bookingInfo;
        confirmationView  =new ConfirmationView();
        setTable();

    }

    /**
     * lunch page method
     */
    public void startPage(){
        JFrame frame =new JFrame("British Airways");
        frame.setSize(525,380);
        frame.getContentPane().add(confirmationView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        confirmationView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton eventSource = (JButton) e.getSource();

                if (eventSource.equals(confirmationView.button1))            //back
                {
                    //hid and dispose the page
                    frame.setVisible(false);
                    frame.dispose();
                    startLastPage();
                }else if(eventSource.equals(confirmationView.button2))
                {
                    if(confirmationView.checkbox1.isSelected()){             //confirm the checkin
                        frame.setVisible(false);
                        frame.dispose();
                        try {
                            FileReaderWriter.passDatatoPrinter(bookingInformation);
                            FileReaderWriter.setCheckinStatus(bookingInformation);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    else{
                        System.out.println("please check the checkbox");//!
                    }

;
                }
            }
        });
    }
    @Override
    public void startLastPage() {
        new AdditionalServiceController(bookingInformationList,bookingInformation).startPage();
    }

    /**
     * Use this method to set the content of the table,show all the user's checkin related information
     */
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
