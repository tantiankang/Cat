import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Panel_Player2 extends JPanel implements KeyListener{

	boolean isLeft = true;
	boolean isJumping = false;
	boolean onGround = true;
	boolean laserCD = false;
	BufferedImage img;
	boolean left;
	Graphics any;
	int hp = 100;
	ConcurrentHashMap<String, Boolean> keyPressedMap = new ConcurrentHashMap<String, Boolean>();

	public Panel_Player2(int x , int y ,int x1 , int y1)
	{
		this.setBounds(x,y,x1,y1);
		this.setLayout(null);
		
		keyPressedMap.put("left", false);
		keyPressedMap.put("right", false);
		keyPressedMap.put("shoot", false);
		keyPressedMap.put("jump", false);
		
		Timer keyTimer = new Timer(25, new KeyTimer());
		keyTimer.start();
		setOpaque(false);
	}
	
	void damageHP(int damage){
		if(this.hp >= 0)
			this.hp = this.hp - damage;
		else
		{
			JOptionPane.showMessageDialog(null, "Angry Cat lost");
			System.exit(0);
		}
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		any =g;
		if(left==true)
		{
			BufferedImage img2=null;
			try {
				img2 = ImageIO.read(new File("CAT2L.gif"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			any.drawImage(img2,0,0,this);
			repaint();
			revalidate();
		}
		else
		{
			BufferedImage img2=null;
			try {
				img2 = ImageIO.read(new File("CAT2R.gif"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			any.drawImage(img2,0,0,this);
			repaint();
			revalidate();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_A)
		{
			left = true;
			keyPressedMap.replace("left", true);
			this.paintComponent(any);
		}
			
		if(e.getKeyCode() == KeyEvent.VK_D)
		{
			left = false;
			keyPressedMap.replace("right", true);
			this.paintComponent(any);
		}
			
		if(e.getKeyCode() == KeyEvent.VK_W)
			keyPressedMap.replace("jump", true);
		if(e.getKeyCode() == KeyEvent.VK_G)
			keyPressedMap.replace("shoot", true);		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_A)
			keyPressedMap.replace("left", false);
		if(e.getKeyCode() == KeyEvent.VK_D)
			keyPressedMap.replace("right", false);
		if(e.getKeyCode() == KeyEvent.VK_G)
			keyPressedMap.replace("shoot", false);
		if(e.getKeyCode() == KeyEvent.VK_W)
			keyPressedMap.replace("jump", false);		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	class KeyTimer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(keyPressedMap.get("left")){
				isLeft = true;
				for(int i = 0; i < 10; i++){
					for(int y = getY(); y < getY() + getHeight(); y++)
						if(CAT.arrayPanel[getX() - 1][y])
							return;
					setLocation(getX() - 1, getY());
				}
			}
			if(keyPressedMap.get("right")){
				isLeft = false;
				for(int i = 0; i < 10; i++){
					for(int y = getY(); y < getY() + getHeight(); y++)
						if(CAT.arrayPanel[getX() + getWidth() + 1][y])
							return;
					setLocation(getX() + 1, getY());
				}
			}
			if(keyPressedMap.get("shoot") && (!laserCD)){
	
				laserCD = true;
				getParent().add(new Bullet(Panel_Player2.this));
				Timer laserTimer = new Timer(200, new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						laserCD = false;
						
					}
					
				});
				
				laserTimer.setRepeats(false);
				laserTimer.start();
			}
			if(keyPressedMap.get("jump")){
				if(!onGround)
					return;
				isJumping = true;
				onGround = false;
				Timer jumpingTimer = new Timer(500, new ActionListener(){
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
	                        isJumping = false;
	                }
	                   
				});
				jumpingTimer.setRepeats(false);
				jumpingTimer.start();
			}
		}		
	}	
}
