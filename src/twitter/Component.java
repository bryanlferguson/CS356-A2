package twitter;

public interface Component {
	
	public String getID();
	public void accept(Visitor visitor);
}
