package twitter;

import java.util.Observable;
import java.util.Observer;

public class TweetObserver implements Observer {
	private String tweet;
	
	public void update(Observable s, Object arg) {
		if (s instanceof User) {
			System.out.println("Tweet added!");
			System.out.println(((User) s).getID() + ": " + ((User) s).getLastTweet());
		}
		
	}
}
