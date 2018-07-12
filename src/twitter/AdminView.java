package twitter;

public class AdminView {
	
	protected static AdminView instance;
	
	protected AdminView() {
		
	}
	
	public static AdminView getInstance() {
		if (instance == null) {
			instance = new AdminView();
		} 
		return instance;
	}
	
	
}
