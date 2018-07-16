package twitter;

public class GroupVisitor implements Visitor{
	
	private int numGroups;
	
	public GroupVisitor() {
		numGroups = 0;
	}
	
	public int getNumGroups() {
		return numGroups;
	}
	
	public void atUser(User user) {
		//do nothing for users
	}

	public void atGroup(Group group) {
		numGroups++;
	}

}
