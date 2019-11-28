package aworld;

import java.util.HashSet;

import business.Simulation;

public class World extends Simulation{
	public int SIZE = 300;
	private HashSet<Agent> agents = new HashSet();
	
	public World() {
		
	}
	
	public void update() {
		
	}
	
	public Agent findNeighbor(Agent seeker, int radius) {
		return null;
	}
}
