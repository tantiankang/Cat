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
	
	public Bullet(final Panel_Player p){
	
		isLeft = p.isLeft;
		if(p.isLeft){
			setBounds(p.getX(),p.getY()+10, 50, 5);
		}
		
		else{
			setBounds(p.getX() + p.getWidth(),p.getY()+10, 50, 5);
		}
		
		System.out.println("bullet created with " + p.getX() + p.getY());
		
		Timer bulletTimer = new Timer(10, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i < 10; i++){
					if(getX() >= getParent().getWidth() || getX() == 0){
						removeBullet();
						stopBulletTimer();
						return;
					}
					else{
						if(isLeft)
							setLocation(getX()-1, getY());
						else
							setLocation(getX()+1, getY());
					}
				}				
			}
			
		});
		
		bTimer = bulletTimer;
		bTimer.start();		
	}
	
	public Bullet(Panel_Player2 p){
		isLeft = p.isLeft;
		setBounds(p.getX(),p.getY(), 50, 5);
		System.out.println("bullet created with " + p.getX() + p.getY());
		
		Timer bulletTimer = new Timer(10, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i < 10; i++){
					if(getX() >= getParent().getWidth() || getX() == 0){
						removeBullet();
						stopBulletTimer();
						return;
					}
					else{
						if(isLeft)
							setLocation(getX()-1, getY());
						else
							setLocation(getX()+1, getY());
					}
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
