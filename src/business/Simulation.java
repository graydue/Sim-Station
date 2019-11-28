package business;

import framework.Model;


public abstract class Simulation extends Model implements Runnable{
	protected String name;
	protected Long clock = 0L;
	protected SimState state = SimState.READY;
	private Thread thread;
	
	public Simulation() {
		
	}
	
	public void run() {
		try {
			thread.sleep(100);
			synchronized(this) {
				while(state == SimState.SUSPENDED) {
					wait();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void start() {
		state = SimState.READY;
	}
	
	public void stop() {
		state = SimState.STOPPED;
	}
	
	public void suspend() {
		state = SimState.SUSPENDED;
	}
	
	public void resume() {
		state = SimState.READY;
		this.notify();
	}
	
	public abstract void update();
}
