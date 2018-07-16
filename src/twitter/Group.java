package twitter;
import java.util.ArrayList;
import java.util.List;

public class Group implements Component {

	private String id;
	private List<Component> components;
	private Group parent;
	
	public Group(String id) {
		this.id = id;
		components = new ArrayList<Component>();
		parent = null;
	}
	
	public Group(String id, Group parent) {
		this.id = id;
		this.parent = parent;
		components = new ArrayList<Component>();
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public Group getParent() {
		return parent;
	}

	public String toString() {
		return id;
	}

	public void accept(Visitor v) {
		v.atGroup(this);
		for (Component component : components) {
			component.accept(v);
		}
	
	}
	
	public void addToGroup(Component c) {
		components.add(c);
	}
	
	public List<Component> getMembers(){
		return components;
	}
}
