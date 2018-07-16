package twitter;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
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
		frame = new JFrame();
		frame.setBounds(100, 100, 1007, 679);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		rootNode = new DefaultMutableTreeNode(root);

		txtUserId = new JTextField();
		txtUserId.setText("User ID");
		txtUserId.setBounds(415, 13, 275, 55);
		frame.getContentPane().add(txtUserId);
		txtUserId.setColumns(10);

		JButton btnNewButton = new JButton("Add User");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(702, 13, 275, 55);
		frame.getContentPane().add(btnNewButton);

		txtGroupId = new JTextField();
		txtGroupId.setText("Group ID");
		txtGroupId.setBounds(415, 81, 275, 55);
		frame.getContentPane().add(txtGroupId);
		txtGroupId.setColumns(10);
		
		//Initialize the add group button, set its location, and add an Action Listener to add a group when it is clicked
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

		JButton btnNewButton_3 = new JButton("Show Messages Total");
		btnNewButton_3.setBounds(415, 564, 275, 55);
		frame.getContentPane().add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Show Positive Percentage");
		btnNewButton_4.setBounds(702, 564, 275, 55);
		frame.getContentPane().add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Show User Total");
		btnNewButton_5.setBounds(415, 496, 275, 55);
		frame.getContentPane().add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("Show Group Total");
		btnNewButton_6.setBounds(702, 496, 275, 55);
		frame.getContentPane().add(btnNewButton_6);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 391, 606);

		frame.getContentPane().add(scrollPane);
		tree = new JTree(rootNode);
		scrollPane.setViewportView(tree);
	}

	private void addGroup(String newGroupID) {
		DefaultMutableTreeNode group1 = new DefaultMutableTreeNode(new Group(newGroupID));
		DefaultMutableTreeNode tempNode = ((DefaultMutableTreeNode) tree.getLastSelectedPathComponent());
		updateTree(group1, tempNode);
	}

	public void updateTree(DefaultMutableTreeNode nodeToAdd, DefaultMutableTreeNode containingNode) {
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		model.insertNodeInto(nodeToAdd, containingNode, containingNode.getChildCount());
		tree.scrollPathToVisible(new TreePath(nodeToAdd.getPath()));
	}

}
