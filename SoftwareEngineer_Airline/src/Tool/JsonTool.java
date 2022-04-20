package Tool;

import Entity.BookingInformation;
import com.google.gson.Gson;

import java.io.*;

/**
 * @author Aaaazdy
 */
public class JsonTool
{
    public JsonTool( )
    {
        Gson gson = new Gson();
        try
        {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(".\\BookingInformation.txt"));
        } catch (Exception e)
        {

        }

    }

    public BookingInformation createBookingInfo(String tmpPath) throws IOException {

        FileInputStream fin = new FileInputStream(tmpPath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";
        String jsonString="";
        while((strTmp = buffReader.readLine())!=null){
                jsonString+=strTmp;
        }
        buffReader.close();

        Gson gson = new Gson();
        System.out.println(jsonString);
        return gson.fromJson(jsonString, BookingInformation.class);
    }
/*    public static void main(String args[]) throws IOException {
        String filePath = "data//bookingInfo//passenger231.txt";
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";
        String jsonString="";
        while((strTmp = buffReader.readLine())!=null){
            if(strTmp.equals("{")&&strTmp.equals("}")){
                continue;
            }
            else{
                jsonString+=strTmp;
            }
        }
        buffReader.close();
    }*/



}

