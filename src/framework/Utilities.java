package framework;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Utilities {

	public static void error(Exception gripe) {
		gripe.printStackTrace();
		JOptionPane.showMessageDialog(null,
				gripe.toString(),
				"OOPS!",
				JOptionPane.ERROR_MESSAGE);
	}
	
	public static void error(String gripe) {
		JOptionPane.showMessageDialog(null,
				gripe,
				"OOPS!",
				JOptionPane.ERROR_MESSAGE);
	}

	public static boolean confirm(String query) {
		int result = JOptionPane.showConfirmDialog(null,
				query, "choose one", JOptionPane.YES_NO_OPTION);
		return result == 1;
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
		/*JOptionPane.showOptionDialog() blahblah
		 * 
		 */
	}

	public static void saveChanges(Model model) {
		if (model.hasUnsavedChanges() && Utilities.confirm("current model has unsaved changes, continue?"))
			Utilities.save(model, false);
	}

	public static void save(Model model, Boolean saveAs) {
		String fName = model.getFileName();
		if (fName == null || saveAs) {
			// use JFileChooser to set the name of the model
			JFileChooser chooser = new JFileChooser();
			if(fName != null) {
				chooser.setCurrentDirectory(new File(fName));
			}
			int returnVal = chooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				fName = chooser.getSelectedFile().getPath();
				model.setFileName(fName);
			}
		}
		try {
			// write model to an ObjectOutputStream
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
			os.writeObject(model);
			model.setUnsavedChanges(false);
			os.close();
			model.setUnsavedChanges(false);			
		} catch (Exception err) {
			Utilities.error(err.getMessage());
		}
	}

	public static Model open(Model model) {
		saveChanges(model);
		String fName = model.getFileName();
		JFileChooser chooser = new JFileChooser();
		if (fName != null) {
			chooser.setCurrentDirectory(new File(fName));
		}
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			fName = chooser.getSelectedFile().getPath();
		}
		Model newModel = null;
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
			newModel = (Model)is.readObject();
			is.close();
		} catch (Exception err) {
			Utilities.error(err.getMessage());
		}
		return newModel;
		//return model;
	}

	public static JMenu makeMenu(String name, String[] items, ActionListener handler) {

		JMenu result = new JMenu(name);

		for(int i = 0; i < items.length; i++) {
			/**if (items[i] == null) {
				result.addSeparator();
			} else {*/
				JMenuItem item = new JMenuItem(items[i]);
				item.addActionListener(handler);
				result.add(item);
			//}
		}
		return result;
	}


}