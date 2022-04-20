package Controller;

import Boundary.WelcomeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeController implements Controller{
    private WelcomeView welcomeView;

    public WelcomeController(){
        welcomeView=new WelcomeView();
    }
    public void startPage(){
        JFrame frame =new JFrame("British Airways");
        frame.setSize(1280,720);
        frame.getContentPane().add(welcomeView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        welcomeView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton eventSource =(JButton) e.getSource();
                if(eventSource.equals(welcomeView.button)){
                    //close and start next page
                    frame.setVisible(false);
                    frame.dispose();
                    startNextPage();
                }
            }
        });
    }
   @Override
    public void startNextPage() {
        new CheckinController().startPage();
    }
}
