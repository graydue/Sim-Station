package presentation;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import aworld.Bird;
import aworld.Drunk;
import aworld.World;
import business.Resume;
import business.Simulation;
import business.Start;
import business.Stop;
import business.Suspend;
import framework.AppFactory;
import framework.AppPanel;
import framework.Command;
import framework.Model;

public class SimFactory implements AppFactory {

	@Override
	public Model makeModel() {
		String[] simType = new String[] {"Random Walk", "Flocking"};
		int response = JOptionPane.showOptionDialog(null,  "Pick a simulation.", null, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, simType, simType[0]); 
		if(response == 0) {
			return new Drunk();
		}
		else if(response == 1) {
			return new Bird();
		}
		return null;
	}

	@Override
	public AppPanel makeAppPanel(Model model, ActionListener listener) {
		return new SimPanel((World) model, listener);
	}

	@Override
	public String[] getEditCommands() {
		return new String[] { "Start", "Suspend", "Resume", "Stop"};
	}

	@Override
	public Command makeEditCommand(Model model, String type) {
		if(type.equals("Start")) {
			return new Start((Simulation)model);
		}
		else if(type.equals("Suspend")){
			return new Suspend((Simulation)model);
		}
		else if(type.equals("Resume")) {
			return new Resume((Simulation)model);
		}
		else if(type.equals("Stop")) {
			return new Stop((Simulation)model);
		}
		else {
			return null;
		}
	}

	@Override
	public String getTitle() {
		return "SimStation";
	}

	@Override
	public String[] getHelp() {
		// TODO Auto-generated method stub
		return new String[] {"'Start' runs the simulation.", "'Suspend' pauses the simulation.", "'Stop' will terminate the simulation.", "'Resume' will continue the operation after suspending it.", "Access the file menu to begin a new simulation."};
	}

	@Override
	public String about() {
		// TODO Auto-generated method stub
		return "A program that uses threading to simulate processes over time.";
	}

}
