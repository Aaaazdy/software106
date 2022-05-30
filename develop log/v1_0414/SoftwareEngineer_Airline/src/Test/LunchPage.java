package Test;
import Boundary.Confirmation;
import Boundary.Creditcard;
import Boundary.MealSelect;
import Entity.BookingInformation;

public class LunchPage {
	
  /** 
   * @param args
   */
  public static void main(String[] args) {
		BookingInformation c1=new BookingInformation();
		new MealSelect(c1).setVisible(true);//construct the first page.
	}
}
