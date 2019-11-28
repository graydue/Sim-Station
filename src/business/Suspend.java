package business;

import framework.Command;

public class Suspend extends Command{
	private Simulation sim;
	
	public Suspend(Simulation sim) {
		this.sim = sim;
	}
	
	public void execute() {
		sim.suspend();
	}
}
