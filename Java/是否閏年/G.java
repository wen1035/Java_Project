import java.util.*;
  
public class G{
 
   public static void main(String[] args) {
      String months[] = {
      "Jan", "Feb", "Mar", "Apr",
      "May", "Jun", "Jul", "Aug",
      "Sep", "Oct", "Nov", "Dec"};
      
      int year;
     
      GregorianCalendar g = new GregorianCalendar();
     
      System.out.print("Date: ");
      System.out.print(months[g.get(Calendar.MONTH)]);
      System.out.print(" " + g.get(Calendar.DATE) + " ");
      System.out.println(year = g.get(Calendar.YEAR));
      System.out.print("Time: ");
      System.out.print(g.get(Calendar.HOUR) + ":");
      System.out.print(g.get(Calendar.MINUTE) + ":");
      System.out.println(g.get(Calendar.SECOND));
      
     
      if(g.isLeapYear(year)) {
         System.out.println("當年是閏年");
      }
      else {
         System.out.println("當年不是閏年");
      }
   }
}