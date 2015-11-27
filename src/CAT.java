import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CAT extends JFrame {
	private Timer timer = new Timer(500, new TimerListener());
	public static boolean arrayPanel[][] = new boolean[100][80];
	public static int xUnit;
	public static int yUnit;
	
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
		
		xUnit = getWidth()/100;
		yUnit = getHeight()/80;
		
		for (int i = 0; i < 100; i++){
			for (int j = 0; j < 80; j++){
				arrayPanel[i][j] = false;
			}
		}
					
		//30,40 to 60,50
		Panel_Platform platformMid = new Panel_Platform(30*xUnit,40*yUnit ,30*xUnit ,5*yUnit);
		this.add(platformMid);
		
		Panel_Platform platformMidObstacle = new Panel_Platform(40*xUnit, 20*yUnit, 5*xUnit, 20*yUnit);
		this.add(platformMidObstacle);
		
		for (int i = platformMid.getX(); i < (platformMid.getX() + platformMid.getWidth()); i++){
			for (int j = platformMid.getY(); i < (platformMid.getY() + platformMid.getHeight()); i++){
				arrayPanel[i][j] = true;
			}
		}
		
		for (int i = platformMidObstacle.getX(); i < (platformMidObstacle.getX() + platformMidObstacle.getWidth()); i++){
			for (int j = platformMidObstacle.getY(); i < (platformMidObstacle.getY() + platformMidObstacle.getHeight()); i++){
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
