
/* 請您幫他建立一套『菜單價目表』（Menu）,

   請製作一個菜單項目 Item，再產生 Menu 物件陣列

   時立即並給予下列初值內容，再依照設計格式印出價目表）*/
import java.util.StringTokenizer;
import java.io.StreamTokenizer;
import java.io.IOException;
import java.util.Scanner;
class Item {

    String name;

}

public class M1{

    static Item[] Menu = new Item[100];
	static int Item_choice = 0; 
    public static void main(String[] args) {
		
        // 給予菜單初值
		
        String name[] = {"長方形(rectangles)\t","直角三角形(right_triangle)",
							"等腰三角形(isosceles_triangle)","等邊三角形(equilateral_shape)",
							"正方形(square)\t\t","圓形(round)\t\t","梯型(ladder_type)\t"

                        };

        for (int i=0; i<name.length; i++) {

			Menu[i] = new Item();
			Menu[i].name = name[i];

		}

		System.out.printf("圖形:\n");

		for (int i=0; i<name.length; i++) {

			System.out.printf("%s\t", Menu[i].name);
			
			System.out.print("\n");
		

		}
		String Rectangles = "rectangles";
        String Right_triangle = "right_triangle";
		String Isosceles_triangle = "isosceles_triangle";
        String Equilateral_shape = "equilateral_shape";
		String Square = "square";
        String Ladder_type = "ladder_type";
		String Round = "round";
		Scanner sc = new Scanner(System.in);  // 聲稱Scanner物件
		String[] temp;
		String[] temp1;
		
		String str = sc.next();
		String str1 = sc.next();
		int n1 =sc.nextInt();
		int n2=0;
		System.out.println("\n");
		
		if(str1.equals(Rectangles))
		{
			n2 =sc.nextInt();
			for(int i=0;i<n2;i++)
			{
				for(int j=0;j<n1;j++)
				{
					System.out.print("* ");
				}
				System.out.println("\n");
			}
			int sum=n1*n2;
			System.out.println("面積:"+sum);
		}
		
		if(str1.equals(Right_triangle))
		{
			int u=n1-1;
			n2 =sc.nextInt();
			for(int i=0;i<n2;i++)
			{
				for(int k=0;k< u;k++)
				{
					System.out.print(" ");
					System.out.print(" ");
				}
				for(int j=0;j<n1-u;j++)
				{
					System.out.print("* ");
				}
				System.out.println("\n");
				u--;
			}
			//double
			int sum=(n1*n2)/2;
			System.out.println("面積:"+sum);
		}
		if(str1.equals(Isosceles_triangle))
		{
			int u=n1/2-1;
			n2 =sc.nextInt();        
			for(int i=0;i<n2;i++)
			{
				for(int k=0;k<= u+1;k++)
				{
					System.out.print(" ");
					System.out.print(" ");
				}
				for(int j=0;j<n1/2-u;j++)
				{
					System.out.print("* ");
					//System.out.print("* ");
				}
				for(int j=0;j<n1/2-u-1;j++)
				{
					if(i>=1)
						System.out.print("* ");
					//System.out.print("* ");
				}
				
				System.out.println("\n");
				u--;
			}
			//double
			int sum=(n1*n2)/2;
			System.out.println("面積:"+sum);
		}
		
		if(str1.equals(Equilateral_shape))
		{
			int u=n1*2-1;
			n2 =n1;
			for(int i=0;i<n2;i++)
			{
				for(int k=0;k<u-n1;k++)
				{
					System.out.print(" ");
					//System.out.print(" ");
				}
				for(int j=0;j<n1*2-u;j++)
				{
					if(j%2==0)
						System.out.print("* ");
					
				}
				for(int j=0;j<n1*2-u-1;j++)
				{
					if(i>=1)
					{
						if(j%2==0)
							System.out.print("* ");
					}//System.out.print("* ");
				}
				
				System.out.println("\n");
				u--;
			}
			//double
			int sum=(n1*n2)/2;
			System.out.println("面積:"+sum);
		}
		if(str1.equals(Round))
		{
			int u=n1*2-1;
			n2 =n1;
			for(int i=0;i<n2;i++)
			{
				for(int k=0;k<u-n1;k++)
				{
					System.out.print(" ");
					//System.out.print(" ");
				}
				for(int j=0;j<n1*2-u;j++)
				{
					if(j%2==0)
						System.out.print("* ");
					
				}
				for(int j=0;j<n1*2-u-1;j++)
				{
					if(i>=1)
					{
						if(j%2==0)
							System.out.print("* ");
					}//System.out.print("* ");
				}
				
				System.out.println("\n");
				u--;
			}
			int sum=n1*n1*3.14;
			System.out.println("面積:"+sum+);
		}
		if(str1.equals(Ladder_type))
		{
			int n3;
			int u=n1-1;
			n2 =sc.nextInt(); 
			n3 =sc.nextInt(); 
			for(int i=0;i<n3;i++)
			{
				for(int j=0;j<n1-1;j++)
				{
					System.out.print("* ");
				}
				for(int j=0;j<n1-u;j++)
				{
					System.out.print("* ");
					//System.out.print("* ");
				}
				
				
				System.out.println("\n");
				u--;
				
			}
			int sum=(n1+n2)*n3/2;
			System.out.println("面積:"+sum);
		}
    }

}