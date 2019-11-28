package business;

import framework.Command;

public class Start extends Command{
	private Simulation sim;
	
	public Start(Simulation sim) {
		this.sim = sim;
	}
	
	public void execute() {
		sim.start();
	}
}
