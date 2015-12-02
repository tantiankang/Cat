
public class driverNo3d {
	
	static CAT cat;
	
	public static void main(String [] args)
	{
		Thread.currentThread().setPriority((int)(Thread.MAX_PRIORITY*0.9));
		cat = new CAT();
	}
	
	public static void restartProgram(){
		cat.closeFrame();
		cat = new CAT();
	}

}
