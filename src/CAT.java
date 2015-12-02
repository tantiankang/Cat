import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;


public class CAT extends JFrame {
	private Timer timer = new Timer(33, new TimerListener());
	private Timer gravityTimer;
	private Timer gravityTimer2;
	public static boolean arrayPanel[][];
	public static JProgressBar hp1 = new JProgressBar(JProgressBar.HORIZONTAL, 0, 200);
	public static JProgressBar hp2 = new JProgressBar(JProgressBar.HORIZONTAL, 0, 200);
	public static Panel_Player player;
	public static Panel_Player2 player2;
	
	public CAT()
	{
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:/Users/Asus/Desktop/nyan.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.setTitle("Cat Shooter Game");
		this.setFocusable(true);
		this.setSize(1380,700);
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		addsetObstacle(x/35*9,y/16*14,x/35*5,y/16, false);
		addsetObstacle(x/35*20,y/16*14,x/35*5,y/16, false);
		addsetObstacle(x/35*6,y/16*12,x/35*3,y/16, false);
		addsetObstacle(x/35*25,y/16*12,x/35*3,y/16, false);
		addsetObstacle(x/35*16,y/16*12,x/35*2,y/16*3, false);
		addsetObstacle(x/35*6,y/16*8,x/35*3,y/16, false);
		addsetObstacle(x/35*25,y/16*8,x/35*3,y/16, false);
		addsetObstacle(x/35*11,y/16*6,x/35*4,y/16, false);
		addsetObstacle(x/35*19,y/16*6,x/35*4,y/16, false);
		addsetObstacle(x/35*16,y/16*4,x/35*2,y/16*1, false);
		
		Display d1 = new Display("CAT1L.gif",x/35*32,y/16*1,x/35*3,y/12*1);
		Display d2 = new Display("CAT2R.gif",x/35,y/16*1,x/35*3,y/12*1);
		this.add(d1);
		this.add(d2);
		
		//moving panel
		PanelMoving left = new PanelMoving(x/35,y/16*14,x/35*5,y/16);
		this.add(left);
		//moving panel
		PanelMoving right = new PanelMoving(x/35*28,y/16*14,x/35*5,y/16);
		this.add(right);
		

				
		//obstacle border of maps
		addsetObstacle(0,getHeight()-50,getWidth(),50, true);
		addsetObstacle(0,0,10,getHeight(), true);
		addsetObstacle(0,0,getWidth(),10, true);
		addsetObstacle(getWidth()-20,0,20,getHeight(), true);
				
		player = new Panel_Player(x/35*9,y/16*12,99,61);
		this.add(player);
		this.addKeyListener(player);
		
		player2 = new Panel_Player2(x/35*20,y/16*12,99,61);
		this.add(player2);
		this.addKeyListener(player2);
		
		hp2.setBounds(20, 20, 400, 20);
		hp1.setBounds(getWidth() - 400 - 20, 20, 400, 20);
		add(hp1);
		add(hp2);
		
		timer.start();	
		
		gravityTimer = new Timer(16, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i<4; i++){
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
		
		gravityTimer2 = new Timer(16, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i<4; i++){
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
	
	void addsetObstacle(int x,int y,int z,int t, boolean isTransparent){
		if(!isTransparent){
			Panel_Platform p = new Panel_Platform(x,y,z,t);
			for (int i = p.getX(); i < (p.getX() + p.getWidth()); i++){
				for (int j = p.getY(); j < (p.getY() + p.getHeight()); j++){
					arrayPanel[i][j] = true;
			}
			this.add(p);
		}	
		}else{
			for(int i = x; i <= x+z; i++)
				for(int j = y; j <= y+t; j++)
					arrayPanel[i][j] = true;
			
		}
	}
	
	class TimerListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			hp1.setValue(player.hp);
			hp2.setValue(player2.hp);
			//repaint();
			revalidate();
		}
	}
	
	public void closeFrame(){
		gravityTimer.stop();
		gravityTimer2.stop();
		setVisible(false);
		dispose();
	}
}