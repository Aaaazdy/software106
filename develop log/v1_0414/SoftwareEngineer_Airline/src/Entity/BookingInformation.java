package Entity;

import java.util.LinkedList;

public class BookingInformation
{

    private String firstName;
    private String lastName;
    private String flightNumber;
    private String boardingTime;
    private String boardingGate;
    private String seat;
    private String primaryFood;
    private String credFirst;
    private String credSecond;
    private String credNumber;
    private String securCode;
    private int extraServiceFee;
    private LinkedList<String> extraService = new LinkedList<String>();

    public BookingInformation()
    {
        extraService.add("coca cola");
        extraService.add("Extra legroom");
        firstName = "firstName";
        lastName = "lastName";
        flightNumber = "AC1234";
        boardingGate = "20";
        seat = "12A";
        primaryFood = "box lunch";
    }

    
    /** 
     * @return String
     */
    public String getFirstName()
    {
        return firstName;
    }

    
    /** 
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    
    /** 
     * @return String
     */
    public String getLastName()
    {
        return lastName;
    }

    
    /** 
     * @param lastName
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }


    
    /** 
     * @return String
     */
    public String getFlightNumber()
    {
        return flightNumber;
    }

    
    /** 
     * @param flightNumber
     */
    public void setFlightNumber(String flightNumber)
    {
        this.flightNumber = flightNumber;
    }

    
    /** 
     * @return String
     */
    public String getBoardingTime()
    {
        return boardingTime;
    }

    
    /** 
     * @param boardingTime
     */
    public void setBoardingTime(String boardingTime)
    {
        this.boardingTime = boardingTime;
    }

    
    /** 
     * @return String
     */
    public String getBoardingGate()
    {
        return boardingGate;
    }

    
    /** 
     * @param boardingGate
     */
    public void setBoardingGate(String boardingGate)
    {
        this.boardingGate = boardingGate;
    }

    
    /** 
     * @return String
     */
    public String getSeat()
    {
        return seat;
    }

    
    /** 
     * @param seat
     */
    public void setSeat(String seat)
    {
        this.seat = seat;
    }

    
    /** 
     * @return String
     */
    public String getPrimaryFood()
    {
        return primaryFood;
    }

    
    /** 
     * @param primaryFood
     */
    public void setPrimaryFood(String primaryFood)
    {
        this.primaryFood = primaryFood;
    }

    
    /** 
     * @return String
     */
    public String getCredFirst()
    {
        return credFirst;
    }

    
    /** 
     * @param credFirst
     */
    public void setCredFirst(String credFirst)
    {
        this.credFirst = credFirst;
    }

    
    /** 
     * @return String
     */
    public String getCredSecond()
    {
        return credSecond;
    }

    
    /** 
     * @param credSecond
     */
    public void setCredSecond(String credSecond)
    {
        this.credSecond = credSecond;
    }

    
    /** 
     * @return String
     */
    public String getCredNumber()
    {
        return credNumber;
    }

    
    /** 
     * @param credNumber
     */
    public void setCredNumber(String credNumber)
    {
        this.credNumber = credNumber;
    }

    
    /** 
     * @return String
     */
    public String getSecurCode()
    {
        return securCode;
    }

    
    /** 
     * @param securCode
     */
    public void setSecurCode(String securCode)
    {
        this.securCode = securCode;
    }

    
    /** 
     * @param a
     */
    public void setExtraServiceFee(int a)
    {
        extraServiceFee=a;
    }
    
    /** 
     * @return int
     */
    public int getExtraServiceFee()
    {
        return extraServiceFee;
    }
    
    /** 
     * @param item
     */
    public void setExtraService(String item)
    {
        extraService.add(item);
    }

    
    /** 
     * @return String
     */
    public String getExtraService()
    {
        String retString = "";
        for (String x : this.extraService)
        {
            retString += (x + ",");
        }
        return retString;
    }
}
