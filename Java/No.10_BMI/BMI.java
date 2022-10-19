import java.util.Scanner;
public class BMI{
    public static void main(String[] args){
		System.out.println("請輸入:");
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String str1 = sc.next();
		int m1 = sc.nextInt();
		int kg1 = sc.nextInt();
		double m = m1;
		double kg = kg1;
		double sum=0.0;
		double j=0.0;
		m/=100;
		sum=kg/(m*m);
		if(sum<18.5)
		{
			System.out.println("過輕");
		}
		if(sum>=18.5 && sum<24.0)
		{
			System.out.println("標準體重");
		}
		if(sum>=24.0 && sum<27.0)
		{
			System.out.println("過重");
		}
		if(sum>27.0)
		{
			System.out.println("肥胖");
		}
		
		
    }
}