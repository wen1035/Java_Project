/*import java.Cat;
import java.Cute;
import java.Beauty;*/

class Test {
    public static void main(String[] args) {
	Cat cat = new Cat();
        cat.meow();
        Cute cute = new Cute();//因為自己有自定義所以跑自己的
        cute.meow();
        Beauty b = new Beauty();
        b.meow();
    }
}