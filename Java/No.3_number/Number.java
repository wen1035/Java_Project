class Number{
    
    Number(){
        System.out.println("無輸入參數, 0");
    }
    Number(int n){
	 System.out.println("輸入一個整數參數, 1");
    }
    Number(int n, int n1){
	 System.out.println("輸入兩個整數參數, 2");
    }
    Number(int n, int n1, int n2){
	 System.out.println("輸入三個整數參數, 3");
    }
    Number(double n){
	 System.out.println("輸入一個double參數, 1");
    }
    Number(double n, double n1){
	 System.out.println("輸入兩個double參數, 2");
    }
    Number(double n, double n1, double n2){
	 System.out.println("輸入三個double參數, 3");
    }	
   
}