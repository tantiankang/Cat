import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Panel_Player extends JPanel implements KeyListener {
	
	public Panel_Player()
	{
		this.setBounds(500,250,100,100);
		this.setFocusable(true);
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
			
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==37)
		{
			System.out.println("the key is left"+this.getX());
			this.setBounds(this.getX()-100, this.getY(),100,100);

		}
		if(e.getKeyCode()==39)
		{
			System.out.println("the key is right");
		}
		if(e.getKeyCode()==32)
		{
			System.out.println("the key is space");
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}
