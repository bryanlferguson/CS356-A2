package twitter;

import java.util.ArrayList;
import java.util.List;

public class UserIDCheckerVisitor implements Visitor {
	
	private List<String> badUserIDs;
	private List<String> badGroupIDs;
	
	public UserIDCheckerVisitor( ) {
		badUserIDs = new ArrayList<String>();
		badGroupIDs = new ArrayList<String>();
	}

	@Override
	public void atUser(User user) {
		String temp = user.getID();
		if (temp.split(" ").length > 1) {
			badUserIDs.add(temp);
		}
	}

	@Override
	public void atGroup(Group group) {
		String temp = group.getID(); 
		if (temp.split(" ").length > 1) {
			badGroupIDs.add(temp);
		}
	}
	
	public String getBadIDs() {
		String ret = "Invalid User IDs:\n";
		if (badUserIDs.size() == 0) {
			ret += "None!\n";
		} else {
			for (String id : badUserIDs) {
				ret += id + "\n";
			}
		}
		ret += "Invalid Group IDs:\n";
		if (badGroupIDs.size() == 0) {
			ret += "None!\n";
		} else {
			for (String id : badGroupIDs) {
				ret += id + "\n";
			}
		}
		return(ret);
	}

}
