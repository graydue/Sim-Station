package presentation;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import aworld.Agent;
import aworld.World;
import business.Simulation;
import framework.AppPanel;
import framework.Model;

public class SimPanel extends AppPanel {
	JTextField clockCycles = new JTextField(10);
	JButton start, suspend, resume, stop;
	JLabel clock = new JLabel("clock ");
	JPanel controls, clockDisp, startPanel, suspendPanel, resumePanel, stopPanel;
	World world;
	Graphics gc;

	public SimPanel(World model, ActionListener listener) {
		super(model, listener);
		this.setLayout(new GridLayout(1, 2));

		controls = new JPanel();
		controls.setLayout(new GridLayout(5,1));
		clockDisp = new JPanel();
		clockDisp.setLayout(new FlowLayout());
		clockDisp.add(clock);
		clockDisp.add(clockCycles);
		
		startPanel = new JPanel();
		startPanel.setLayout(new FlowLayout());
		suspendPanel = new JPanel();
		suspendPanel.setLayout(new FlowLayout());
		resumePanel = new JPanel();
		resumePanel.setLayout(new FlowLayout());
		stopPanel = new JPanel();
		stopPanel.setLayout(new FlowLayout());
		
		start = new JButton("Start");
		start.addActionListener(listener);
		suspend = new JButton("Suspend");
		suspend.addActionListener(listener);
		resume = new JButton("Resume");
		resume.addActionListener(listener);
		stop = new JButton("Stop");
		stop.addActionListener(listener);
		
		startPanel.add(start);
		suspendPanel.add(suspend);
		resumePanel.add(resume);
		stopPanel.add(stop);
		
		controls.add(clockDisp);
		controls.add(startPanel);
		controls.add(suspendPanel);
		controls.add(resumePanel);
		controls.add(stopPanel);
		
		SimView sv = new SimView(model);
		
		this.add(controls);
		SimView sim = new SimView(model);
		this.add(sim);
		sim.repaint();
	}

	public void update(Observable o, Object arg) {
		if(clockCycles != null) {
			if(o instanceof Simulation) {
				Simulation s = (Simulation) o;
				clockCycles.setText(Long.toString(s.getClockTime()));
			}
		}
	}

}
