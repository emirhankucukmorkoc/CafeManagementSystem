package cafeManagementSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import model.User;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ChangeSecurityQuestion extends JFrame {

	private JPanel contentPane;
	private JTextField txtOldSecurityQuestion;
	private JTextField txtNewSecurityQuestion;
	private JTextField txtNewAnswer;
	private JPasswordField txtPassword;
	public String userEmail;
	JButton btnUpdate = new JButton("Update");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeSecurityQuestion frame = new ChangeSecurityQuestion();
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
	public ChangeSecurityQuestion() {

	
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				User user = UserDao.getSecurityQuestion(userEmail);
				txtOldSecurityQuestion.setText(user.getSecurityQuestion());

			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 250, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Change Security Question");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setIcon(
				new ImageIcon(ChangeSecurityQuestion.class.getResource("/images/change Security Question.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 249, 23);
		contentPane.add(lblNewLabel);

		JLabel lblOldSecurityQuestion = new JLabel("Old Security Question");
		lblOldSecurityQuestion.setForeground(new Color(255, 255, 255));
		lblOldSecurityQuestion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOldSecurityQuestion.setBounds(94, 96, 165, 23);
		contentPane.add(lblOldSecurityQuestion);

		JLabel lblNewLabel_1_1 = new JLabel("New Security Question");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(94, 148, 165, 23);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("New Answer");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(94, 197, 165, 23);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Password");
		lblNewLabel_1_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(94, 257, 165, 23);
		contentPane.add(lblNewLabel_1_3);

		this.txtOldSecurityQuestion = new JTextField();
		txtOldSecurityQuestion.setBounds(270, 99, 296, 20);
		contentPane.add(txtOldSecurityQuestion);
		txtOldSecurityQuestion.setColumns(10);

		txtNewSecurityQuestion = new JTextField();
		txtNewSecurityQuestion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();
			}
		});
		txtNewSecurityQuestion.setColumns(10);
		txtNewSecurityQuestion.setBounds(270, 151, 296, 20);
		contentPane.add(txtNewSecurityQuestion);

		txtNewAnswer = new JTextField();
		txtNewAnswer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();
			}
		});
		txtNewAnswer.setColumns(10);
		txtNewAnswer.setBounds(270, 200, 296, 20);
		contentPane.add(txtNewAnswer);

		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();
			}
		});
		txtPassword.setBounds(270, 260, 296, 20);
		contentPane.add(txtPassword);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ChangeSecurityQuestion(userEmail).setVisible(true);

			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnClear.setIcon(new ImageIcon(ChangeSecurityQuestion.class.getResource("/images/clear.png")));
		btnClear.setBounds(467, 301, 99, 23);
		contentPane.add(btnClear);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String password = txtPassword.getText();
				String securityQuestion = txtNewSecurityQuestion.getText();
				String answer = txtNewAnswer.getText();

				UserDao.changeSecuritQuestion(userEmail, password, securityQuestion, answer);
				setVisible(false);
				new ChangeSecurityQuestion(userEmail).setVisible(true);

			}
		});

		btnUpdate.setIcon(new ImageIcon(ChangeSecurityQuestion.class.getResource("/images/save.png")));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpdate.setBounds(270, 301, 114, 23);
		contentPane.add(btnUpdate);

		JButton btnClose = new JButton("");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnClose.setIcon(new ImageIcon(ChangeSecurityQuestion.class.getResource("/images/close.png")));
		btnClose.setBounds(656, 13, 34, 23);
		contentPane.add(btnClose);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1
				.setIcon(new ImageIcon(ChangeSecurityQuestion.class.getResource("/images/full-page-background.PNG")));
		lblNewLabel_1.setBounds(0, 0, 700, 400);
		contentPane.add(lblNewLabel_1);
		setUndecorated(true);
		
		txtOldSecurityQuestion.setEditable(false);
		btnUpdate.setEnabled(false);
	}

	public ChangeSecurityQuestion(String email) {
		userEmail = email;
		

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				User user = UserDao.getSecurityQuestion(userEmail);
				txtOldSecurityQuestion.setText(user.getSecurityQuestion());

			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 250, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Change Security Question");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setIcon(
				new ImageIcon(ChangeSecurityQuestion.class.getResource("/images/change Security Question.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 249, 23);
		contentPane.add(lblNewLabel);

		JLabel lblOldSecurityQuestion = new JLabel("Old Security Question");
		lblOldSecurityQuestion.setForeground(new Color(255, 255, 255));
		lblOldSecurityQuestion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOldSecurityQuestion.setBounds(94, 96, 165, 23);
		contentPane.add(lblOldSecurityQuestion);

		JLabel lblNewLabel_1_1 = new JLabel("New Security Question");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(94, 148, 165, 23);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("New Answer");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(94, 197, 165, 23);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Password");
		lblNewLabel_1_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(94, 257, 165, 23);
		contentPane.add(lblNewLabel_1_3);

		this.txtOldSecurityQuestion = new JTextField();
		txtOldSecurityQuestion.setBounds(270, 99, 296, 20);
		contentPane.add(txtOldSecurityQuestion);
		txtOldSecurityQuestion.setColumns(10);

		txtNewSecurityQuestion = new JTextField();
		txtNewSecurityQuestion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();
			}
		});
		txtNewSecurityQuestion.setColumns(10);
		txtNewSecurityQuestion.setBounds(270, 151, 296, 20);
		contentPane.add(txtNewSecurityQuestion);

		txtNewAnswer = new JTextField();
		txtNewAnswer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();
			}
		});
		txtNewAnswer.setColumns(10);
		txtNewAnswer.setBounds(270, 200, 296, 20);
		contentPane.add(txtNewAnswer);

		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();
			}
		});
		txtPassword.setBounds(270, 260, 296, 20);
		contentPane.add(txtPassword);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ChangeSecurityQuestion(userEmail).setVisible(true);

			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnClear.setIcon(new ImageIcon(ChangeSecurityQuestion.class.getResource("/images/clear.png")));
		btnClear.setBounds(467, 301, 99, 23);
		contentPane.add(btnClear);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String password = txtPassword.getText();
				String securityQuestion = txtNewSecurityQuestion.getText();
				String answer = txtNewAnswer.getText();

				UserDao.changeSecuritQuestion(userEmail, password, securityQuestion, answer);
				setVisible(false);
				new ChangeSecurityQuestion(userEmail).setVisible(true);

			}
		});

		btnUpdate.setIcon(new ImageIcon(ChangeSecurityQuestion.class.getResource("/images/save.png")));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpdate.setBounds(270, 301, 114, 23);
		contentPane.add(btnUpdate);

		JButton btnClose = new JButton("");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnClose.setIcon(new ImageIcon(ChangeSecurityQuestion.class.getResource("/images/close.png")));
		btnClose.setBounds(656, 13, 34, 23);
		contentPane.add(btnClose);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1
				.setIcon(new ImageIcon(ChangeSecurityQuestion.class.getResource("/images/full-page-background.PNG")));
		lblNewLabel_1.setBounds(0, 0, 700, 400);
		contentPane.add(lblNewLabel_1);
		setUndecorated(true);
		
		txtOldSecurityQuestion.setEditable(false);
		btnUpdate.setEnabled(false);
	}

	public void validateField() {

		String password = txtPassword.getText();
		String securityQuestion = txtNewSecurityQuestion.getText();
		String answer = txtNewAnswer.getText();

		if (!password.equals("") && !securityQuestion.equals("") && !answer.equals("")) {
			btnUpdate.setEnabled(true);

		} else {
			btnUpdate.setEnabled(false);
		}
	}

}
