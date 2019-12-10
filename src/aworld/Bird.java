package aworld;

public class Bird extends World{
	
	public Bird() {
		agents.clear();
		for(int i = 0; i <= 50; i++) {
			Agent a = new Agent((int)(Math.random()*SIZE), (int) (Math.random()*SIZE), (int)(Math.random()*10)+1);
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
			Agent neighbor = findNeighbor(a, 10);
			a.heading = neighbor.heading;
			a.speed = neighbor.speed;
			a.move();
		}
	}
}
