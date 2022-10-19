import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ChessBoard extends Frame 
{
	public TextField txt;//提示文本（顯示當前的玩家）
	public int flag=1,p1=0,p2=0;
	public int[][] board= new int[3][3];
	public Button[] btn=new Button[9];
	public ArrayList<Player> player1=new ArrayList<>();
	public ArrayList<Player> player2=new ArrayList<>();//建立玩家的集合
	public ChessBoard(String title) {
		super(title);
		init();
	}
	private void init() {
		this.setBounds(100,100,300,340);
		txt=new TextField();
		this.add(txt, BorderLayout.SOUTH);
		
		txt.setText("當前玩家:Player1");
		TextField name=new TextField();
		this.add(name, BorderLayout.NORTH);
		
		name.setText("Player1:O      Player2:X ");
		Panel panel=new Panel();
		GridLayout gl=new GridLayout(3,3,1,1);
		panel.setLayout(gl);
		this.add(panel);
		
		//建立棋盤
		for(int i=0;i<btn.length;i++) {
			btn[i]=new Button();
			panel.add(btn[i]);
		}
		
		//棋子打印出来
		for(int i=0;i<btn.length;i++) {
				btn[i].addMouseListener(new ChessButton(this,i));
		}
		new P1(this);
		new P2(this);
		
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{
				ChessBoard.this.dispose();
			}
		});//關閉遊戲
	}
}

