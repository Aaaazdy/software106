package Entity;

import java.util.LinkedList;

public class BookingInformation{

    private String filePath;
    private String flightNo;
    private String startWhere;
    private String destWhere;
    private String duringTime;
    private String firstName;
    private String lastName;
    private String idNo;
    private int BookingNo;
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
    public BookingInformation(){
        firstName = "firstName";
        lastName = "lastName";
        flightNumber = "AC1234";
        boardingGate = "20";
        seat = "12A";
        primaryFood = "box lunch";
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public String getFlightNumber()
    {
        return flightNumber;
    }
    public void setFlightNumber(String flightNumber)
    {
        this.flightNumber = flightNumber;
    }
    public String getBoardingTime()
    {
        return boardingTime;
    }
    public void setBoardingTime(String boardingTime)
    {
        this.boardingTime = boardingTime;
    }

    public String getBoardingGate()
    {
        return boardingGate;
    }

    public void setBoardingGate(String boardingGate)
    {
        this.boardingGate = boardingGate;
    }

    public String getSeat()
    {
        return seat;
    }

    public void setSeat(String seat)
    {
        this.seat = seat;
    }

    public String getPrimaryFood()
    {
        return primaryFood;
    }

    public void setPrimaryFood(String primaryFood)
    {
        this.primaryFood = primaryFood;
    }

    public String getCredFirst()
    {
        return credFirst;
    }

    public void setCredFirst(String credFirst)
    {
        this.credFirst = credFirst;
    }

    public String getCredSecond()
    {
        return credSecond;
    }

    public void setCredSecond(String credSecond)
    {
        this.credSecond = credSecond;
    }

    public String getCredNumber()
    {
        return credNumber;
    }

    public void setCredNumber(String credNumber)
    {
        this.credNumber = credNumber;
    }

    public String getSecurCode()
    {
        return securCode;
    }

    public void setSecurCode(String securCode)
    {
        this.securCode = securCode;
    }

    public void setExtraServiceFee(int a)
    {
        extraServiceFee=a;
    }
    public int getExtraServiceFee()
    {
        return extraServiceFee;
    }
    public void setExtraService(String item)
    {
        extraService.add(item);
    }

    public String getFlightNo() {
        return flightNo;
    }

    public String getStartWhere() {
        return startWhere;
    }

    public String getDestWhere() {
        return destWhere;
    }

    public String getDuringTime() {
        return duringTime;
    }

    public int getBookingNo() {
        return BookingNo;
    }
    public String getExtraService()
    {
        String retString = "";
        for (String x : this.extraService)
        {
            retString += (x + ",");
        }
        return retString;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public void setStartWhere(String startWhere) {
        this.startWhere = startWhere;
    }

    public void setDestWhere(String destWhere) {
        this.destWhere = destWhere;
    }

    public void setDuringTime(String duringTime) {
        this.duringTime = duringTime;
    }

    public void setBookingNo(int bookingNo) {
        BookingNo = bookingNo;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
