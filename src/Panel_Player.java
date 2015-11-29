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


public class Panel_Player extends JPanel implements KeyListener{

	int count=-1;
	int stopCount =0;
	boolean isJumping = false;
	boolean onGround = true;

	public Panel_Player()
	{
		this.setBounds(500,200,100,100);
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
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
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
			for(int i = 0; i < 10; i++){
				for(int y = getY(); y < getY() + getHeight(); y++)
					if(CAT.arrayPanel[getX() - 1][y])
						return;
				setLocation(getX() - 1, getY());
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
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
			for(int i = 0; i < 10; i++){
				for(int y = getY(); y < getY() + getHeight(); y++)
					if(CAT.arrayPanel[getX() + getWidth() + 1][y])
						return;
				setLocation(getX() + 1, getY());
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)
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
