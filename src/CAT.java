import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CAT extends JFrame {
	private Timer timer = new Timer(33, new TimerListener());
	public static boolean arrayPanel[][];
	public static Object playerArray[] = new Object[2];
	public static JProgressBar hp1 = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
	public static JProgressBar hp2 = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
	public static Panel_Player player;
	public static Panel_Player2 player2;
	
	public CAT()
	{
		this.setTitle("Cat Shooter Game");
		this.setFocusable(true);
		this.setSize(1380,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		
		setContentPane(new JLabel(new ImageIcon("BG.png")));
						
		arrayPanel = new boolean[getWidth()+1][getHeight()+1];
		for (int i = 0; i < getWidth(); i++){
			for (int j = 0; j < getHeight(); j++){
				arrayPanel[i][j] = false;
			}
		}		
					
		int x=getWidth();
		int y =getHeight();
		//All platforms
		addsetObstacle(x/15*3,y/10*8,x/15*3,y/10*1);
		addsetObstacle(x/15*9,y/10*8,x/15*3,y/10*1);
		addsetObstacle(x/15*7,y/10*7,x/15*1,y/10*3);
		addsetObstacle(x/15*6,y/10*6,x/15*3,y/10*1);
		addsetObstacle(x/15*7,y/10*5,x/15*1,y/10*1);
		addsetObstacle(x/15*3,y/10*5,x/15*1,y/10*1);
		addsetObstacle(x/15*11,y/10*5,x/15*1,y/10*1);
		addsetObstacle(x/15*2,y/10*4,x/15*1,y/10*1);
		addsetObstacle(x/15*12,y/10*4,x/15*1,y/10*1);
		addsetObstacle(x/15*6,y/10*3,x/15*3,y/25*1);
		
		//moving panel
		PanelMoving left = new PanelMoving(x/35,y/15*3,x/20*2,y/15*1);
		this.add(left);
		//moving panel
		PanelMoving right = new PanelMoving(x/35*31,y/15*3,x/20*2,y/15*1);
		this.add(right);
		

				
		//obstacle border of maps
		addsetObstacle(0,getHeight()-50,getWidth(),50);
		addsetObstacle(0,0,10,getHeight());
		addsetObstacle(0,0,getWidth(),10);
		addsetObstacle(getWidth()-20,0,20,getHeight());
				
		player = new Panel_Player(500,200,100,100);
		this.add(player);
		this.addKeyListener(player);
		playerArray[0] = player;
		
		player2 = new Panel_Player2(300,200,100,100);
		this.add(player2);
		this.addKeyListener(player2);
		playerArray[1] = player2;
		
		hp2.setBounds(20, 20, 400, 20);
		hp1.setBounds(getWidth() - 400 - 20, 20, 400, 20);
		add(hp1);
		add(hp2);
		
		timer.start();		
		
		//this.add(new Panel_Main());	
						
		Timer gravityTimer = new Timer(50, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i<10; i++){
					if(player.isJumping == false){
//						if(arrayPanel[player.getX()][player.getY() + 1 + player.getHeight()] == false
//								&& arrayPanel[player.getX() + player.getWidth()][player.getY() + 1 + player.getHeight()] == false)
//							player.setLocation(player.getX(), player.getY()+1);
						for(int j = player.getX(); j < (player.getX() + player.getWidth()); j++){
							if(arrayPanel[j][player.getY() + player.getHeight() + 1] == true){
								player.onGround = true;
								return;
							}
						}
						player.onGround = false;
						player.setLocation(player.getX(), player.getY()+1);
						
					}
					if(player.isJumping == true){
//						if(arrayPanel[player.getX()][player.getY() - 1 + player.getHeight()] == false
//								&& arrayPanel[player.getX() + player.getWidth()][player.getY() - 1 + player.getHeight()] == false)
//							player.setLocation(player.getX(), player.getY()-1);
						for(int j = player.getX(); j < (player.getX() + player.getWidth()); j++){
							if(arrayPanel[j][player.getY() - 1] == true)
								return;
						}
						player.setLocation(player.getX(), player.getY()-1);
					}
				}
			}
			
		});
		gravityTimer.start();
		
		Timer gravityTimer2 = new Timer(50, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i<10; i++){
					if(player2.isJumping == false){
//						if(arrayPanel[player.getX()][player.getY() + 1 + player.getHeight()] == false
//								&& arrayPanel[player.getX() + player.getWidth()][player.getY() + 1 + player.getHeight()] == false)
//							player.setLocation(player.getX(), player.getY()+1);
						for(int j = player2.getX(); j < (player2.getX() + player2.getWidth()); j++){
							if(arrayPanel[j][player2.getY() + player2.getHeight() + 1] == true){
								player2.onGround = true;
								return;
							}
						}
						player2.onGround = false;
						player2.setLocation(player2.getX(), player2.getY()+1);
						
					}
					if(player2.isJumping == true){
//						if(arrayPanel[player.getX()][player.getY() - 1 + player.getHeight()] == false
//								&& arrayPanel[player.getX() + player.getWidth()][player.getY() - 1 + player.getHeight()] == false)
//							player.setLocation(player.getX(), player.getY()-1);
						for(int j = player2.getX(); j < (player2.getX() + player2.getWidth()); j++){
							if(arrayPanel[j][player2.getY() - 1] == true)
								return;
						}
						player2.setLocation(player2.getX(), player2.getY()-1);
					}
				}
			}
			
		});
		gravityTimer2.start();
		
	}
	
	void addsetObstacle(int x,int y,int z,int t){
		Panel_Platform p = new Panel_Platform(x,y,z,t);
		for (int i = p.getX(); i < (p.getX() + p.getWidth()); i++){
			for (int j = p.getY(); j < (p.getY() + p.getHeight()); j++){
				arrayPanel[i][j] = true;
			}
		}	
		this.add(p);
	}
	
	class TimerListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			hp1.setValue(((Panel_Player)playerArray[0]).hp);
			hp2.setValue(((Panel_Player2)playerArray[1]).hp);
			//repaint();
			revalidate();
		}
	}

}
