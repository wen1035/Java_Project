import static java.lang.System.out;
import java.util.*;
import java.util.Scanner;
public class P {
    private List<List<Integer>> rows = new ArrayList<>();
    
    P(int height) {
        for(int r = 0; r < height; r++) {
            rows.add(createRow(r));
        }
    }
    
    int combi(int r, int n) {
        return rows.get(r).get(n);
    }
    
    private List<Integer> createRow(int r){
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for(int n = 1; n <= r; n++) {
            row.add(row.get(n - 1) * (r - n + 1) / n);
        }
        return row;
    }
    
    public static void main(String[] args) {
		int sum=0;
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String str1 = sc.next();
		sum = sc.nextInt();
		int s=0,s1=0;
        final int HEIGHT = sum;
        P p = new P(HEIGHT);
        for(int r = 0; r < HEIGHT; r++) {
			s++;
            out.printf(String.format("%%%ds", (HEIGHT - r) * 3), "");
            for(int n = 0; n <= r; n++) {
                out.printf("%6d", p.combi(r, n));
				if(s==sum)
				{
					s1+=p.combi(r, n);
				}
            }
            out.println();
			
        }
		System.out.println("Sum:");
		System.out.println(s1);
    }
}
