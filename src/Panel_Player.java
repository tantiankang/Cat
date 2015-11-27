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

import javax.imageio.ImageIO;
import javax.swing.*;


public class Panel_Player extends JPanel implements KeyListener{

	public Panel_Player()
	{
		this.setBounds(500,250,100,100);
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
			if(CAT.arrayPanel[getX()][getY()])
			{
				//System.out.println(getX() + " " + getY());
				//do nothing
			}
			else
			{
				setLocation(getX()-10, getY());
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			setLocation(getX()+10, getY());
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			System.out.println("the key is space");
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
