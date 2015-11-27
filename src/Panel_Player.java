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
		this.setBounds(65*CAT.xUnit,35*CAT.yUnit,5*CAT.xUnit,13*CAT.yUnit);
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
			System.out.println(getX()/CAT.xUnit + getX()%CAT.xUnit + " " + getY()/CAT.yUnit + getY()%CAT.yUnit);
			if(CAT.arrayPanel[getX()/CAT.xUnit + getX()%CAT.xUnit][getY()/CAT.yUnit + getY()%CAT.yUnit])
			{
				System.out.println("cat can't move");
				//do nothing
			}
			else
			{
				setLocation(getX()-CAT.xUnit, getY());
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
