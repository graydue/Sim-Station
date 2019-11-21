package framework;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public abstract class AppPanel extends JPanel implements Observer{
	protected Model model;
	private ActionListener listener;
	protected HashSet<View> views = new HashSet<View>();
	
	public AppPanel(Model model, ActionListener listener) {
		setModel(model);
		this.listener = listener;
	}
	
	public abstract void update(Observable o, Object arg);
	
	public void setModel(Model model) {
		if(this.model != null) {
			this.model.deleteObserver(this);
		}
		this.model = model;
		if(this.model != null) {
			this.model.addObserver(this);
			update(this.model, null);
		}
	}
	
	public void add(View view) {
		super.add(view);
		views.add(view);
	}
}
