package cafeManagementSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	public String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

	private JPanel contentPane;
	private JTextField txtEmail;
	JButton btnLogin = new JButton("Login");
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);

		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtPassword.setBounds(533, 305, 368, 20);
		contentPane.add(txtPassword);

		JLabel txtLogin = new JLabel("Login");
		txtLogin.setForeground(new Color(255, 0, 0));
		txtLogin.setFont(new Font("Tahoma", Font.BOLD, 36));
		txtLogin.setBounds(633, 128, 106, 76);
		contentPane.add(txtLogin);

		JLabel lblPasswor = new JLabel("Password");
		lblPasswor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPasswor.setBounds(427, 306, 74, 14);
		contentPane.add(lblPasswor);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(427, 243, 46, 14);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtEmail.setBounds(533, 240, 368, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String password = txtPassword.getText();

				User user = null;
				user = UserDao.login(email, password);
				if (user == null) {
					JOptionPane.showMessageDialog(null,
							"<html><b style=\"color:red\">Incorrect Username or Password</b></html>", "Message ",
							JOptionPane.ERROR_MESSAGE);

				} else {
					if (user.getStatus().equals("false")) {
						ImageIcon icon = new ImageIcon("src/popupicon/wait.png");
						JOptionPane.showMessageDialog(null,
								"<html><b style=\"color:black\">Wait for Admin Approval</b></html>", "Message",
								JOptionPane.INFORMATION_MESSAGE, icon);
						clear();

					}
					if (user.getStatus().equals("true")) {
						setVisible(false);
						new Home(email).setVisible(true);

					}
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setIcon(new ImageIcon(Login.class.getResource("/images/login.png")));
		btnLogin.setSelectedIcon(new ImageIcon(Login.class.getResource("/images/login.png")));
		btnLogin.setBounds(533, 357, 106, 23);
		contentPane.add(btnLogin);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setIcon(new ImageIcon(Login.class.getResource("/images/clear.png")));
		btnClear.setBounds(675, 357, 106, 23);
		contentPane.add(btnClear);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to Close Application", "Select",
						JOptionPane.YES_NO_OPTION);
				if (a == 0) {
					System.exit(0);

				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setIcon(new ImageIcon(Login.class.getResource("/images/exit small.png")));
		btnExit.setBounds(812, 357, 89, 23);
		contentPane.add(btnExit);

		JButton btnForgotPassword = new JButton("Forgot Password  ?");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ForgotPassword().setVisible(true);
			}
		});
		btnForgotPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnForgotPassword.setBounds(533, 411, 187, 23);
		contentPane.add(btnForgotPassword);

		JButton btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Signup().setVisible(true);
			}
		});
		btnSignup.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSignup.setBounds(812, 411, 89, 23);
		contentPane.add(btnSignup);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/images/first page background.PNG")));
		lblNewLabel.setBounds(0, 0, 1376, 779);
		contentPane.add(lblNewLabel);

		btnLogin.setEnabled(false);
	}

	public void clear() {
		txtEmail.setText("");
		txtPassword.setText("");
		btnLogin.setEnabled(false);
	}

	public void validateFields() {
		String email = txtEmail.getText();
		String password = txtPassword.getText();

		if (email.matches(emailPattern) && !password.equals("")) {
			btnLogin.setEnabled(true);

		} else {
			btnLogin.setEnabled(false);
		}
	}
}
