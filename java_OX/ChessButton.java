import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessButton extends MouseAdapter 
{
	private ChessBoard frame;
	private int i;
	public ChessButton(ChessBoard frame,int i) 
	{
		this.frame =frame;
		this.i=i;
	}

	public void mouseClicked(MouseEvent e) 
	{
		if(frame.board[i/3][i%3]==0)//判斷是否空棋盤 
		{
			if(frame.flag==1)//到哪個玩家
			{
				frame.board[i/3][i%3]=1;//改變對應二维的值
				frame.btn[i].setLabel("O");//输出棋子
				frame.flag=2;
				frame.p1++;//次數
				frame.txt.setText("當前玩家:Player2");
				frame.player1.add(new Player(i/3,i%3));
			}
			else if(frame.flag==2) 
			{
				frame.board[i/3][i%3]=2;
				frame.btn[i].setLabel("X");
				frame.flag=1;
				frame.p2++;
				frame.txt.setText("當前玩家:Player1");
				frame.player2.add(new Player(i/3,i%3));
			}
		}
		super.mouseClicked(e);
	}
	
}
