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
		
		System.out.println(getWidth());
		System.out.println(getHeight());
	
		arrayPanel = new boolean[getWidth()][getHeight()];
		for (int i = 0; i < getWidth(); i++){
			for (int j = 0; j < getHeight(); j++){
				arrayPanel[i][j] = false;
			}
		}
		
					
		//30,40 to 60,50
		Panel_Platform platformMid = new Panel_Platform(300, 300, 400 ,50);
		this.add(platformMid);
		
		Panel_Platform platformMidObstacle = new Panel_Platform(400, 200, 50, 100);
		this.add(platformMidObstacle);
		
		for (int i = platformMid.getX(); i < (platformMid.getX() + platformMid.getWidth()); i++){
			for (int j = platformMid.getY(); j < (platformMid.getY() + platformMid.getHeight()); j++){
				arrayPanel[i][j] = true;
			}
		}
		
		for (int i = platformMidObstacle.getX(); i < (platformMidObstacle.getX() + platformMidObstacle.getWidth()); i++){
			for (int j = platformMidObstacle.getY(); j < (platformMidObstacle.getY() + platformMidObstacle.getHeight()); j++){
				arrayPanel[i][j] = true;
			}
		}
		
		Panel_Player player = new Panel_Player();
		this.add(player);
		this.addKeyListener(player);
		this.add(new Panel_Main());
		timer.start();
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
