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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ForgotPassword extends JFrame {
	
	
	private String dbAnswer = null;
	private String email = null;
	public String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtSecurityQuestion;
	private JTextField txtAnswer;
	private JTextField txtEnterNewPassword;
	JButton btnUpdate = new JButton("Update");
	JButton btnSearch = new JButton("Search");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
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
	public ForgotPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
        setUndecorated(true);

		JLabel lblNewLabel = new JLabel("Forgot Password ?");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(481, 146, 347, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1.setBounds(300, 230, 188, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Your Security Question");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(300, 288, 188, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Your Answer");
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(300, 341, 188, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Enter New Password");
		lblNewLabel_1_2_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(300, 404, 188, 14);
		contentPane.add(lblNewLabel_1_2_1);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Dialog", Font.BOLD, 14));
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validteEmail();
			}
		});
		txtEmail.setBounds(498, 230, 325, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtSecurityQuestion = new JTextField();
		txtSecurityQuestion.setFont(new Font("Dialog", Font.BOLD, 14));
		txtSecurityQuestion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();

			}
		});
		txtSecurityQuestion.setColumns(10);
		txtSecurityQuestion.setBounds(498, 285, 325, 20);
		contentPane.add(txtSecurityQuestion);
		
		txtAnswer = new JTextField();
		txtAnswer.setFont(new Font("Dialog", Font.BOLD, 14));
		txtAnswer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();

			}
		});
		txtAnswer.setColumns(10);
		txtAnswer.setBounds(498, 344, 325, 20);
		contentPane.add(txtAnswer);
		
		txtEnterNewPassword = new JTextField();
		txtEnterNewPassword.setFont(new Font("Dialog", Font.BOLD, 14));
		txtEnterNewPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();

			}
		});
		txtEnterNewPassword.setColumns(10);
		txtEnterNewPassword.setBounds(498, 407, 325, 20);
		contentPane.add(txtEnterNewPassword);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				email = txtEmail.getText();
				User user = null;
				user = UserDao.getSecurityQuestion(email);
				if (user == null) {
					JOptionPane.showMessageDialog(null, "<html><b style=\"color:red\">Incorrect Email</b></html>","Message",JOptionPane.ERROR_MESSAGE);
					
					
				}else {
					btnSearch.setEnabled(false);
					txtEmail.setEditable(false);
					dbAnswer = user.getAnswer();
					txtSecurityQuestion.setText(user.getSecurityQuestion());
					validateFields();
				}
			}
		});
		
		btnSearch.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSearch.setIcon(new ImageIcon(ForgotPassword.class.getResource("/images/search.png")));
		btnSearch.setBounds(833, 226, 117, 23);
		contentPane.add(btnSearch);
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String answer = txtAnswer.getText();
				String newPassword = txtEnterNewPassword.getText();
				if (answer.equals(dbAnswer)) {
					UserDao.update(email, newPassword);
					clear();
					
					
				}else {
					JOptionPane.showMessageDialog(null, "<html><b style=\"color:red\">Incorrect Answer</b></html>","Message",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 14));
		btnUpdate.setIcon(new ImageIcon(ForgotPassword.class.getResource("/images/save.png")));
		btnUpdate.setBounds(498, 452, 113, 23);
		contentPane.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setFont(new Font("Dialog", Font.BOLD, 14));
		btnClear.setIcon(new ImageIcon(ForgotPassword.class.getResource("/images/clear.png")));
		btnClear.setBounds(621, 452, 103, 23);
		contentPane.add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to Close Application","Select",JOptionPane.YES_NO_OPTION);
				if (a == 0) {
					System.exit(0);
					
					
				}
			}
		});
		btnExit.setFont(new Font("Dialog", Font.BOLD, 14));
		btnExit.setIcon(new ImageIcon(ForgotPassword.class.getResource("/images/exit small.png")));
		btnExit.setBounds(734, 452, 89, 23);
		contentPane.add(btnExit);
		
		JButton btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Signup().setVisible(true);
			}
		});
		btnSignup.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSignup.setBounds(498, 507, 89, 23);
		contentPane.add(btnSignup);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login().setVisible(true);
			}
		});
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 14));
		btnLogin.setBounds(739, 507, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ForgotPassword.class.getResource("/images/first page background.PNG")));
		lblNewLabel_2.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel_2);
		
		btnUpdate.setEnabled(false);
		btnSearch.setEnabled(false);
		txtSecurityQuestion.setEditable(false);
		
		
	}
	public void clear() {
		txtEmail.setText("");
		txtSecurityQuestion.setText("");
		txtAnswer.setText("");
		txtEnterNewPassword.setText("");
		btnUpdate.setEnabled(false);
		btnSearch.setEnabled(false);
		txtEmail.setEditable(true);
	}
	
	public void validteEmail() {
		email = txtEmail.getText();
		if (email.matches(emailPattern)) {
			btnSearch.setEnabled(true);
		}else {
			btnSearch.setEnabled(false);
		}
	}
	
	public void validateFields() {
		String password = txtEnterNewPassword.getText();
		String answer = txtAnswer.getText();
		String securityQuestion = txtSecurityQuestion.getText();
		if (!password.equals("")&& !answer.equals("")&& !securityQuestion.equals("")) {
			btnUpdate.setEnabled(true);			
		}else {
			btnUpdate.setEnabled(false);
		}
	}
	
	
	
}
