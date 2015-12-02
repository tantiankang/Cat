import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Display extends JPanel {
	
	String filename;
	public Display(String filename,int x ,int y,int sizex,int sizey)
	{
		this.filename=filename;
		this.setBounds(x,y,sizex,sizey);
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		BufferedImage img2=null;
		try {
			img2 = ImageIO.read(new File(filename));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		g.drawImage(img2,0,0,this);
		repaint();
		revalidate();
	}

}