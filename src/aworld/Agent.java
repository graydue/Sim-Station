package aworld;

public abstract class Agent {
	protected int xc;
	protected int yc;
	protected Heading heading;
	protected World world;
	
	public Agent() {
		
	}
	
	public void move(int steps) {
		
	}
	
	public void turn(Heading heading) {
		
	}
	
	public abstract void update();
}
