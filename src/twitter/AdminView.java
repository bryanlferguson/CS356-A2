package twitter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JOptionPane;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;

import javax.swing.tree.DefaultMutableTreeNode;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class AdminView {

	protected static AdminView instance;
	private JFrame frame;
	private JTextField txtUserId;
	private JTextField txtGroupId;
	private Group root;
	private DefaultMutableTreeNode rootNode;
	private JTree tree;

	private AdminView() {
		root = new Group("root");
		initialize();

	}

	/*
	 * Singleton implementation of AdminView.
	 */
	public static AdminView getInstance() {
		if (instance == null) {
			synchronized (AdminView.class) {
				if (instance == null) {
					instance = new AdminView();
				}
			}
		}
		return instance;
	}

	public void runWindow() {
		instance.frame.setVisible(true);
	}

	/*
	 * Initializes UI components.
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
				addUser(txtUserId.getText());
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
		addGroupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addGroup(txtGroupId.getText());
			}
		});

		// Initialize the open user view button
		JButton openUserViewButton = new JButton("Open User View");
		openUserViewButton.setBounds(415, 149, 562, 55);
		frame.getContentPane().add(openUserViewButton);
		// Adds an action listener to open the user view. First it checks if the
		// selected node is a User, then calls openUserView in the User class. If not,
		// dialogue message appears.
		openUserViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (getSelected().getUserObject() instanceof User) {
					((User) getSelected().getUserObject()).openUserView();
				} else {
					alert("Please select a user to see user view.");
				}
			}
		});

		// Initialize the total messages button.
		JButton totalMessageButton = new JButton("Show Messages Total");
		totalMessageButton.setBounds(415, 564, 275, 55);
		frame.getContentPane().add(totalMessageButton);
		// Adds action listener to use the TotalMessagesVisitor class to get the
		// number of messages. Displays appropriate text box.
		totalMessageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				TotalMessagesVisitor tmv = new TotalMessagesVisitor();
				root.accept(tmv);
				alert("There are " + tmv.getNumMessages() + " messages in total.");
			}
		});

		// Initialize the positive message percentage button.
		JButton positivePercentageButton = new JButton("Show Positive Percentage");
		positivePercentageButton.setBounds(702, 564, 275, 55);
		frame.getContentPane().add(positivePercentageButton);
		// Adds action listener to use the PositiveMessageVisitor class to get the
		// percent of positive messages. Displays appropriate text box.
		positivePercentageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				PositiveMessageVisitor pmv = new PositiveMessageVisitor();
				root.accept(pmv);
				alert("Percent of positive messages is: " + pmv.getNumPositiveMessages() + "%");
			}
		});

		// Initialize User total button.
		JButton userTotalButton = new JButton("Show User Total");
		userTotalButton.setBounds(415, 496, 275, 55);
		frame.getContentPane().add(userTotalButton);
		// Adds action listener to use the UserVisitor class to get the
		// number of users. Displays appropriate text box.
		userTotalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				UserVisitor uv = new UserVisitor();
				root.accept(uv);
				alert("There are " + uv.getNumUsers() + " users.");
			}
		});

		// Initialize Group total button
		JButton groupTotalButton = new JButton("Show Group Total");
		groupTotalButton.setBounds(702, 496, 275, 55);
		frame.getContentPane().add(groupTotalButton);
		// Adds action listener to use the UserVisitor class to get the
		// number of groups. Displays appropriate text box.
		groupTotalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				GroupVisitor gv = new GroupVisitor();
				root.accept(gv);
				alert("There are " + gv.getNumGroups() + " groups.");
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 391, 606);

		frame.getContentPane().add(scrollPane);
		tree = new JTree(rootNode);
		scrollPane.setViewportView(tree);
	}

	/*
	 * Method to add a user to the Tree. Checks to make sure that a group is
	 * selected, and that ID's are unique. If so, the tree is updated with the new
	 * group.
	 */
	private void addGroup(String newGroupID) {
		if (newGroupID.equals("")) {
			alert("Please enter an ID!");
		} else {
			DefaultMutableTreeNode tempNode = getSelected();
			if (tempNode != null && tempNode.getUserObject() instanceof Group) {
				Group tempGroup = (Group) tempNode.getUserObject();

				Group newGroup = new Group(newGroupID, tempGroup);
				DefaultMutableTreeNode newGroupNode = new DefaultMutableTreeNode(newGroup);

				if (tempGroup.addToGroup(newGroup)) {
					updateTree(newGroupNode, tempNode);
				} else {
					alert("ID already exists. Please use a unique ID and try again!");
				}
			} else {
				alert("Error! Select a group and try again!");
			}
		}
	}

	/*
	 * Method to add a user to the Tree. Checks to make sure that a group is
	 * selected, and that ID's are unique. If so, the tree is updated with the new
	 * user.
	 */
	private void addUser(String newUserID) {
		if (newUserID.equals("")) {
			alert("Please enter an ID!");
		} else {
			DefaultMutableTreeNode tempNode = getSelected();
			if (tempNode != null && tempNode.getUserObject() instanceof Group) {
				Group tempGroup = (Group) tempNode.getUserObject();

				User newUser = new User(newUserID, tempGroup);
				DefaultMutableTreeNode newUserNode = new DefaultMutableTreeNode(newUser);

				if (tempGroup.addToGroup(newUser)) {
					updateTree(newUserNode, tempNode);
				} else {
					alert("ID already exists. Please use a unique ID and try again!");
				}
			} else {
				alert("Error! Select a group and try again!");
			}
		}
	}

	/*
	 * This method is used to update the tree in the Admin View. It is called every
	 * time a new group or user is added. It also checks to make sure the container
	 * node is a Group, and will not add if it is a user.
	 */
	public void updateTree(DefaultMutableTreeNode nodeToAdd, DefaultMutableTreeNode containingNode) {
		if (containingNode.getUserObject() instanceof Group) {
			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			model.insertNodeInto(nodeToAdd, containingNode, containingNode.getChildCount());
			tree.scrollPathToVisible(new TreePath(nodeToAdd.getPath()));
		} else {
			alert("Cannot add to User. Select a group and try again!");
		}
	}

	/*
	 * Container method to pop up a dialog box.
	 */
	private void alert(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	/*
	 * Returns the currently selected node in the JTree. Returns null if none are
	 * selected.
	 */
	public DefaultMutableTreeNode getSelected() {
		try {
			return ((DefaultMutableTreeNode) tree.getLastSelectedPathComponent());
		} catch (NullPointerException e) {
			return null;
		}
	}

}
