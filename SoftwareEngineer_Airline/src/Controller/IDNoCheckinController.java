package Controller;

import Boundary.IDNoCheckinView;
import Entity.BookingInformation;
import Tool.JsonTool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class IDNoCheckinController implements Controller{
    IDNoCheckinView idNoCheckinView;

    public ArrayList<BookingInformation> bookingInfoList;

    public IDNoCheckinController(){
        idNoCheckinView=new IDNoCheckinView();
        bookingInfoList=new ArrayList<BookingInformation>();
    }

    public void startPage(){
        JFrame frame =new JFrame("British Airways");
        frame.setSize(1280,720);
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
                        valid=searchBooingInfo(inputSurname,inputIDNumber);
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
    private boolean searchBooingInfo(String inputSurName,String inputIDno) throws IOException {
        String path="data//bookingInfo";
        File file = new File(path);
        File[] files = file.listFiles();
        ArrayList<String> foundPath=new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            String filePath=files[i].getPath();
            FileInputStream fin = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(fin);
            BufferedReader buffReader = new BufferedReader(reader);
            String strTmp = "";
            boolean surnameSame=false;
            boolean idNoSame=false;
            while((strTmp = buffReader.readLine())!=null){
                if(strTmp.contains("lastName")){
                    String[] tmpStr1=strTmp.split(":");
                    //System.out.println(tmpStr1[1].substring(1,tmpStr1[1].length()-2));
                    if(tmpStr1[1].substring(1,tmpStr1[1].length()-2).equals(inputSurName)){
                        surnameSame=true;
                    }
                }
                if(strTmp.contains("idNo")){
                    String[] tmpStr1=strTmp.split(":");
                    //System.out.println(tmpStr1[1].substring(1,tmpStr1[1].length()-2));
                    if(tmpStr1[1].substring(1,tmpStr1[1].length()-2).equals(inputIDno)){
                        idNoSame=true;
                    }
                }
            }
            buffReader.close();
            if(surnameSame==true&&idNoSame==true){
                foundPath.add(filePath);
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
