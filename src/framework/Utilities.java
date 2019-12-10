package framework;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Utilities {

	public static void error(Exception gripe) {
		gripe.printStackTrace();
		JOptionPane.showMessageDialog(null, gripe.toString(), "OOPS!", JOptionPane.ERROR_MESSAGE);
	}

	public static void error(String gripe) {
		JOptionPane.showMessageDialog(null, gripe, "OOPS!",	JOptionPane.ERROR_MESSAGE);
	}

	public static boolean confirm(String query) {
		int result = JOptionPane.showConfirmDialog(null, query, "Choose one: ", JOptionPane.YES_NO_OPTION);
		return result == 0;
	}

	public static void inform(String info) {
		JOptionPane.showMessageDialog(null, info);

	}

	public static String ask(String query) {
		return JOptionPane.showInputDialog(null,query);
	}

	public static void inform(String [] items) {
		String helpString = "";
		for(int i = 0; i < items.length; i++) {
			helpString = helpString + "\n" + items[i]; 
		}
		inform(helpString);
	}

	public static void saveChanges(Model model) {
		if(model == null) {
			model.setUnsavedChanges(false);
		}
		if (model.hasUnsavedChanges() && Utilities.confirm("Current model has unsaved changes. Do you want to save them?")) {
			Utilities.save(model, false);
			model.setUnsavedChanges(true);
		}
		model.setUnsavedChanges(false);
	}

	public static void save(Model model, Boolean saveAs) {
		String fName = model.getFileName();
		if (fName == null || saveAs) {
			fName = Utilities.getFileName(fName, "Save");
			model.setFileName(fName);
		}
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
			os.writeObject(model);
			model.setUnsavedChanges(false);
			JOptionPane.showMessageDialog(new JPanel(), "File successfully saved.", "", JOptionPane.INFORMATION_MESSAGE);
			os.close();
		} catch (Exception err) {
			model.setUnsavedChanges(true);
		}
	}

	public static Model open(Model model) {
		if(model.hasUnsavedChanges()) {
			if(model.hasUnsavedChanges()== true) {
				return null;
			}
		}
		String name = Utilities.getFileName(model.getFileName(), "Open");
		if(name == null) {
			return null;
		}
		Model newModel = null;
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(name));
			newModel = (Model)is.readObject();
			is.close();
		} catch (Exception err) {
			Utilities.error(err.getMessage());
		}
		return newModel;
	}

	private static String getFileName(String fName, String action) {
		JFileChooser chooser = new JFileChooser();
		String result = null;
		if (fName != null) {
			chooser.setCurrentDirectory(new File(fName));
		}
		int returnVal = chooser.showDialog(null, action);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			result = chooser.getSelectedFile().getPath();
		} 
		else if (returnVal == JFileChooser.CANCEL_OPTION){
		}
		return result;
	}

	public static JMenu makeMenu(String name, String[] items, ActionListener handler) {
		JMenu result = new JMenu(name);
		for(String command: items) {
			JMenuItem item = new JMenuItem(command);
			item.addActionListener(handler);
			result.add(item);
		}
		return result;
	}


}