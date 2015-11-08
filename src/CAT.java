import javax.swing.*;


public class CAT extends JFrame {
	
	public CAT()
	{
		this.setTitle("Cat Shooter Game");
		this.setFocusable(true);
		this.setSize(1200,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		this.add(new Panel_Platform());
		this.addKeyListener(new Panel_Player());
		this.add(new Panel_Player());
		this.add(new Panel_Main());
	}

}
