package twitter;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;

public class AdminView {
	
	protected static AdminView instance;
	private JFrame frame;
	private JTextField newUserID, newGroupID;
	private JPanel left;
	private JTree tree;
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
		frame.setSize(2000, 1200);
        frame.setLocation(100, 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
	
	private void initTreeView() {
		left = new JPanel();
		left.setLayout(new BorderLayout());

		tree = new JTree();
		tree.setVisible(true);

		left.add(tree);
		
		frame.add(left);
	}
	
}
