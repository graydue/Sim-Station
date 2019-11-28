package business;

import framework.Command;

public class Resume extends Command{
	private Simulation sim;
	
	public Resume(Simulation sim) {
		this.sim = sim;
	}
	
	public void execute() {
		sim.resume();
	}
}
