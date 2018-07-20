package twitter;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class AdminViewBeta {

	private JFrame frame;
	private JTextField txtUserId;
	private JTextField txtGroupId;
	private Group root;
	private DefaultMutableTreeNode rootNode;
	private JTree tree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminViewBeta window = new AdminViewBeta();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminViewBeta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Basic UI Components
				frame = new JFrame("Admin View");
				frame.setBounds(100, 100, 1007, 679);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);

				rootNode = new DefaultMutableTreeNode(root);

				txtUserId = new JTextField();
				txtUserId.setBounds(415, 13, 275, 55);
				frame.getContentPane().add(txtUserId);
				txtUserId.setColumns(10);

				// Initialize Add User Button
				JButton addUserButton = new JButton("Add User");
				// Adds an action listener to add user with the text from the txtUserID
				// JTextField
				addUserButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

					}
				});
				addUserButton.setBounds(702, 13, 275, 55);
				frame.getContentPane().add(addUserButton);

				txtGroupId = new JTextField();
				txtGroupId.setBounds(415, 81, 275, 55);
				frame.getContentPane().add(txtGroupId);
				txtGroupId.setColumns(10);

				// Initialize the add group button
				JButton addGroupButton = new JButton("Add Group");
				addGroupButton.setBounds(702, 81, 275, 55);
				frame.getContentPane().add(addGroupButton);
				// Adds an action listener to add a group with the text from the txtGroupID
				// JTextField


				// Initialize the open user view button
				JButton openUserViewButton = new JButton("Open User View");
				openUserViewButton.setBounds(415, 149, 562, 55);
				frame.getContentPane().add(openUserViewButton);
				// Adds an action listener to open the user view. First it checks if the
				// selected node is a User, then calls openUserView in the User class. If not,
				// dialogue message appears.

				// Initialize the total messages button.
				JButton totalMessageButton = new JButton("Show Messages Total");
				totalMessageButton.setBounds(415, 496, 275, 55);
				frame.getContentPane().add(totalMessageButton);
				// Adds action listener to use the TotalMessagesVisitor class to get the
				// number of messages. Displays appropriate text box.


				// Initialize the positive message percentage button.
				JButton positivePercentageButton = new JButton("Show Positive Percentage");
				positivePercentageButton.setBounds(702, 496, 275, 55);
				frame.getContentPane().add(positivePercentageButton);
				// Adds action listener to use the PositiveMessageVisitor class to get the
				// percent of positive messages. Displays appropriate text box.


				// Initialize User total button.
				JButton userTotalButton = new JButton("Show User Total");
				userTotalButton.setBounds(415, 428, 275, 55);
				frame.getContentPane().add(userTotalButton);
				// Adds action listener to use the UserVisitor class to get the
				// number of users. Displays appropriate text box.


				// Initialize Group total button
				JButton groupTotalButton = new JButton("Show Group Total");
				groupTotalButton.setBounds(702, 428, 275, 55);
				frame.getContentPane().add(groupTotalButton);
				// Adds action listener to use the UserVisitor class to get the
				// number of groups. Displays appropriate text box.


				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(12, 13, 391, 606);

				frame.getContentPane().add(scrollPane);
				tree = new JTree(rootNode);
				scrollPane.setViewportView(tree);
				
				JButton btnNewButton = new JButton("Verify IDs");
				btnNewButton.setBounds(415, 564, 275, 55);
				frame.getContentPane().add(btnNewButton);
				
				JButton btnNewButton_1 = new JButton("Last Updated User");
				btnNewButton_1.setBounds(702, 564, 275, 55);
				frame.getContentPane().add(btnNewButton_1);
	}
}
