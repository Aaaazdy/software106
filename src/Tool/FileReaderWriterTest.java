package Tool;

import Entity.BookingInformation;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

class FileReaderWriterTest {

    
    /** 
     * @throws IOException
     */
    void searchBookingNo() throws IOException {
        ArrayList<BookingInformation> bookingInfo=new ArrayList<BookingInformation>();
        boolean x1=FileReaderWriter.searchBookingNo(123456,bookingInfo);
        boolean x2=FileReaderWriter.searchBookingNo(654321,bookingInfo);
        boolean x3=FileReaderWriter.searchBookingNo(111111,bookingInfo);
        assertEquals(true,x1);
        assertEquals(true,x2);
        assertEquals(false,x3);
        assertEquals("FF960",bookingInfo.get(0).getFlightNumber());
        assertEquals("AC1270",bookingInfo.get(1).getFlightNumber());

    }

    
    /** 
     * @throws IOException
     */
    void searchBooingInfo() throws IOException {
        ArrayList<BookingInformation> bookingInfo=new ArrayList<BookingInformation>();
        boolean x1=FileReaderWriter.searchBooingInfo("Shelby","123123123",bookingInfo);
        boolean x2=FileReaderWriter.searchBooingInfo("Shelby","111111111",bookingInfo);
        assertEquals(true,x1);
        assertEquals(false,x2);
        assertEquals("FF960",bookingInfo.get(0).getFlightNumber());
        assertEquals("AC1270",bookingInfo.get(1).getFlightNumber());
    }

}