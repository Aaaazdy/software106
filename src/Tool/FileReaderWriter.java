package Tool;

import Entity.BookingInformation;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileReaderWriter {
    private static String checkinDataFiles = "data//checkinData";

    private static String printerDataFiles = "data//printerData//";

    private static String flightDataFiles = "data//flightData//";




    /**
     * Write the seat selected by the user into the data of the aircraft.
     * When the subsequent user checks in, this seat will be regarded as unselectable.
     * @param bookingInformation entity object contains the passenger's information
     */
    public static void setSeatstatus(BookingInformation bookingInformation) throws IOException {
        File file = new File(flightDataFiles);
        File[] files = file.listFiles();
        ArrayList<String> newLines = new ArrayList<String>();
        String filePath="";


        for (int i = 0; i < files.length; i++) {
            if(files[i].getName().equals(bookingInformation.getFlightNumber()+".json")){
                filePath=files[i].getPath();
                for (String line : Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8)) {
                    if (line.contains("selectedSeat")) {
                        String tmpString=line.substring(0,line.length()-1)+","+bookingInformation.getSeatHelpNumber()+"\"";
                        newLines.add(tmpString);
                    }else {
                        newLines.add(line);
                    }
                }
            }
        }

        Files.write(Paths.get(filePath), newLines, StandardCharsets.UTF_8);
    }


    /**
     * set the checkin status to false
     * write the booking information to the checkin database
     * @param bookingInformation entity object contains the passenger's information
     */
    public static void setCheckinStatus(BookingInformation bookingInformation) throws IOException {
        String fileName=bookingInformation.getFilePath();
        ArrayList<String> newLines = new ArrayList<String>();
        for (String line : Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            if (line.contains("checkin")) {
                newLines.add(line.replace("false", "true"));
            }else {
                newLines.add(line);
            }
        }
        Files.write(Paths.get(fileName), newLines, StandardCharsets.UTF_8);
        newLines.remove(newLines.size()-1);
        newLines.remove(newLines.size()-1);
        newLines.add("  \"checkin\":"+"true"+",");
        newLines.add("  \"seat\":"+"\""+bookingInformation.getSeat()+"\",");
        newLines.add("  \"primaryFood\":"+"\""+bookingInformation.getPrimaryFood()+"\",");
        newLines.add("  \"credFirst\":"+"\""+bookingInformation.getCredFirst()+"\",");
        newLines.add("  \"credSecond\":"+"\""+bookingInformation.getCredSecond()+"\",");
        newLines.add("  \"credNumber\":"+"\""+bookingInformation.getCredNumber()+"\",");
        newLines.add("  \"securCode\":"+"\""+bookingInformation.getSecurCode()+"\",");
        newLines.add("  \"extraServiceFee\":"+bookingInformation.getExtraServiceFee()+",");
        newLines.add("  \"extraService\":"+"\""+bookingInformation.getExtraService()+"\"");
        fileName=bookingInformation.getFilePath().replace("checkinData","passengerData");
        newLines.add("}");
        Files.write(Paths.get(fileName), newLines, StandardCharsets.UTF_8);
    }


    /**
     * write the booking information to the printer
     * @param bookingInformation entity object contains the passenger's information
     */
    public static void passDatatoPrinter(BookingInformation bookingInformation) throws IOException{
        String path=printerDataFiles;
        path+=bookingInformation.getBookingNo()+".txt";
        BufferedWriter bw= new BufferedWriter(new FileWriter(path));
        bw.write("Name:"+bookingInformation.getFirstName() + " " + bookingInformation.getLastName());
        bw.newLine();
        bw.write("Flight number:"+bookingInformation.getFlightNumber());
        bw.newLine();
        bw.write("Boarding gate:"+bookingInformation.getBoardingGate());
        bw.newLine();
        bw.write("Seat:"+ bookingInformation.getSeat());
        bw.newLine();
        bw.write("Departure:"+bookingInformation.getStartWhere());
        bw.newLine();
        bw.write("Destination:"+bookingInformation.getDestWhere());
        bw.newLine();
        bw.write("During Time:"+bookingInformation.getDuringTime());
        bw.newLine();
        bw.close();
    }


    /**
     * Use the entered predetermined number to find the files in the database, and write all the matching results into the list.
     * @param inputBookingNo the user input booking number
     * @param bookingInfoList entity objects list to hold the information
     * @return whether found the booking number in the database
     * @throws IOException
     */
    public static boolean searchBookingNo(int inputBookingNo,ArrayList<BookingInformation> bookingInfoList) throws IOException {
        File file = new File(checkinDataFiles);
        File[] files = file.listFiles();
        boolean found=false;
        ArrayList<String> foundPath=new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            String filePath=files[i].getPath();
            FileInputStream fin = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(fin);
            BufferedReader buffReader = new BufferedReader(reader);
            String strTmp = "";
            boolean same=false;
            while((strTmp = buffReader.readLine())!=null){
                if(strTmp.contains("BookingNo")){
                    String[] tmpStr1=strTmp.split(":");
                    String[] tmpStr2=tmpStr1[1].split(",");
                    int tmpBookingNo=Integer.parseInt(tmpStr2[0]);
                    if(tmpBookingNo==inputBookingNo){
                        same=true;
                    }
                }
                if(strTmp.contains("checkin")){
                    if(strTmp.contains("false")){ //If it has not been checkin
                        if(same){
                            found=true;
                        }
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
                bookingInfoList.add(tool.createBookingInfo(tmpPath));
                bookingInfoList.get(bookingInfoList.size()-1).setFilePath(tmpPath);
            }
            return true;
        }
    }

    /**
     * According to the family name and ID number, and write the result into the list.
     * @param inputSurName the user input name
     * @param inputIDno    the user input IDno
     * @param bookingInfoList entity objects list to hold the information
     * @return whether found the booking number in the database
     * @throws IOException
     */
    public static boolean searchBooingInfo(String inputSurName,String inputIDno,ArrayList<BookingInformation> bookingInfoList) throws IOException {
        File file = new File(checkinDataFiles);
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
                    if(tmpStr1[1].substring(1,tmpStr1[1].length()-2).equals(inputSurName)){
                        surnameSame=true;
                    }
                }
                if(strTmp.contains("idNo")){
                    String[] tmpStr1=strTmp.split(":");
                    if(tmpStr1[1].substring(1,tmpStr1[1].length()-2).equals(inputIDno)){
                        idNoSame=true;
                    }
                }
                if(strTmp.contains("checkin")){
                    if(!strTmp.contains("false")){ //If it has  been checkin
                        if(surnameSame&&idNoSame){
                            surnameSame=false;
                            idNoSame=false;
                        }
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
                bookingInfoList.add(tool.createBookingInfo(tmpPath));
                bookingInfoList.get(bookingInfoList.size()-1).setFilePath(tmpPath);
            }
            return true;
        }
    }
    /**
     * Enter the flight number, check the seat status of this flight, and write the result into the array.
     * @param inputFlightNo the flight number to search
     * @throws IOException
     */
    public static void searchFlightInfo(String inputFlightNo,int[] seatStatus) throws IOException {
        File file = new File(flightDataFiles);
        File[] files = file.listFiles();
        ArrayList<String> foundPath=new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            if(files[i].getName().equals(inputFlightNo+".json")){
                String filePath=files[i].getPath();
                FileInputStream fin = new FileInputStream(filePath);
                InputStreamReader reader = new InputStreamReader(fin);
                BufferedReader buffReader = new BufferedReader(reader);
                String strTmp = "";
                while((strTmp = buffReader.readLine())!=null){
                    if(strTmp.contains("selectedSeat")){
                        String[] tmpStr1=strTmp.split(":");
                        String[] tmpStr2=tmpStr1[1].substring(1,tmpStr1[1].length()-1).split(",");
                        for(String x:tmpStr2){
                            seatStatus[Integer.parseInt(x)]=1;
                        }
                     }
                }
                buffReader.close();
            }
        }
    }

    /**
     * Use the flight number to check the aircraft model and return the aircraft model.
     * @param inputFlightNo
     * @return  The model of the aircraft, 1 represents A220 , 2 represents A320
     * @throws IOException
     */
    public static int getPlaneModel(String inputFlightNo) throws IOException {
        File file = new File(flightDataFiles);
        File[] files = file.listFiles();
        ArrayList<String> foundPath=new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            if(files[i].getName().equals(inputFlightNo+".json")){
                String filePath=files[i].getPath();
                FileInputStream fin = new FileInputStream(filePath);
                InputStreamReader reader = new InputStreamReader(fin);
                BufferedReader buffReader = new BufferedReader(reader);
                String strTmp = "";
                while((strTmp = buffReader.readLine())!=null){
                    if(strTmp.contains("planeModel")){
                        String[] tmpStr1=strTmp.split(":");
                        String modelString=tmpStr1[1].substring(1,tmpStr1[1].length()-2);
                        if(modelString.equals("A220")){
                            return 1;
                        }
                        else if(modelString.equals("A320")){
                            return 2;
                        }
                    }
                }
                buffReader.close();
            }
        }
        return 1;
    }

}

