package cafeManagementSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.UserDao;
import model.User;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class VerifyUsers extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerifyUsers frame = new VerifyUsers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VerifyUsers() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				getAllRecords("");
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("*CLICK  ON ROW  TO CHANGE STATUS");
		lblNewLabel_3.setFont(new Font("Informal Roman", Font.BOLD, 27));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(446, 728, 635, 29);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("Verify User");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon(VerifyUsers.class.getResource("/images/verify users.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 160, 33);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
		btnNewButton.setIcon(new ImageIcon(VerifyUsers.class.getResource("/images/close.png")));
		btnNewButton.setBounds(1319, 11, 37, 29);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(465, 84, 58, 17);
		contentPane.add(lblNewLabel_1);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String email = txtSearch.getText();
				getAllRecords(email);
			}
		});
		txtSearch.setBounds(546, 84, 298, 20);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 114, 1260, 603);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				String email = model.getValueAt(index, 2).toString();
				txtSearch.setText(email);
				String status = model.getValueAt(index, 6).toString();
				if (status.equals("true")) {
					status = "false";

				} else {
					status = "true";
					int a = JOptionPane.showConfirmDialog(null, "Do you want to change status of " + email + "",
							"Select", JOptionPane.YES_NO_OPTION);
					if (a == 0) {
						UserDao.changeStatus(email, status);
						setVisible(false);
						new VerifyUsers().setVisible(true);

					}
				}

			}
		});
		scrollPane.setViewportView(table);

		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Name", "Email", "Mobile Number", "Address", "Securit Question", "Status" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(3).setPreferredWidth(93);
		table.getColumnModel().getColumn(5).setPreferredWidth(99);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(VerifyUsers.class.getResource("/images/full-page-background.PNG")));
		lblNewLabel_2.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel_2);
		setUndecorated(true);

	}

	public void getAllRecords(String email) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		ArrayList<User> list = UserDao.getAllRecords(email);
		Iterator<User> itr = list.iterator();
		while (itr.hasNext()) {
			User userObj = itr.next();
			if (!userObj.getEmail().equals("emirhan.morkoc@icloud.com")) {
				dtm.addRow(new Object[] { userObj.getId(), userObj.getName(), userObj.getEmail(),
						userObj.getMobileNumber(), userObj.getAddress(), userObj.getSecurityQuestion(),
						userObj.getStatus() });

			}
		}

	}
}
