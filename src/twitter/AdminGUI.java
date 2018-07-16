package twitter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class AdminGUI {

	JFrame frame;
	private JTextField txtUserId;
	private JTextField txtGroupId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI window = new AdminGUI();
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
	public AdminGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1007, 679);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("root");
		
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
		
		JButton btnNewButton_1 = new JButton("Add Group");
		btnNewButton_1.setBounds(702, 81, 275, 55);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Open User View");
		btnNewButton_2.setBounds(415, 149, 562, 55);
		frame.getContentPane().add(btnNewButton_2);
		
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
		//createNodes(top);
		JTree tree = new JTree(top);
		scrollPane.setViewportView(tree);
	}
}
