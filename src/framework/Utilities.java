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
//		saveChanges(model);
//		String fName = model.getFileName();
//		JFileChooser chooser = new JFileChooser();
//		if (fName != null) {
//			chooser.setCurrentDirectory(new File(fName));
//		}
//		int returnVal = chooser.showOpenDialog(null);
//		if(returnVal == JFileChooser.APPROVE_OPTION) {
//			fName = chooser.getSelectedFile().getPath();
//		}
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
		//return model;
	}

	private static String getFileName(String fName, String action) {
		 JFileChooser chooser = new JFileChooser();
	        String result = null;
	        if (fName != null) {
	            // open chooser in directory of fName
	            chooser.setCurrentDirectory(new File(fName));
	        }
	        int returnVal = chooser.showDialog(null, action);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            result = chooser.getSelectedFile().getPath();
	        } else if (returnVal == JFileChooser.CANCEL_OPTION)
	        {
	        	//Do nothing
	        }
	        return result;
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