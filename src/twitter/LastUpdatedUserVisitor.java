package twitter;

public class LastUpdatedUserVisitor implements Visitor {
	
	private String userID;
	private long latestUpdateTime;
	
	public LastUpdatedUserVisitor () {
		userID = "No Users! Make a user and try again!";
		latestUpdateTime = -1;
	}
	
	public String getLastUpdatedUser() {
		return userID;
	}
	
	@Override
	public void atUser(User user) {
		if (user.getLastUpdateTime() > latestUpdateTime) {
			latestUpdateTime = user.getLastUpdateTime();
			userID = user.getID();
		}
	}

	@Override
	public void atGroup(Group group) {
		// TODO Auto-generated method stub
		
	}
	
}
