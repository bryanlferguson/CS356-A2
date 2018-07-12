package twitter;

import java.util.ArrayList;
import java.util.List;

public class UserTree implements Component{
	
	private List<Component> components;
	
	public UserTree() {
		components = new ArrayList<Component>();
	}
	public void setID(String id) {
		
	}
	


	public String getID() {
		return null;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
	@Override
	public void addGroup(Group g) {
		// TODO Auto-generated method stub
		
	}

}
