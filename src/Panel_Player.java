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


public class Panel_Player extends JPanel implements KeyListener,ActionListener{
	int count=-1;
	int stopCount =0;
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
			setLocation(getX()-10, getY());
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
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
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			   Timer timer = new Timer(50, this);
			   timer.start();

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

	@Override
	public void actionPerformed(ActionEvent e) {
		Timer time = (Timer)e.getSource();
		
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
		{
			if(count<3)
			{
				setLocation(getX(),getY()-15);
				count++;
			}
			else
			{
				setLocation(getX(),getY()+15);
				stopCount++;
			}
			if(stopCount == 4)
			{
				time.stop();
				stopCount =0;
				count =-1;
			}
		}
	
		
	}
	
}
