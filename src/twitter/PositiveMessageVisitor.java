package twitter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PositiveMessageVisitor implements Visitor {
	private int positiveMessages;
	private List<String> positiveWords;

	public PositiveMessageVisitor() {
		positiveMessages = 0;
		positiveWords = new ArrayList<String>();
		positiveWords.add("happy");
		positiveWords.add("great");
		positiveWords.add("awesome");
		positiveWords.add("beautiful");
		
	}
	
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
			
		}
	}

	@Override
	public void atGroup(Group group) {
		//do nothing for now will TODO this laterpls
	}

	
}
