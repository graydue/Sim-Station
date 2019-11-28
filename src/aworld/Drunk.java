package aworld;

public class Drunk extends World{
	
	private int speed = 10;
	
	public Drunk() {
		agents.clear();
		for(int i = 0; i <= 50; i++) {
			Agent a = new Agent((int)(Math.random()*SIZE), (int) (Math.random()*SIZE), (int)(Math.random()*speed)+1);
			int dir = (int)(Math.random()*4) + 1;
			if(dir == 1) {
				a.turn(Heading.NORTH);
			}
			else if (dir == 2) {
				a.turn(Heading.WEST);
			}
			else if(dir == 3) {
				a.turn(Heading.EAST);
			}
			else if(dir == 4) {
				a.turn(Heading.SOUTH);
			}
			agents.add(a);
		}
		this.start();
	}
	public void update() {
		for(Agent a: agents) {
			a.move();
			int dir = (int)(Math.random()*4) + 1;
			if(dir == 1)
			{
				a.turn(Heading.NORTH);
			}
			else if(dir == 2)
			{
				a.turn(Heading.WEST);
			}
			else if(dir == 3)
			{
				a.turn(Heading.EAST);
			}
			else if (dir == 4)
			{
				a.turn(Heading.SOUTH);
			}
		}
	}

}
