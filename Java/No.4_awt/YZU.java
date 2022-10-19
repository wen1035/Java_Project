import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class YZU {
    public static void main(String[] args) {
        Frame frame = new Frame("Our TAs are the best.");
        Button button1 = new Button("button1");
		Button button2 = new Button("button2");
		Button button3 = new Button("button3");
		Panel panel=new Panel();
		TextField textField = new TextField();
        textField.setText("I love YZU.");
        textField.setVisible(true);
        frame.setSize(500,500);
		
		//單選按鈕 
		JRadioButton j1, j2, j3;    
		ButtonGroup bg;
		j1 = new JRadioButton("option1");  
        j2 = new JRadioButton("option2");
		j3 = new JRadioButton("option3");
		bg = new ButtonGroup();  
        bg.add(j1);  
        bg.add(j2);
		bg.add(j3);
		
        //設定佈局
        frame.setLayout(null);
        //視窗可見
        frame.setVisible(true);
        //設定流式佈局
        frame.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(j1);
		panel.add(j2);
		panel.add(j3);
        panel.add(button1);
	    panel.add(button2);
	    panel.add(button3);
        panel.add(textField);

        frame.add(panel);


		frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //結束程式
                System.exit(0);
            }
        });




    }
}