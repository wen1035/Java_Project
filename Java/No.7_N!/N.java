import java.util.Scanner;
public class N{
    public static void main(String[] args){
		int sum=1,n=0;
		Scanner scanner = new Scanner(System.in);
		System.out.print("請輸入數字:");
		n = scanner.nextInt();
		for(int i=1;i<=n;i++){
			sum*=i;
		}
		System.out.println(sum);
    }
}