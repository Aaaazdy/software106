package Controller;

import Boundary.FlightInformationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlightInformationController implements Controller{
    FlightInformationView flightInformationView;

    public FlightInformationController(){
        flightInformationView = new FlightInformationView();
    }

    public void startPage(){
        JFrame frame =new JFrame("British Airways");
        frame.setSize(1280,720);
        frame.getContentPane().add(flightInformationView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        flightInformationView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton eventSource = (JButton) e.getSource();

                if (eventSource.equals(flightInformationView.button1))            //back
                {

                    //close this page and go to last page
                    frame.setVisible(false);
                    frame.dispose();
                    startLastPage();

                }else if(eventSource.equals(flightInformationView.button2))
                {
                    //close this page and go to next page
                    frame.setVisible(false);
                    frame.dispose();
                    startNextPage();
                }
            }
        });

    }
    @Override
    public void startNextPage() {

    }
    @Override
    public void startLastPage() {

    }
}
