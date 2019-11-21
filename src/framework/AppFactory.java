package framework;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public interface AppFactory {
	
    public Model makeModel();
    public AppPanel makeAppPanel(Model model, ActionListener listener);
    public String[] getEditCommands();
    public Command makeEditCommand(Model model, String type);
    public String getTitle();
    public String [] getHelp();
    public String about();

}