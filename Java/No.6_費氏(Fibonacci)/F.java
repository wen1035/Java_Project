import java.util.Scanner;
public class F{
    public static void main(String[] args){
        int sum=1,n=0;
		Scanner scanner = new Scanner(System.in);
		System.out.print("請輸入數字:");
		n = scanner.nextInt();
		int num[]=new int[n];
		num[0]=1;
		num[1]=1;
		for(int i=2;i< n;i++){
			num[i]=num[i-1]+num[i-2];
			if(i+1==50)//第50項數字結果
			{
				System.out.println(num[i]);
			}
		}
		
	
    }
}