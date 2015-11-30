import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class PanelMoving extends JPanel {
	private Timer timer = new Timer(200, new MoveListener());
	public PanelMoving()
	{
		
	}
	public PanelMoving(int x,int y,int sizex,int sizey)
	{
		this.setBounds(x,y,sizex,sizey);
		timer.start();
	}
	
	protected void paintComponent(Graphics g)
	{
		BufferedImage img=null;
		try {
			img = ImageIO.read(new File("platform.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(img,0,0,this);
	}
	
	class MoveListener implements ActionListener
	{
		
		boolean direction = false;
		public void actionPerformed(ActionEvent e) {
			for (int i = getX(); i < (getX() + getWidth()); i++)
				for (int j = getY(); j < (getY() + getHeight()); j++)
					CAT.arrayPanel[i][j] = false;
				
			Timer moving = (Timer)e.getSource();

			if( direction ==false)
			{
				setLocation(getX(), getY()+10);
				if(getY()==508)
				{
					direction = true;
				}
			}
			if( direction ==true)
			{
				setLocation(getX(), getY()-10);
				if(getY()==138)
				{
					direction = false;
				}
			}
			
			for (int i = getX(); i < (getX() + getWidth()); i++){
				for (int j = getY(); j < (getY() + getHeight()); j++){
					CAT.arrayPanel[i][j] = true;
				}
		}
	}

}}
