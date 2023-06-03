package cafeManagementSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.UserDao;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JTextField txtOldPassword;
	private JPasswordField txtNewPassword;
	private JPasswordField txtConfirmPassword;
	JButton btnUpdate = new JButton("Update");
	public String userEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword frame = new ChangePassword();
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

	public ChangePassword(String email) {

		userEmail = email;
		btnUpdate.setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 250, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Change Password");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/change Password.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 170, 24);
		contentPane.add(lblNewLabel);

		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setForeground(new Color(255, 255, 255));
		lblOldPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOldPassword.setBounds(86, 94, 110, 24);
		contentPane.add(lblOldPassword);

		JLabel lblNewLabel_1_1 = new JLabel("New Password");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(86, 158, 110, 24);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Confirm Password");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(86, 224, 128, 24);
		contentPane.add(lblNewLabel_1_1_1);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ChangePassword(userEmail).setVisible(true);
			}
		});
		btnClear.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/clear.png")));
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClear.setBounds(468, 269, 100, 23);
		contentPane.add(btnClear);

		JButton btnClose = new JButton("");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnClose.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/close.png")));
		btnClose.setBounds(655, 11, 35, 23);
		contentPane.add(btnClose);

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String oldPassword = txtOldPassword.getText();
				String newPassword = txtNewPassword.getText();
				String confirmPassword = txtConfirmPassword.getText();

				UserDao.changePassword(userEmail, oldPassword, newPassword);
				setVisible(false);
				new ChangePassword(confirmPassword).setVisible(true);

			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/save.png")));
		btnUpdate.setBounds(248, 269, 100, 23);
		contentPane.add(btnUpdate);

		txtOldPassword = new JTextField();
		txtOldPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();
			}
		});
		txtOldPassword.setBounds(248, 98, 320, 20);
		contentPane.add(txtOldPassword);
		txtOldPassword.setColumns(10);

		txtNewPassword = new JPasswordField();
		txtNewPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();

			}
		});
		txtNewPassword.setBounds(248, 162, 320, 20);
		contentPane.add(txtNewPassword);

		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();

			}
		});
		txtConfirmPassword.setBounds(248, 228, 320, 20);
		contentPane.add(txtConfirmPassword);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/full-page-background.PNG")));
		lblNewLabel_1.setBounds(0, 0, 700, 400);
		contentPane.add(lblNewLabel_1);
		setUndecorated(true);

	}

	public ChangePassword() {
		btnUpdate.setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 250, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Change Password");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/change Password.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 170, 24);
		contentPane.add(lblNewLabel);

		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setForeground(new Color(255, 255, 255));
		lblOldPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOldPassword.setBounds(86, 94, 110, 24);
		contentPane.add(lblOldPassword);

		JLabel lblNewLabel_1_1 = new JLabel("New Password");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(86, 158, 110, 24);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Confirm Password");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(86, 224, 128, 24);
		contentPane.add(lblNewLabel_1_1_1);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ChangePassword(userEmail).setVisible(true);
			}
		});
		btnClear.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/clear.png")));
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClear.setBounds(468, 269, 100, 23);
		contentPane.add(btnClear);

		JButton btnClose = new JButton("");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnClose.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/close.png")));
		btnClose.setBounds(655, 11, 35, 23);
		contentPane.add(btnClose);

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String oldPassword = txtOldPassword.getText();
				String newPassword = txtNewPassword.getText();
				String confirmPassword = txtConfirmPassword.getText();

				UserDao.changePassword(userEmail, oldPassword, newPassword);
				setVisible(false);
				new ChangePassword(confirmPassword).setVisible(true);

			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/save.png")));
		btnUpdate.setBounds(248, 269, 100, 23);
		contentPane.add(btnUpdate);

		txtOldPassword = new JTextField();
		txtOldPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();
			}
		});
		txtOldPassword.setBounds(248, 98, 320, 20);
		contentPane.add(txtOldPassword);
		txtOldPassword.setColumns(10);

		txtNewPassword = new JPasswordField();
		txtNewPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();

			}
		});
		txtNewPassword.setBounds(248, 162, 320, 20);
		contentPane.add(txtNewPassword);

		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();

			}
		});
		txtConfirmPassword.setBounds(248, 228, 320, 20);
		contentPane.add(txtConfirmPassword);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/full-page-background.PNG")));
		lblNewLabel_1.setBounds(0, 0, 700, 400);
		contentPane.add(lblNewLabel_1);
		setUndecorated(true);
	}

	public void validateField() {
		String oldPassword = txtOldPassword.getText();
		String newPassword = txtNewPassword.getText();
		String confirmPassword = txtConfirmPassword.getText();

		if (!oldPassword.equals("") && !newPassword.equals("") && !confirmPassword.equals("")
				&& newPassword.equals(confirmPassword)) {
			btnUpdate.setEnabled(true);

		} else {
			btnUpdate.setEnabled(false);
		}
	}
}
