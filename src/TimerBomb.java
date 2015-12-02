import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;

import javax.swing.JPanel;
import javax.swing.Timer;

public class TimerBomb extends JPanel{
	
	Timer bTimer;
	int explodeRadius = 75;
		
	public TimerBomb(Panel_Player p){
		
		setBounds(p.getX() + p.getWidth()/3,p.getY()+ p.getHeight() - 50, 50, 50);
		setOpaque(false);
		revalidate();
		
		Timer bombTimer = new Timer(3000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {	
				System.out.println("Bomb timer triggered.");
				Ellipse2D.Double bombRadius = new Ellipse2D.Double(getX()- explodeRadius, getY()- explodeRadius, getX() + getWidth()+ explodeRadius, getY() + getHeight()+ explodeRadius);
				Rectangle2D.Double playerHitbox = new Rectangle2D.Double(CAT.player.getX(), CAT.player.getY(), CAT.player.getWidth(),CAT.player.getHeight());
				Rectangle2D.Double player2Hitbox = new Rectangle2D.Double(CAT.player2.getX(), CAT.player2.getY(), CAT.player2.getWidth(),CAT.player2.getHeight());
				if (bombRadius.intersects(playerHitbox)){
					CAT.player.damageHP(50);
				}
				if (bombRadius.intersects(player2Hitbox)){
					CAT.player2.damageHP(50);
				}
				setVisible(false);
				removeBomb();
			}			
		});
		bTimer = bombTimer;
		bombTimer.setRepeats(false);
		bombTimer.start();
	}
	
	public TimerBomb(Panel_Player2 p){
		
		setBounds(p.getX() + p.getWidth()/3,p.getY()+ p.getHeight() - 50, 50, 50);
		setOpaque(false);
		revalidate();
		
		Timer bombTimer = new Timer(3000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {	
				System.out.println("Bomb timer triggered.");
				Ellipse2D.Double bombRadius = new Ellipse2D.Double(getX()-explodeRadius, getY()- explodeRadius, getX() + getWidth()+explodeRadius, getY() + getHeight()+explodeRadius);
				Rectangle2D.Double playerHitbox = new Rectangle2D.Double(CAT.player.getX(), CAT.player.getY(), CAT.player.getWidth(),CAT.player.getHeight());
				Rectangle2D.Double player2Hitbox = new Rectangle2D.Double(CAT.player2.getX(), CAT.player2.getY(), CAT.player2.getWidth(),CAT.player2.getHeight());
				if (bombRadius.intersects(playerHitbox)){
					CAT.player.damageHP(50);
				}
				if (bombRadius.intersects(player2Hitbox)){
					CAT.player2.damageHP(50);
				}
				setVisible(false);
				removeBomb();
			}			
		});
		bTimer = bombTimer;
		bombTimer.setRepeats(false);
		bombTimer.start();		
	}
	
	void removeBomb(){
		getParent().remove(this);
	}
	
	protected void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillOval(0, 0, getWidth(), getHeight());
		revalidate();
	}
}