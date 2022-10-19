class Test {
    public static void main(String[] args) {
	Dog d= new Dog();
        d.Bark();
        GoldenRetriever  g= new GoldenRetriever();//因為自己有自定義所以跑自己的
        g.Bark();
	d.toString();
	g.toString();
        
    }
}