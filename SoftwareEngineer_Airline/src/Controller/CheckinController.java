package Controller;

import Boundary.CheckinView;
import Entity.BookingInformation;
import Tool.JsonTool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
public class CheckinController implements Controller{
    private CheckinView checkinView;


    public  ArrayList<BookingInformation> bookingInfoList;


    public CheckinController(){
        checkinView=new CheckinView();
        bookingInfoList=new ArrayList<BookingInformation>();
    }

    public void startPage(){
        JFrame frame =new JFrame("British Airways");
        frame.setSize(1280,720);
        frame.getContentPane().add(checkinView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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

                    String input=checkinView.textField.getText();
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
                            valid=searchBookingNo(inputNo);
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
                        valid=searchBookingNo(inputNo);
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
    public void startIDCardCheckPage(){
        new IDNoCheckinController().startPage();
    }

    /**
     * Search and tell if the input bookingNo exist,if it exists, generate the entity object
     * @param inputBookingNo
     * @return
     */
    public  boolean searchBookingNo(int inputBookingNo) throws IOException {
        String path="data//bookingInfo";
        File file = new File(path);
        File[] files = file.listFiles();
        boolean found=false;
        ArrayList<String> foundPath=new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            String filePath=files[i].getPath();
            FileInputStream fin = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(fin);
            BufferedReader buffReader = new BufferedReader(reader);
            String strTmp = "";
            while((strTmp = buffReader.readLine())!=null){
                if(strTmp.contains("bookingNo")){
                    String[] tmpStr1=strTmp.split(":");
                    String[] tmpStr2=tmpStr1[1].split(",");
                    int tmpBookingNo=Integer.parseInt(tmpStr2[0]);
                    if(tmpBookingNo==inputBookingNo){
                        found=true;
                        break;
                    }
                }
            }
            buffReader.close();
            if(found){
                foundPath.add(filePath);
                found=false;
            }
        }
        if(foundPath.size()==0){
            return false;
        }
        else{
            //creat objects
            JsonTool tool=new JsonTool();
            for(String tmpPath:foundPath){
                System.out.println(tmpPath);
                bookingInfoList.add(tool.createBookingInfo(tmpPath));
            }
            return true;
        }
    }
}
