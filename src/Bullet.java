import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

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
		
		System.out.println("bullet created with " + p.getX() + p.getY());
		
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
								getX() >= ((Panel_Player2)CAT.playerArray[1]).getX() ||
								(getX() + getWidth()) >= ((Panel_Player2)CAT.playerArray[1]).getX()
							)
							&&
							(
								getX()  <= ((Panel_Player2)CAT.playerArray[1]).getX() + ((Panel_Player2)CAT.playerArray[1]).getWidth() ||
								(getX() + getWidth())  <= ((Panel_Player2)CAT.playerArray[1]).getX() + ((Panel_Player2)CAT.playerArray[1]).getWidth()
							)
							&&
							(
								getY() >= ((Panel_Player2)CAT.playerArray[1]).getY() ||
								(getY() + getHeight()) >= ((Panel_Player2)CAT.playerArray[1]).getY()
							)
							&&
							(
								getY()  <= ((Panel_Player2)CAT.playerArray[1]).getY() + ((Panel_Player2)CAT.playerArray[1]).getHeight() ||
								(getY() + getHeight())  <= ((Panel_Player2)CAT.playerArray[1]).getY() + ((Panel_Player2)CAT.playerArray[1]).getHeight()
							)
							
						){
						((Panel_Player2)CAT.playerArray[1]).damageHP(10);
						removeBullet();
						stopBulletTimer();
						System.out.println("Player 2's hp " + ((Panel_Player2)CAT.playerArray[1]).hp);
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
		System.out.println("bullet created with " + p.getX() + p.getY());
		
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
								getX() >= ((Panel_Player)CAT.playerArray[0]).getX() ||
								(getX() + getWidth()) >= ((Panel_Player)CAT.playerArray[0]).getX()
							)
							&&
							(
								getX()  <= ((Panel_Player)CAT.playerArray[0]).getX() + ((Panel_Player)CAT.playerArray[0]).getWidth() ||
								(getX() + getWidth())  <= ((Panel_Player)CAT.playerArray[0]).getX() + ((Panel_Player)CAT.playerArray[0]).getWidth()
							)
							&&
							(
								getY() >= ((Panel_Player)CAT.playerArray[0]).getY() ||
								(getY() + getHeight()) >= ((Panel_Player)CAT.playerArray[0]).getY()
							)
							&&
							(
								getY()  <= ((Panel_Player)CAT.playerArray[0]).getY() + ((Panel_Player)CAT.playerArray[0]).getHeight() ||
								(getY() + getHeight())  <= ((Panel_Player)CAT.playerArray[0]).getY() + ((Panel_Player)CAT.playerArray[0]).getHeight()
							)
							
						){
						((Panel_Player)CAT.playerArray[0]).damageHP(10);
						removeBullet();
						stopBulletTimer();
						System.out.println("Player 1's hp " + ((Panel_Player)CAT.playerArray[0]).hp);
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
		System.out.println("Bullet removed.");
	}
	
	void stopBulletTimer(){
		bTimer.stop();
	}
	
	protected void paintComponent(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}
