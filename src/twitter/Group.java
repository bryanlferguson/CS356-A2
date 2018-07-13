package twitter;
import java.util.ArrayList;
import java.util.List;

public class Group implements Component {

	private String id;
	private List<Component> components;
	
	public Group(String id) {
		this.id = id;
		components = new ArrayList<Component>();
	}
	
	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return id;
	}

	public void accept(Visitor v) {
		v.atGroup(this);
	
	}
	
	public void addToGroup(Component c) {
		components.add(c);
	}
	
	public List<Component> getMembers(){
		return components;
	}

	public void addGroup(Group g) {
		
	}

	public void visit(Component component) {
		
	}
}
