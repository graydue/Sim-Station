package framework;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JMenuBar;

public class View extends JComponent implements Observer {

	private static final long serialVersionUID = 1L;
	protected String title; 
	protected Model newModel;
	protected JMenuBar menuBar;
	

	public View(Model model) {
		setModel(model);
	}

	public void update(Observable o, Object arg) {
		repaint();
	}

	public Model getModel() {
		return newModel;
	}

	public void setModel(Model newModel) {
		if(this.newModel != null) 
		{
			this.newModel.deleteObserver(this);
		}
		this.newModel = newModel;
		if(this.newModel != null) 
		{
			this.newModel.addObserver(this);
			this.update(this.newModel, null); 
		}
	}
}
