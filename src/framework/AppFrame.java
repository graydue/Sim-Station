package framework;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import framework.AppFactory;

public class AppFrame extends JFrame implements ActionListener {

	protected AppPanel panel;
	protected Model model;
	protected AppFactory factory;

	public AppFrame(AppFactory factory) {
		this.factory = factory;
		model = factory.makeModel();
		panel = factory.makeAppPanel(model, this);
		getContentPane().add(panel);
		setJMenuBar(createMenuBar());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(factory.getTitle());
		setSize(500, 500);
	}

	public void display() {
		this.setVisible(true);
	}

	public void setModel(Model model) {
		this.model = model;
		this.panel = factory.makeAppPanel(model, this);
		getContentPane().add(panel);
		panel.revalidate();
		panel.repaint();
	}

	protected JMenuBar createMenuBar() {
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = Utilities.makeMenu("File", new String[] {"New", "Open", "Save", "SaveAs", "Exit"}, this);
		JMenu helpMenu = Utilities.makeMenu("Help", new String[] {"About", "Contents"}, this);
		JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands(), this);

		bar.add(fileMenu);
		bar.add(editMenu);
		bar.add(helpMenu);

		return bar;
	}

	public void actionPerformed(ActionEvent ae) {
		String cmmd = ae.getActionCommand();
		if (cmmd == "Save") {
			Utilities.save(model, false);
		} else if (cmmd == "SaveAs") {
			Utilities.save(model, true);
		} else if (cmmd == "Open") {
			model = Utilities.open(model);
			if(model != null) {
				setModel(model);
			}
		} else if (cmmd == "New") {
			Utilities.saveChanges(model);
			setModel(factory.makeModel());
		} else if (cmmd == "Exit") {
			Utilities.saveChanges(model);
			System.exit(1);
		} else if (cmmd == "About") {
			Utilities.inform(factory.about());
		} else if (cmmd == "Contents") {
			Utilities.inform(factory.getHelp());
		} else {
			Command command = factory.makeEditCommand(model, cmmd);
			CommandProcessor.execute(command);
		}

	}
}