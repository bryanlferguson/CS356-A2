package twitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Subject extends Observable {
	private String id;
	
	public Subject(String id) {
		 this.id = id;
	}
	
	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return id;
	}
}
