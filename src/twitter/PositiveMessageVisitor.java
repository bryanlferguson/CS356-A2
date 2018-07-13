package twitter;

import java.util.ArrayList;
import java.util.List;

public class PositiveMessageVisitor implements Visitor {
	private int positiveMessages, totalMessages;
	private List<String> positiveWords;

	public PositiveMessageVisitor() {
		positiveMessages = 0;
		totalMessages = 0;
		positiveWords = new ArrayList<String>();
		positiveWords.add("happy");
		positiveWords.add("great");
		positiveWords.add("awesome");
		positiveWords.add("beautiful");
		
	}
	
	public double getNumPositiveMessages() {
		return ((double) positiveMessages) / totalMessages;
	}
	
	@Override
	public void atUser(User user) {
		List<String> usertweets = user.getTweets();
		for (String tweet : usertweets) {
			for (String positiveWord : positiveWords) {
				System.out.println(tweet + " " + positiveWord);
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
		//do nothing for now will TODO this laterpls
	}

	
}
