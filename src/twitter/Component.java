package twitter;

public interface Component {
	
	public String toString();
	public void accept(Visitor visitor);
	public Group getParent();
}
