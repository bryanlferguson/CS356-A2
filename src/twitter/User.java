package twitter;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

public class User extends Observable {
	private String id;
	//private final DefaultListModel listModel = new DefaultListModel();
	List<User> followers;
	List<User> following;
	List<String> tweets;
	
	
	public User(String id) {
		this.id = id;
		followers = new ArrayList<User>();
		following = new ArrayList<User>();
		tweets = new ArrayList<String>();
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	public void follow(User u) {
		u.addFollower(this);
		following.add(u);
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

	public String getID() {
		return id;
	}

}
