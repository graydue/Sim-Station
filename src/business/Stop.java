package business;

import framework.Command;

public class Stop extends Command{
	private Simulation sim;
	
	public Stop(Simulation sim) {
		this.sim = sim;
	}
	
	public void execute() {
		sim.stop();
	}
}
