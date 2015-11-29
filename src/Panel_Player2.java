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

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;


public class Panel_Player2 extends JPanel implements KeyListener{

	boolean isLeft = true;
	boolean isJumping = false;
	boolean onGround = true;

	public Panel_Player2(int x , int y ,int x1 , int y1)
	{
		this.setBounds(x,y,x1,y1);
		this.setLayout(null);
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
			boolean block = false;
			for(int x1=getX();x1<getX()+getWidth();x1++)
				for(int y1=getY();y1<getY()+getHeight();y1++)
				{

				      if(CAT.arrayPanel[x1][y1])
					   {
						 block=true;
					   }
 
				}
			if(block==false)
			setLocation(getX()-10, getY());
			*/
			isLeft = true;
			for(int i = 0; i < 10; i++){
				for(int y = getY(); y < getY() + getHeight(); y++)
					if(CAT.arrayPanel[getX() - 1][y])
						return;
				setLocation(getX() - 1, getY());
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_D)
		{
			/*
			int x;
			int y;
			boolean block = false;
			for(int x1=getX();x1<getX()+getWidth();x1++)
				for(int y1=getY();y1<getY()+getHeight();y1++)
				{
					  x=x1;
					  y=y1;
				      if(CAT.arrayPanel[x][y])
					   {
						 block=true;
					   }
 
				}
			if(block==false)
			setLocation(getX()+10, getY());
			*/
			isLeft = false;
			for(int i = 0; i < 10; i++){
				for(int y = getY(); y < getY() + getHeight(); y++)
					if(CAT.arrayPanel[getX() + getWidth() + 1][y])
						return;
				setLocation(getX() + 1, getY());
			}
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
			System.out.println("G key pressed.");
			getParent().add(new Bullet(this));
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	
}
