package twitter;

public interface Composite {
	
	public void setID(String id);
	public String getID();
	public void accept(Visitor v);
	
}
