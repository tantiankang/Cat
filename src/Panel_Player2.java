import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;


public class Panel_Player2 extends JPanel implements KeyListener{

	boolean isLeft = true;
	boolean isJumping = false;
	boolean onGround = true;
	int hp = 100;
	ConcurrentHashMap<String, Boolean> keyPressedMap = new ConcurrentHashMap<String, Boolean>();

	public Panel_Player2(int x , int y ,int x1 , int y1)
	{
		this.setBounds(x,y,x1,y1);
		this.setLayout(null);
		
		keyPressedMap.put("left", false);
		keyPressedMap.put("right", false);
		keyPressedMap.put("shoot", false);
		
		Timer keyTimer = new Timer(25, new KeyTimer());
		keyTimer.start();
	}
	
	void damageHP(int damage){
		if(this.hp - 10 != 0)
		this.hp = this.hp - damage;
	}
	
	protected void paintComponent(Graphics g)
	{
		BufferedImage img=null;
		try {
			img = ImageIO.read(new File("player.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(img,0,0,this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_A)
		{
			/*
			isLeft = true;
			for(int i = 0; i < 10; i++){
				for(int y = getY(); y < getY() + getHeight(); y++)
					if(CAT.arrayPanel[getX() - 1][y])
						return;
				setLocation(getX() - 1, getY());
			}
			*/
			keyPressedMap.replace("left", true);
		}
		if(e.getKeyCode() == KeyEvent.VK_D)
		{
			keyPressedMap.replace("right", true);
			/*
			isLeft = false;
			for(int i = 0; i < 10; i++){
				for(int y = getY(); y < getY() + getHeight(); y++)
					if(CAT.arrayPanel[getX() + getWidth() + 1][y])
						return;
				setLocation(getX() + 1, getY());
			}
			*/
		}
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			if(!onGround)
				return;
			isJumping = true;
			onGround = false;
			Timer jumpingTimer = new Timer(750, new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
                        isJumping = false;
                }
                   
			});
			jumpingTimer.setRepeats(false);
			jumpingTimer.start();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_G){
			keyPressedMap.replace("shoot", true);
			/*
			System.out.println("G key pressed.");
			getParent().add(new Bullet(this));
			*/
		}
		
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
			if(keyPressedMap.get("shoot")){
				System.out.println("G key pressed.");
				getParent().add(new Bullet(Panel_Player2.this));
			}
		}
		
	}



	
}
