import java.util.Date;
import java.time.*;
import java.util.Scanner;
import java.util.*;
public class TimeNow {
    public static void main(String[] args) {
		System.out.print("輸入:");
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String str1 = sc.next();
		System.out.println("輸出:");
		
		//年月日
		//Date date = new Date();
		//System.out.println("date = " + date);
		LocalDate today = LocalDate.now();
		int year = today.getYear(); 
		int month = today.getMonthValue(); 
		int day = today.getDayOfMonth(); 
		System.out.printf("年: %d \n月: %d \n日: %d \t %n \n", year, month, day); 
		
		//星期
		Date date=new Date();  
		
        //A的使用，星期全称  
        System.out.printf("星期幾:%tA%n\n",date);  
		
		//時分秒
		GregorianCalendar g = new GregorianCalendar();
		System.out.print("時:");
		System.out.print(g.get(Calendar.HOUR) + "\n");
		System.out.print("分:");
        System.out.print(g.get(Calendar.MINUTE) + "\n");
		System.out.print("秒:");
        System.out.println(g.get(Calendar.SECOND) + "\n");
		

    }
}