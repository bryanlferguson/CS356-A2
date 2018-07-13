package twitter;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminView {
	
	protected static AdminView instance;
	private JFrame frame;
	private JTextField newUserID, newGroupID;
	private JPanel left;
	private Group root;
	
	private AdminView() {
		root = new Group("root");
		initFrame(2000, 1200);
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
	
	private void initFrame(int width, int height) {
		frame = new JFrame("Admin View");
		frame.setVisible(true);
		frame.setSize(width, height);
	}
	
	private void initTreeView() {
		left = new JPanel(new BorderLayout());
		frame.add(left);
	}
	
}
