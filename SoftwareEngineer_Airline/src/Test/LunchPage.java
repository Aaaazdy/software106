package Test;
import Boundary.Creditcard;
import Entity.BookingInformation;

public class LunchPage {
	public static void main(String[] args) {
		BookingInformation c1=new BookingInformation();
		new Creditcard(c1).setVisible(true);//construct the first page.
	}
}
