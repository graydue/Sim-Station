package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import aworld.Agent;
import aworld.World;
import framework.View;

public class SimView extends View{
	
	public SimView(World world) {
		super(world);
		this.setPreferredSize(new Dimension(world.SIZE, world.SIZE));
	}
	
	public void paintComponent(Graphics gc) {
		Graphics2D g2 = (Graphics2D) gc;
		if(newModel != null && newModel instanceof World) {
			World w = (World) newModel;
			g2.drawRect(0, 0, w.SIZE + 4, w.SIZE + 9);
			for(Agent a: w.getAgents()) {
				g2.setColor(Color.RED);g2.fillOval(a.getXC(), a.getYC(), 10, 10);
			}
		}
	}
}
