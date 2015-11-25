import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MoveListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
			
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==37)
		{
			System.out.println("the key is left"+Panel_Player.getX());
			Panel_Player.setLocation(Panel_Player.getX()-50, Panel_Player.getY());
			Panel_Player.setBounds(Panel_Player.getX()-50, Panel_Player.getY(),100,100);


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
