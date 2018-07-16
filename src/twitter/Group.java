package twitter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group implements Component {

	private String id;
	private List<Component> components;
	private Set<String> ids;
	private Group parent;
	
	public Group(String id) {
		this.id = id;
		components = new ArrayList<Component>();
		parent = this;
		ids = new HashSet<String>();
	}
	
	public Group(String id, Group parent) {
		this.id = id;
		this.parent = parent;
		components = new ArrayList<Component>();
		ids = new HashSet<String>();
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
	
	public boolean addToGroup(Component c) {
		if (getRoot().getIDs().contains(c.toString())){
			System.out.println("yesples");
			return false;
		}
		components.add(c);
		getRoot().getIDs().add(c.toString());
		return true;
	}
	
	public Set<String> getIDs() {
		return ids;
	}
	
	public boolean equals(Group g) {
		return this.id.toLowerCase().equals(g.toString().toLowerCase());
	}
	
	public Group getRoot() {
		Group root = parent;
		while (parent.toString() != "root") {
			root = parent.getParent();
		}
		return root;
	}
	
	public List<Component> getMembers(){
		return components;
	}
}
