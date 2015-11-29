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
					
		//30,40 to 60,50
		Panel_Platform platformMid = new Panel_Platform(300, 300, 400 ,50);
		setObstacle(platformMid);
		this.add(platformMid);
		
		Panel_Platform platformMidObstacle = new Panel_Platform(400, 200, 50, 100);
		setObstacle(platformMidObstacle);
		this.add(platformMidObstacle);
		
		Panel_Platform groundPlatform = new Panel_Platform(0, getHeight()-50, getWidth(), 50);
		setObstacle(groundPlatform);
		this.add(groundPlatform);
				
		final Panel_Player player = new Panel_Player();
		this.add(player);
		this.addKeyListener(player);
		this.add(new Panel_Main());
		timer.start();
		
		Timer gravityTimer = new Timer(50, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i<10; i++){
					if(player.isJumping == false){
//						if(arrayPanel[player.getX()][player.getY() + 1 + player.getHeight()] == false
//								&& arrayPanel[player.getX() + player.getWidth()][player.getY() + 1 + player.getHeight()] == false)
//							player.setLocation(player.getX(), player.getY()+1);
						for(int j = player.getX(); j < (player.getX() + player.getWidth()); j++){
							if(arrayPanel[j][player.getY() + player.getHeight() + 1] == true)
								return;
						}
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
		
	}
	
	void setObstacle(Panel_Platform p){
		for (int i = p.getX(); i < (p.getX() + p.getWidth()); i++){
			for (int j = p.getY(); j < (p.getY() + p.getHeight()); j++){
				arrayPanel[i][j] = true;
			}
		}		
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
