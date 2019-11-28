package aworld;

import java.util.HashSet;

import business.Simulation;

public class World extends Simulation{
	public int SIZE = 300;
	protected HashSet<Agent> agents = new HashSet();
	
	public World() {
		
	}
	
	public void update() {
		for(Agent a: agents) {
			a.move();
		}
	}
	
	public Agent findNeighbor(Agent seeker, int radius) {
		for(Agent a: agents) {
			if(Math.sqrt(Math.pow((a.xc - seeker.xc),2) + Math.pow((a.yc - seeker.yc), 2)) <= radius){
				return a;
			}
		}
		return null;
	}

	public HashSet<Agent> getAgents() {
		return new HashSet<Agent>(agents);
	}
}
