class Cute extends Cat{
    String name;
    String color;
    int age;
    static int totalCount = 0;
    Cute(){
        name = "cute1";
	color = "white";
        age = 2;  
        //totalCount++;
    }
    Cute(String theName, String theColor, int theAge)
    {
	this();                          
        this.name = theName;
	this.age = theAge;
	this.color = theColor;
	
    }
    void meow(){
	System.out.println("ha!");
    }
    void print_1(){
        System.out.println(name+" 顏色:"+color+" 年齡:"+age);
    }
}

