package twitter;

import java.util.Observer;

public class Driver {

	public static void main(String[] args) {
		User u = new User("Bryan");
		User u2 = new User("Anto");
		Group r = new Group("root");
		
		Group grouperino = new Group("CS 356 Study Group");
		
		r.addToGroup(u);
		r.addToGroup(u2);
		
		
		
		
	}


}
