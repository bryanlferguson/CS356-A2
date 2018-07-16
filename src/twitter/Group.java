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

	/*
	 * Constructor used for the root group.
	 */
	public Group(String id) {
		this.id = id;
		components = new ArrayList<Component>();
		parent = this;
		ids = new HashSet<String>();
	}

	/*
	 * Constructor used for all other groups. Keeps track of parent group to easily
	 * locate root.
	 */
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

	/*
	 * toString override. Used to display that this Component is a group in the
	 * JTree.
	 */
	@Override
	public String toString() {
		return "[Group] " + id;
	}

	public String getID() {
		return id;
	}

	public void accept(Visitor v) {
		v.atGroup(this);
		for (Component component : components) {
			component.accept(v);
		}

	}

	/*
	 * Checks if the ID is unique before adding component to group. Also adds ID to
	 * the root groups HashSet to store ID's.
	 */
	public boolean addToGroup(Component c) {
		if (getRoot().getIDs().contains(c.getID())) {
			return false;
		}
		components.add(c);
		getRoot().getIDs().add(c.getID());
		return true;
	}
	
	public Set<String> getIDs() {
		return ids;
	}
	
	public boolean equals(Group g) {
		return this.id.toLowerCase().equals(g.getID().toLowerCase());
	}
	
	/*
	 * Traverses parent nodes until root is found.
	 */
	public Group getRoot() {
		Group root = parent;
		while (parent.getID() != "root") {
			root = parent.getParent();
		}
		return root;
	}

	public List<Component> getMembers() {
		return components;
	}
}
