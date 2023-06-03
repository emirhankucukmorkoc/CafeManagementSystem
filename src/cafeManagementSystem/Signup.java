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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Signup extends JFrame {

	public String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	public String mobileNumberPattern = "^[0-9]*$";

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtMobileNumber;
	private JTextField txtAddress;
	private JTextField txtSecurityQuestion;
	private JTextField txtAnswer;
	private JPasswordField passwordField;
	JButton btnSave = new JButton("Save");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Signup() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setUndecorated(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sign Up");
		lblNewLabel.setBounds(628, 126, 160, 64);
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(393, 215, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setBounds(393, 255, 46, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Mobile Number");
		lblNewLabel_1_1_1.setBounds(393, 301, 123, 14);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Address");
		lblNewLabel_1_1_1_1.setBounds(393, 346, 80, 14);
		contentPane.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Security Question");
		lblNewLabel_1_1_1_1_1.setBounds(393, 423, 123, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Password");
		lblNewLabel_1_1_1_1_2.setBounds(393, 386, 80, 14);
		contentPane.add(lblNewLabel_1_1_1_1_2);

		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtName.setBounds(548, 215, 338, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();

			}
		});
		txtEmail.setBounds(548, 258, 338, 20);
		txtEmail.setColumns(10);
		contentPane.add(txtEmail);

		txtMobileNumber = new JTextField();
		txtMobileNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();

			}
		});
		txtMobileNumber.setBounds(548, 301, 338, 20);
		txtMobileNumber.setColumns(10);
		contentPane.add(txtMobileNumber);

		txtAddress = new JTextField();
		txtAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();

			}
		});
		txtAddress.setBounds(548, 349, 338, 20);
		txtAddress.setColumns(10);
		contentPane.add(txtAddress);

		JLabel lblNewLabel_1_1_1_1_2_1 = new JLabel("Answer");
		lblNewLabel_1_1_1_1_2_1.setBounds(393, 465, 46, 14);
		contentPane.add(lblNewLabel_1_1_1_1_2_1);

		txtSecurityQuestion = new JTextField();
		txtSecurityQuestion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();

			}
		});
		txtSecurityQuestion.setBounds(548, 426, 338, 20);
		txtSecurityQuestion.setColumns(10);
		contentPane.add(txtSecurityQuestion);

		txtAnswer = new JTextField();
		txtAnswer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();

			}
		});
		txtAnswer.setBounds(548, 465, 338, 20);
		txtAnswer.setColumns(10);
		contentPane.add(txtAnswer);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();

			}
		});
		passwordField.setBounds(547, 389, 339, 20);
		passwordField.setToolTipText("");
		contentPane.add(passwordField);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();

				user.setName(txtName.getText());
				user.setEmail(txtEmail.getText());
				user.setMobileNumber(txtMobileNumber.getText());
				user.setAddress(txtAddress.getText());
				user.setPassword(passwordField.getText());
				user.setSecurityQuestion(txtSecurityQuestion.getText());
				user.setAnswer(txtAnswer.getText());
				UserDao.save(user);
				clear();
			}
		});

		btnSave.setBounds(547, 510, 89, 23);
		btnSave.setIcon(new ImageIcon(Signup.class.getResource("/images/save.png")));
		contentPane.add(btnSave);

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
		btnExit.setBounds(797, 510, 89, 23);
		btnExit.setIcon(new ImageIcon(Signup.class.getResource("/images/exit small.png")));
		contentPane.add(btnExit);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setBounds(676, 510, 89, 23);
		btnClear.setIcon(new ImageIcon(Signup.class.getResource("/images/clear.png")));
		contentPane.add(btnClear);

		JButton btnForgotPass = new JButton("Forgot Password");
		btnForgotPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ForgotPassword().setVisible(true);
			
			}
		});
		btnForgotPass.setBounds(547, 560, 136, 23);
		contentPane.add(btnForgotPass);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login().setVisible(true);
			}
		});
		btnLogin.setBounds(797, 560, 89, 23);
		contentPane.add(btnLogin);

		JLabel lblNewLabel_2 = new JLabel(new ImageIcon(Signup.class.getResource("/images/first page background.PNG")));
		lblNewLabel_2.setBounds(-33, -21, 1432, 804);
		contentPane.add(lblNewLabel_2);

		btnSave.setEnabled(false);

	}

	public void clear() {
		txtName.setText("");
		txtEmail.setText("");
		txtMobileNumber.setText("");
		txtAddress.setText("");
		txtSecurityQuestion.setText("");
		txtAnswer.setText("");
		passwordField.setText("");
		btnSave.setEnabled(false);

	}

	public void validateFields() {
		String name = txtName.getText();
		String email = txtEmail.getText();
		String mobileNumber = txtMobileNumber.getText();
		String address = txtAddress.getText();
		String securityQuestion = txtSecurityQuestion.getText();
		String answer = txtAnswer.getText();
		String password = passwordField.getText();

		if (!name.equals("") && email.matches(emailPattern) && mobileNumber.matches(mobileNumberPattern)
				&& mobileNumber.length() == 10 && !address.equals("") && !password.equals("")
				&& !securityQuestion.equals("") && !answer.equals("")) {
			btnSave.setEnabled(true);

		} else {
			btnSave.setEnabled(false);

		}

	}
}
