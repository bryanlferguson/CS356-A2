package twitter;

import java.util.Observable;
import java.util.Observer;

public class TweetObserver implements Observer {
	
	public void update(Observable s, Object arg) {
		if (s instanceof User) {
			((User) s).updateTweetView(((User) s).toString() + ": " + ((User) s).getLastTweet());
		}
	}
}
