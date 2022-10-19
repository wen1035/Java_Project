import java.util.Date;
import java.time.*;
import java.util.Scanner;
import java.util.*;
import java.text.*;
import java.util.Calendar;
/*
public class Time {
    public static void main(String[] args)throws ParseException {
		Date now=new Date();
		System.out.print("輸入:");
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String str1 = sc.next();
		String str2 = sc.next();
		System.out.println("輸出:");
		
		//String time = "3:30 PM";

		SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm:ss a");
	
		SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm:ss");
		 Date g = date12Format.parse(str2);
		String c = date24Format.format(g);
        System.out.println(c);
		//System.out.println(date24Format.format(date12Format.parse(str2)));

		
		
    }
}*/

public class Time {
    public static void main(String[] args) throws ParseException {
        Date date = new Date();
		Calendar   date   =   Calendar.getInstance();   
        date.get(Calendar.HOUR_OF_DAY   );//得到24小时机制的   
        date.get(Calendar.HOUR);//   得到12小时机制的   
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
		SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm:ss");
        String dateFormat = sdf.format(date);
		String time = "3:30:10 PM";
		Date g = sdf.parse(time);
        //Date dateFormatParse = sdf.parse(dateFormat);
		//String dateFormat = sdf.format(g);
		String c = date24Format.format(g);
        System.out.println(c);
		String a = sdf.format(g);
        System.out.println(a);
        /*System.out.println("date: "+date);
        System.out.println("date.toString: "+date.toString());
        System.out.println("g: "+g);
        System.out.println("dateFormatParse "+dateFormatParse);*/
    }
}