package twitter;

public class AdminView {
	
	protected static AdminView instance;
	
	private AdminView() {
		
	}
	
	public static AdminView getInstance() {
		if (instance == null) {
			synchronized(AdminView.class) {
				if (instance == null) {
					instance = new AdminView();
				}
			}
		} 
		return instance;
	}
	
	
}
