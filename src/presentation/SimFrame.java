package presentation;

import framework.AppFrame;
import framework.AppFactory;

public class SimFrame extends AppFrame {
	public SimFrame(AppFactory f) {
		super(f);
		this.pack();
		this.setResizable(false);
	}
	
	public static void main(String[] args) {
		SimFrame sf = new SimFrame(new SimFactory());
		sf.display();
	}
}
