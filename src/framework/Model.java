package framework;

import java.io.Serializable;
import java.util.Observable;

public abstract class Model extends Observable implements Serializable{
	private String fileName = null;
	private boolean unsavedChanges = false;
	
	public void changed() {
		this.unsavedChanges = true;
		this.setChanged();
		this.notifyObservers();
		this.clearChanged();
	}
	
	public boolean hasUnsavedChanges() {
		return unsavedChanges;
	}
	
	public void setUnsavedChanges(boolean bool) {
		unsavedChanges = bool;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String name) {
		fileName = name;
	}
}
