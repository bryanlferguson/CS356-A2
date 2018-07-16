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
	
	public User(String id, Group parent) {
		this.id = id;
		followers = new ArrayList<User>();;
		following = new ArrayList<User>();
		tweets = new ArrayList<String>();
		newsFeed = new ArrayList<String>();
		this.parent = parent;
		follow(this);
	}
	
	public void accept(Visitor v) {
		v.atUser(this);
	}
	
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
	
	public void tweet(String s) {
		addTweet(s);
		setChanged();
		notifyObservers(s);
	}

	public String toString() {
		return "[User] " + id;
	}
	
	public List<String> getTweets() {
		return tweets;
	}
	
	public void openUserView() {
		userView = new UserView(this);
	}
	
	public Group getParent() {
		return parent;
	}

	public void followUser(String userToFollow) {
		Group root = getParent().getRoot();
		UserFinderVisitor ufv = new UserFinderVisitor(userToFollow);		
		root.accept(ufv);
		follow(ufv.getTarget());
		userView.updateFollowers(userToFollow);
	}
	
	public List<String> getNewsFeed(){
		return newsFeed;
	}
	
	public void updateTweetView(String message) {
		userView.updateTweetList(message);
		newsFeed.add(message);
	}
}
