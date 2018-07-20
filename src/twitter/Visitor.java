package twitter;

public interface Visitor {
	public void atUser(User user);
	public void atGroup(Group group);
}
