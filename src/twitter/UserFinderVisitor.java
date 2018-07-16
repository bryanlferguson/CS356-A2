package twitter;

public class UserFinderVisitor implements Visitor {
		
	private User target;
	private String targetID;
	
	public UserFinderVisitor(String targetID) {
		this.targetID = targetID;
		target = null;
	}
	
	public User getTarget() {
		return target;
	}
	
	@Override
	public void atUser(User user) {
		if (targetID.toLowerCase().equals(user.getID().toLowerCase())) {
			target = user;
		}
	}

	@Override
	public void atGroup(Group user) {
		//Do nothing
	}

}
