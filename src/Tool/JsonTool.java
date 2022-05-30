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
    }

    
    /** 
     * @param tmpPath
     * @return BookingInformation
     * @throws IOException
     */
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
        return gson.fromJson(jsonString, BookingInformation.class);
    }



}

