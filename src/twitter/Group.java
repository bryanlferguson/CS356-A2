package twitter;

import java.util.ArrayList;
import java.util.List;

public class Group implements Composite {

	private String id;
	private List<Composite> members;
	
	public Group(String id) {
		this.id = id;
		members = new ArrayList<Composite>();
	}
	
	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return id;
	}

	public void accept(Visitor v) {
		 v.visit(this);
	}
	
	public void addToGroup(Composite c) {
		members.add(c);
	}
	
	public List<Composite> getMembers(){
		return members;
	}
	
}
