package twitter;

public class Driver {

	public static void main(String[] args) {
		AdminView.getInstance().runWindow();;
		
		User u = new User("Bryan");
		User u2 = new User("Anto");
		Group r = new Group("root");
		
		Group grouperino = new Group("CS 356 Study Group");
		
		TweetObserver to = new TweetObserver();
		
		TotalMessagesVisitor totalmessagesvisitor = new TotalMessagesVisitor();
		GroupVisitor groupvisitor = new GroupVisitor();
		PositiveMessageVisitor positivemessagesvisitor = new PositiveMessageVisitor();
		UserVisitor uservisitor = new UserVisitor();
		
		u.addObserver(to);
		u.tweet("Memes");
		
		r.addToGroup(u);
		r.addToGroup(u2);
		
		u2.tweet("Happy day!");
		
		
		r.accept(totalmessagesvisitor);
		System.out.println(totalmessagesvisitor.getNumMessages());
		
		r.accept(groupvisitor);
		System.out.println(groupvisitor.getNumGroups());
		
		r.accept(positivemessagesvisitor);
		System.out.println(positivemessagesvisitor.getNumPositiveMessages());
		
		r.accept(uservisitor);
		System.out.println(uservisitor.getNumUsers());
		
	}


}
