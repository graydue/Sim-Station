package aworld;

public class Agent {
	protected int xc;
	protected int yc;
	protected int speed;
	protected Heading heading;
	
	public Agent(int xc, int yc, int speed) {
		this.xc = xc;
		this.yc = yc;
		this.speed = speed;
	}
	
	public void move() {
		try {
			Thread.sleep(1);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(heading == Heading.NORTH) {
			yc -= speed;
		}
		else if(heading == Heading.EAST) {
			xc += speed;
		}
		else if(heading == Heading.WEST) {
			xc -= speed;
		}
		else if(heading == Heading.SOUTH) {
			yc += speed;
		}
		else if(heading == null) {
			
		}
		
		if(xc > 300) {
			xc = 0;
		}
		if(yc > 300) {
			yc = 0;
		}
		if(xc < 0) {
			xc = 300;
		}
		if(yc < 0) {
			yc = 300;
		}
	}
	
	public int getXC() {
		return xc;
	}
	
	public int getYC() {
		return yc;
	}
	
	public void turn(Heading heading) {
		this.heading = heading;
	}
	
	public void update() {
		
	}
}
