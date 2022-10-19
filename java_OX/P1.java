public class P1 extends Thread {
	private ChessBoard frame;
	private boolean result=false;//判斷输赢
	private int[] count=new int[4];

	public P1(ChessBoard frame) {
		this.frame = frame;
		start();
	}
	public void run() 
	{
		while(true)//是否赢了
		{
			synchronized("")
			{
				for(int i=0;i<3;i++) 
				{
					for(Player p:frame.player1)
					{
						if(p.getX()==p.getY())//左斜
							count[0]++;
						if(p.getX()+p.getY()==2)//右斜
							count[1]++;
						if(p.getX()==i)//横行
							count[2]++;
						if(p.getY()==i)//縱列
							count[3]++;
					}
					for(int j=0;j<4;j++) 
					{
						if(count[j]==3)
						{
							result=true;
						}
						count[j]=0;
					}
				}
				if(result)
				{
					frame.txt.setText("Player1勝！");//输出结果
					for(int i=0;i<3;i++) 
					{
						for(int j=0;j<3;j++)
							frame.board[i][j]=1;//停止落棋
					}
					result=false;
				}
			}
			
		}
	}
}
