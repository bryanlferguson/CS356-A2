package twitter;

import java.util.Observer;

public class Driver {

	public static void main(String[] args) {
		User u = new User("Bryan");
		TweetObserver to = new TweetObserver();
		
		u.addObserver(((Observer) to));
		
		u.tweet("what");
	}

}
