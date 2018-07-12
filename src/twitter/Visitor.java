package twitter;

public interface Visitor {
	public void visit(Group g);
	public void visit(User u);
	public void visit(UserTree ut);
}
