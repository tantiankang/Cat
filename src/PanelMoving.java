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
	private Timer timer = new Timer(100, new MoveListener());
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
			img = ImageIO.read(new File("platform.jpg"));
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
				setLocation(getX(), getY()+5);
				if(getY()==652)
				{
					direction = true;
				}
			}
			if( direction ==true)
			{

				setLocation(getX(), getY()-5);
				for(int j = CAT.player.getX(); j<CAT.player.getX() + CAT.player.getWidth(); j++){
					
				}
				if(CAT.arrayPanel[CAT.player.getX()][CAT.player.getY() + CAT.player.getHeight()] 
						|| CAT.arrayPanel[CAT.player.getX()+CAT.player.getWidth()][CAT.player.getY() + CAT.player.getHeight()]){
					CAT.player.setLocation(CAT.player.getX(), getY() - CAT.player.getHeight());
				}
				if(CAT.arrayPanel[CAT.player2.getX()][CAT.player2.getY() + CAT.player2.getHeight()] 
						|| CAT.arrayPanel[CAT.player2.getX()+CAT.player2.getWidth()][CAT.player2.getY() + CAT.player2.getHeight()]){
					CAT.player2.setLocation(CAT.player2.getX(), getY() - CAT.player2.getHeight());
				}
				
				if(getY()==132)
				{
					direction = false;
				}
			}
			
			for (int i = getX(); i < (getX() + getWidth()); i++)
				for (int j = getY(); j < (getY() + getHeight()); j++)
					CAT.arrayPanel[i][j] = true;

	}

}}
