class Beauty extends Cat{
    String name;
    String color;
    int age;
    
    Beauty(){
        name = "beauty1";
	color = "black";
        age = 3;  
        
    }
     void print_2(){
        System.out.println(name+" 顏色:"+color+" 年齡:"+age);
    }
    void Smile(){
        System.out.println("^_^");
    }
    void meow(){
	System.out.println("哈哈");
    }
}