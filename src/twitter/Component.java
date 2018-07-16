package twitter;

/*
 * Superclass of Group and User.
 */
public interface Component {
	public String toString();
	public void accept(Visitor visitor);
	public Group getParent();
	public String getID();
}
