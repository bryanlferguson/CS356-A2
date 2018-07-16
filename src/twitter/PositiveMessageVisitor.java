package twitter;

import java.util.ArrayList;
import java.util.List;

public class PositiveMessageVisitor implements Visitor {
	private int positiveMessages, totalMessages;
	private List<String> positiveWords;

	/*
	 * Initializes list of happy words.
	 */
	public PositiveMessageVisitor() {
		positiveMessages = 0;
		totalMessages = 0;
		positiveWords = new ArrayList<String>();
		positiveWords.add("happy");
		positiveWords.add("great");
		positiveWords.add("awesome");
		positiveWords.add("beautiful");
		positiveWords.add("terrific");
		positiveWords.add(":)");

	}

	public double getNumPositiveMessages() {
		if (totalMessages == 0) {
			return 0.0;
		}
		return ((double) positiveMessages) / totalMessages * 100;
	}

	/*
	 * Searches user's messages for positive words in their tweets. If one is found,
	 * loop is broken as that tweet is already considered positive.
	 */
	@Override
	public void atUser(User user) {
		List<String> usertweets = user.getTweets();
		for (String tweet : usertweets) {
			for (String positiveWord : positiveWords) {
				if (tweet.toLowerCase().indexOf(positiveWord.toLowerCase()) != -1) {
					positiveMessages++;
					break;
				}
			}
			totalMessages++;
		}
	}

	@Override
	public void atGroup(Group group) {
		// do nothing for groups.
	}

}
