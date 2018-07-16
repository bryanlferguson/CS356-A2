package twitter;

public class TotalMessagesVisitor implements Visitor {
	
	private int numMessages;
	
	public TotalMessagesVisitor() {
		numMessages = 0;
	}
	
	public int getNumMessages() {
		return numMessages;
	}

	@Override
	public void atUser(User user) {
		numMessages += user.getTweets().size();
	}

	@Override
	public void atGroup(Group user) {
		// do nothing for groups
	}
}
