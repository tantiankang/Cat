import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CAT extends JFrame {
	private Timer timer = new Timer(500, new TimerListener());
	public static boolean arrayPanel[][];
	
	public CAT()
	{
		this.setTitle("Cat Shooter Game");
		this.setFocusable(true);
		this.setSize(1200,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		
		arrayPanel = new boolean[getWidth()][getHeight()];
		for (int i = 0; i < getWidth(); i++){
			for (int j = 0; j < getHeight(); j++){
				arrayPanel[i][j] = false;
			}
		}		
					
		//All platforms
		addsetObstacle(300,300,400,50);
		//addsetObstacle(300,300,400,50);
		//addsetObstacle(300,300,400,50);
		//addsetObstacle(300,300,400,50);
		//addsetObstacle(300,300,400,50);
		//addsetObstacle(300,300,400,50);
		//addsetObstacle(300,300,400,50);
		
		//All obstacle
		addsetObstacle(400,200,50,100);

		//obstacle border of maps
		addsetObstacle(0,getHeight()-50,getWidth(),50);
		addsetObstacle(0,0,10,getHeight());
		addsetObstacle(0,0,getWidth(),10);
		addsetObstacle(getWidth()-20,0,20,getHeight());

				
		final Panel_Player player = new Panel_Player(500,200,100,100);
		this.add(player);
		this.addKeyListener(player);
		
		
		final Panel_Player2 player2 = new Panel_Player2(300,200,100,100);
		this.add(player2);
		this.addKeyListener(player2);

		
		timer.start();
		this.add(new Panel_Main());
		
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
							if(arrayPanel[j][player.getY() + player.getHeight() - 1] == true)
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
							if(arrayPanel[j][player2.getY() + player2.getHeight() - 1] == true)
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
			repaint();
			revalidate();

		}
	}

}
