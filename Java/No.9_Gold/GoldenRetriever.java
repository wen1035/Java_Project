class GoldenRetriever extends Dog{
    String name;
    String color;
    int age;
    
    GoldenRetriever(){
        name = "小黃";
	color = "黃色";
        age = 3;  
        
    }
    GoldenRetriever(String theName, String theColor, int theAge)
    {
	this();                          
        this.name = theName;
	this.age = theAge;
	this.color = theColor;
	
    }
    void Smile(){
        System.out.println("^____^");
    }
    void Bark(){
	System.out.println("黃金獵犬汪～");
    }
    void toString(){
        System.out.println("我是一隻可愛的黃金獵犬！");
    }
}