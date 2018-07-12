package twitter;

public class Group implements Composite {

	private String id;
	
	public Group(String id) {
		this.id = id;
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
	
}
