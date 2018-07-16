package twitter;

import java.util.Observable;
import java.util.Observer;

public class TweetObserver implements Observer {
	User user;

	public TweetObserver(User user) {
		this.user = user;
	}

	/*
	 * Observer implementation to update user's news feeds when new tweets are
	 * added.
	 */
	@Override
	public void update(Observable s, Object arg) {
		if (s instanceof User) {
			user.updateTweetView(((User) s).getID() + ": " + ((User) s).getLastTweet());
		}
	}
}
