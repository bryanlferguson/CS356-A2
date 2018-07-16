package twitter;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

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

	private void initialize() {
		frame = new JFrame("Admin View");
		frame.setBounds(100, 100, 1007, 679);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		rootNode = new DefaultMutableTreeNode(root);

		txtUserId = new JTextField();
		txtUserId.setBounds(415, 13, 275, 55);
		frame.getContentPane().add(txtUserId);
		txtUserId.setColumns(10);

		JButton btnNewButton = new JButton("Add User");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addUser(txtUserId.getText());
			}
		});
		btnNewButton.setBounds(702, 13, 275, 55);
		frame.getContentPane().add(btnNewButton);

		txtGroupId = new JTextField();
		txtGroupId.setBounds(415, 81, 275, 55);
		frame.getContentPane().add(txtGroupId);
		txtGroupId.setColumns(10);

		// Initialize the add group button, set its location, and add an Action Listener
		// to add a group when it is clicked
		JButton addGroupButton = new JButton("Add Group");
		addGroupButton.setBounds(702, 81, 275, 55);
		frame.getContentPane().add(addGroupButton);
		addGroupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addGroup(txtGroupId.getText());
			}
		});

		JButton openUserViewButton = new JButton("Open User View");
		openUserViewButton.setBounds(415, 149, 562, 55);
		frame.getContentPane().add(openUserViewButton);
		openUserViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (getSelected().getUserObject() instanceof User) {
					((User) getSelected().getUserObject()).openUserView();
				} else {
					alert("Please select a user to see user view.");
				}
			}
		});

		JButton totalMessageButton = new JButton("Show Messages Total");
		totalMessageButton.setBounds(415, 564, 275, 55);
		frame.getContentPane().add(totalMessageButton);
		totalMessageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				TotalMessagesVisitor tmv = new TotalMessagesVisitor();
				root.accept(tmv);
				alert("There are " + tmv.getNumMessages() + " messages in total.");
			}
		});

		JButton positivePercentageButton = new JButton("Show Positive Percentage");
		positivePercentageButton.setBounds(702, 564, 275, 55);
		frame.getContentPane().add(positivePercentageButton);
		positivePercentageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				PositiveMessageVisitor pmv = new PositiveMessageVisitor();
				root.accept(pmv);
				alert("Percent of positive messages is: " + pmv.getNumPositiveMessages() + " percent");
			}
		});

		JButton userTotalButton = new JButton("Show User Total");
		userTotalButton.setBounds(415, 496, 275, 55);
		frame.getContentPane().add(userTotalButton);
		userTotalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				UserVisitor uv = new UserVisitor();
				root.accept(uv);
				alert("There are " + uv.getNumUsers() + " users.");
			}
		});

		JButton groupTotalButton = new JButton("Show Group Total");
		groupTotalButton.setBounds(702, 496, 275, 55);
		frame.getContentPane().add(groupTotalButton);
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

	public void updateTree(DefaultMutableTreeNode nodeToAdd, DefaultMutableTreeNode containingNode) {
		if (containingNode.getUserObject() instanceof Group) {
			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			model.insertNodeInto(nodeToAdd, containingNode, containingNode.getChildCount());
			tree.scrollPathToVisible(new TreePath(nodeToAdd.getPath()));
		} else {
			alert("Cannot add to User. Select a group and try again!");
		}
	}

	private void alert(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public DefaultMutableTreeNode getSelected() {
		try {
			return ((DefaultMutableTreeNode) tree.getLastSelectedPathComponent());
		} catch (NullPointerException e) {
			return null;
		}
	}

}
