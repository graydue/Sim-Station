package business;

import framework.Model;


public abstract class Simulation extends Model implements Runnable{
	protected String name;
	protected long clock = 0L;
	protected SimState state = SimState.READY;
	private Thread thread;

	public Simulation() {

	}

	public void run() {
		synchronized(thread) {
			while(state == SimState.SUSPENDED) {
				try {
					thread.wait();
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		while(state == SimState.RUNNING) {
			update();
			changed();
			clock++;
		}
	}

	public void start() {
		if(state == SimState.READY) {
			thread = new Thread(this);
			state = SimState.RUNNING;
			thread.start();
		}
	}

	public void stop() {
		state = SimState.STOPPED;
	}

	public void suspend() {
		if(state != SimState.STOPPED) {
			state = SimState.SUSPENDED;
		}
	}

	public void resume() {
		synchronized(thread) {
			if(state != SimState.RUNNING && state != SimState.STOPPED) {
				state = SimState.READY;
				thread.notify();
			}
		}
	}

	public abstract void update();

	public long getClockTime() {
		return clock;
	}
}
