package twitter;

import java.util.List;
import java.util.Observable;
import java.util.ArrayList;

public class User extends Observable implements Component {
	private String id;
	private List<User> followers;
	private List<User> following;
	private List<String> tweets;
	private List<String> newsFeed;
	private UserView userView;
	private Group parent;
	private final long creationTime;
	private long lastUpdateTime;

	/*
	 * Constructor. Initializes list. Also follows self so that their own tweets
	 * will be seen.
	 */
	public User(String id, Group parent) {
		this.id = id;
		followers = new ArrayList<User>();
		;
		following = new ArrayList<User>();
		tweets = new ArrayList<String>();
		newsFeed = new ArrayList<String>();
		this.parent = parent;
		follow(this);
		creationTime = 	System.currentTimeMillis();
		lastUpdateTime = System.currentTimeMillis();
	}

	@Override
	public void accept(Visitor v) {
		v.atUser(this);
	}

	/*
	 * Follows a user and adds an observer.
	 */
	public void follow(User u) {
		u.addFollower(this);
		following.add(u);
		u.addObserver(new TweetObserver(this));
	}

	public String getID() {
		return id;
	}

	public List<User> getFollowing() {
		return following;
	}

	/*
	 * Retrieves the last tweet the user has made.
	 */
	public String getLastTweet() {
		return tweets.get(tweets.size() - 1);
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void addFollower(User u) {
		followers.add(u);
	}

	public void addTweet(String s) {
		tweets.add(s);
	}

	/*
	 * Adds a new tweet and notifies observers.
	 */
	public void tweet(String s) {
		addTweet(s);
		setChanged();
		notifyObservers(s);
	}

	/*
	 * toString override. Used to display that this Component is a user in the
	 * JTree.
	 */
	@Override
	public String toString() {
		return "[User] " + id;
	}

	public List<String> getTweets() {
		return tweets;
	}

	/*
	 * Creates the user view and displays it. Called when the open user view button
	 * is pressed
	 */
	public void openUserView() {
		userView = new UserView(this);
	}

	public Group getParent() {
		return parent;
	}

	/*
	 * Uses a UserFinderVisitor to search through all users and return the reference
	 * of the user with the matching ID. It then follows the user and updates the
	 * users following view.
	 */
	public void followUser(String userToFollow) {
		Group root = getParent().getRoot();
		UserFinderVisitor ufv = new UserFinderVisitor(userToFollow);
		root.accept(ufv);
		follow(ufv.getTarget());
		userView.updateFollowers(userToFollow);
	}

	public List<String> getNewsFeed() {
		return newsFeed;
	}

	/*
	 * Updates messages view. Also adds message to newsfeed for persistence after
	 * closing user view.
	 */
	public void updateTweetView(String message) {
		userView.updateTweetList(message);
		newsFeed.add(message);
		lastUpdateTime = System.currentTimeMillis();
	}

	@Override
	public long getCreationTime() {
		return creationTime;
	}
	
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	public void setLastUpdateTime(long time) {
		lastUpdateTime = time;
	}
}
