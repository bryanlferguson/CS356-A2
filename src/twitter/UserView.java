package twitter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;

public class UserView {

	private JFrame frame;
	private JTextField followUserID, tweetText;
	private DefaultListModel<String> followList, tweetList;
	private User user;

	/**
	 * Create the application.
	 */
	public UserView(User user) {
		this.user = user;
		initialize(user.getID() + "'s user view");
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String title) {
		frame = new JFrame(title);
		frame.setBounds(100, 100, 658, 622);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		tweetText = new JTextField();
		tweetText.setBounds(12, 270, 403, 52);
		frame.getContentPane().add(tweetText);
		tweetText.setColumns(10);

		JButton followUserButton = new JButton("Follow User");
		followUserButton.setBounds(324, 13, 304, 52);
		frame.getContentPane().add(followUserButton);
		followUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				user.followUser(followUserID.getText());
			}
		});

		followList = new DefaultListModel<String>();
		JList<String> followDisplay = new JList<String>(followList);
		followDisplay.setBounds(12, 78, 616, 179);
		frame.getContentPane().add(followDisplay);
		followList.addElement("Current Following:");

		followUserID = new JTextField();
		followUserID.setBounds(12, 13, 300, 52);
		frame.getContentPane().add(followUserID);
		followUserID.setColumns(10);

		JButton tweetButton = new JButton("Post Tweet");
		tweetButton.setBounds(427, 270, 201, 52);
		frame.getContentPane().add(tweetButton);
		tweetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				user.tweet(tweetText.getText());
			}
		});

		tweetList = new DefaultListModel<String>();
		JList<String> tweetDisplay = new JList<String>(tweetList);
		tweetDisplay.setBounds(12, 351, 616, 211);
		frame.getContentPane().add(tweetDisplay);

		JLabel lblMessages = new JLabel("Messages");
		lblMessages.setBounds(12, 332, 155, 16);
		frame.getContentPane().add(lblMessages);

		repopulateFollowers();
		repopulateTweets();
	}

	public void updateFollowers(String userID) {
		followList.addElement(" - " + userID);
	}

	private void repopulateTweets() {
		for (String tweet : user.getNewsFeed()) {
			tweetList.addElement(tweet);
		}
	}

	private void repopulateFollowers() {
		for (User following : user.getFollowing()) {
			if (!following.getID().equals(user.getID())) {
				followList.addElement(following.getID());
			}
		}
	}

	public void updateTweetList(String message) {
		tweetList.addElement(message);
	}

}
