import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Bullet extends JPanel{
	
	Timer bTimer;
	boolean isLeft;
	int offset = 40;
	
	public Bullet(Panel_Player p){
	
		isLeft = p.isLeft;
		if(p.isLeft){
			setBounds(p.getX(),p.getY()+ offset, 50, 5);
		}
		
		else{
			setBounds(p.getX() + p.getWidth(),p.getY()+ offset, 50, 5);
		}		
		
		Timer bulletTimer = new Timer(10, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i < 10; i++){
					for(int x = getX(); x < getX() + getWidth(); x++)
						for(int y = getY(); y < getY() + getHeight(); y++){
							if(CAT.arrayPanel[x][y]){
								removeBullet();
								stopBulletTimer();								
								return;
							}						
						}
					int bMove;
					if(isLeft)
						bMove = -1;
					else
						bMove = +1;
						
					setLocation(getX()+bMove, getY());
					if (
							(
								getX() >= CAT.player2.getX() ||
								(getX() + getWidth()) >= CAT.player2.getX()
							)
							&&
							(
								getX()  <= CAT.player2.getX() + CAT.player2.getWidth() ||
								(getX() + getWidth())  <= CAT.player2.getX() + CAT.player2.getWidth()
							)
							&&
							(
								getY() >= CAT.player2.getY() ||
								(getY() + getHeight()) >= CAT.player2.getY()
							)
							&&
							(
								getY()  <= CAT.player2.getY() + CAT.player2.getHeight() ||
								(getY() + getHeight())  <= CAT.player2.getY() + CAT.player2.getHeight()
							)
							
						){
						CAT.player2.damageHP(4);
						removeBullet();
						stopBulletTimer();

						return;
					} //end of the extremely long hitbox check if
				}
			}			
		});
		
		bTimer = bulletTimer;
		bTimer.start();		
	}
	
	public Bullet(Panel_Player2 p){
		isLeft = p.isLeft;
		if(p.isLeft){
			setBounds(p.getX(),p.getY()+ offset, 50, 5);
		}
		
		else{
			setBounds(p.getX() + p.getWidth(),p.getY()+ offset, 50, 5);
		}

		
		Timer bulletTimer = new Timer(10, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i < 10; i++){
					for(int x = getX(); x < getX() + getWidth(); x++)
						for(int y = getY(); y < getY() + getHeight(); y++){
							if(CAT.arrayPanel[x][y]){
								removeBullet();
								stopBulletTimer();								
								return;
							}						
						}
					int bMove;
					if(isLeft)
						bMove = -1;
					else
						bMove = +1;
						
					setLocation(getX()+bMove, getY());
					if (
							(
								getX() >= CAT.player.getX() ||
								(getX() + getWidth()) >= CAT.player.getX()
							)
							&&
							(
								getX()  <= CAT.player.getX() + CAT.player.getWidth() ||
								(getX() + getWidth())  <= CAT.player.getX() + CAT.player.getWidth()
							)
							&&
							(
								getY() >= CAT.player.getY() ||
								(getY() + getHeight()) >= CAT.player.getY()
							)
							&&
							(
								getY()  <= CAT.player.getY() + CAT.player.getHeight() ||
								(getY() + getHeight())  <= CAT.player.getY() + CAT.player.getHeight()
							)
							
						){
						CAT.player.damageHP(4);
						removeBullet();
						stopBulletTimer();

						return;
					} //end of the extremely long hitbox check if
				}
			}			
		});
		
		bTimer = bulletTimer;
		bTimer.start();		
	}
	
	void removeBullet(){
		getParent().remove(this);
	}
	
	void stopBulletTimer(){
		bTimer.stop();
	}
	
	protected void paintComponent(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}