import java.util.Scanner;
import java.util.*;
public class N_Matrix {
	public static void main(String[] args) {
		int i,j,n;
		Scanner scanner = new Scanner(System.in);
		System.out.print("請輸入數字:");
		n = scanner.nextInt();
		int A[][]; 
		A = new int[n][n];
		int B[][]; 
		B = new int[n][n];
		int C[][]; 
		C = new int[n][n];
		Random random = new Random();
		//int ran1 = random.nextInt(21);
		
		for(i=0; i<C.length; i++)
		{
		   for(j=0; j<C[i].length; j++) //這此運用到取得第i列的元素個數
		    {
				A[i][j] = random.nextInt(21);
				B[i][j] = random.nextInt(21);
				C[i][j] = A[i][j]+B[i][j];
				  
		    }
		}
		System.out.println("A陣列:");
		for(i=0; i<A.length; i++)
		{
		   for(j=0; j<A[i].length; j++) //這此運用到取得第i列的元素個數
			{
				if(A[i][j]<10)
					System.out.print(" ");
				System.out.print(A[i][j]+" ");
			}
			System.out.print("\n");
		}
		System.out.println("B陣列:");
		for(i=0; i<B.length; i++)
		{
		   for(j=0; j<B[i].length; j++) //這此運用到取得第i列的元素個數
			{
				if(B[i][j]<10)
					System.out.print(" ");
				System.out.print(B[i][j]+" ");
			}
			System.out.print("\n");
		}
		System.out.println("C陣列:");
		for(i=0; i<C.length; i++)
		{
		   for(j=0; j<C[i].length; j++) //這此運用到取得第i列的元素個數
			{
				if(C[i][j]<10)
					System.out.print(" ");
				System.out.print(C[i][j]+" ");
			}
			System.out.print("\n");
		}
	}

	
}