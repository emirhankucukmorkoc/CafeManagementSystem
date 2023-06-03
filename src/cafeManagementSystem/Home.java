package cafeManagementSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Home extends JFrame {

	private JPanel contentPane;
	public String email;
	JButton btnManageCategory = new JButton("Manage Category");
	JButton btnVerifyUser = new JButton("Verify User");
	JButton btnViewEditDeleteProd = new JButton("View Edit & Delete Product");
	JButton btnNewProduct = new JButton("New Product");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Home(String userEmail) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to Logout ", "Select",
						JOptionPane.YES_NO_OPTION);
				if (a == 0) {
					setVisible(false);
					new Login().setVisible(true);
				}

			}
		});
		btnLogout.setBounds(32, 35, 126, 39);
		btnLogout.setIcon(new ImageIcon(Home.class.getResource("/images/logout.png")));
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnLogout);

		JButton btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.setBounds(185, 35, 161, 39);
		btnPlaceOrder.setIcon(new ImageIcon(Home.class.getResource("/images/place order.png")));
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new PlaceOrder(email).setVisible(true);
			}
		});
		btnPlaceOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnPlaceOrder);

		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(671, 35, 209, 39);
		btnChangePassword.setIcon(new ImageIcon(Home.class.getResource("/images/change Password.png")));
		btnChangePassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnChangePassword);

		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePassword(email).setVisible(true);
			}
		});

		JButton btnChangeSecQue = new JButton("Change Security Question");
		btnChangeSecQue.setBounds(908, 35, 280, 39);
		btnChangeSecQue.setIcon(new ImageIcon(Home.class.getResource("/images/change Security Question.png")));
		btnChangeSecQue.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnChangeSecQue);
		btnChangeSecQue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangeSecurityQuestion(email).setVisible(true);
			}
		});

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
		btnExit.setIcon(new ImageIcon(Home.class.getResource("/images/exit small.png")));
		btnExit.setBounds(1213, 35, 99, 39);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnExit);

		JButton btnManageCategory = new JButton("Manage Category");
		btnManageCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageCategory().setVisible(true);
			}
		});

		JButton btnNewButton = new JButton("View Bill & Order Placed Details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewBillOrderPlacedDetails().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setIcon(new ImageIcon(Home.class.getResource("/images/view edit delete product.png")));
		btnNewButton.setBounds(356, 35, 305, 39);
		contentPane.add(btnNewButton);

		btnManageCategory.setIcon(new ImageIcon(Home.class.getResource("/images/category.png")));
		btnManageCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnManageCategory.setBounds(188, 661, 198, 39);
		contentPane.add(btnManageCategory);

		JButton btnNewProduct = new JButton("New Product");
		btnNewProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddNewProduct().setVisible(true);
			}
		});
		btnNewProduct.setIcon(new ImageIcon(Home.class.getResource("/images/new product.png")));
		btnNewProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewProduct.setBounds(429, 661, 166, 39);
		contentPane.add(btnNewProduct);

		JButton btnViewEditDeleteProd = new JButton("View Edit & Delete Product");
		btnViewEditDeleteProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewEditDeleteProduct().setVisible(true);

			}
		});
		btnViewEditDeleteProd.setIcon(new ImageIcon(Home.class.getResource("/images/view edit delete product.png")));
		btnViewEditDeleteProd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnViewEditDeleteProd.setBounds(655, 661, 279, 39);
		contentPane.add(btnViewEditDeleteProd);

		JButton btnVerifyUser = new JButton("Verify User");
		btnVerifyUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VerifyUsers().setVisible(true);

			}
		});
		btnVerifyUser.setIcon(new ImageIcon(Home.class.getResource("/images/verify users.png")));
		btnVerifyUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVerifyUser.setBounds(954, 661, 166, 39);
		contentPane.add(btnVerifyUser);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/images/home-background-image.png")));
		lblNewLabel.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel);
		email = userEmail;
		if (!email.equals("admin@gmail.com")) {
			btnManageCategory.setEnabled(false);
			btnNewProduct.setEnabled(false);
			btnVerifyUser.setEnabled(false);
			btnViewEditDeleteProd.setEnabled(false);

		}

	}

	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to Logout ", "Select",
						JOptionPane.YES_NO_OPTION);
				if (a == 0) {
					setVisible(false);
					new Login().setVisible(true);
				}

			}
		});

		JButton btnNewButton = new JButton("View Bill & Order Placed Details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewBillOrderPlacedDetails().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setIcon(new ImageIcon(Home.class.getResource("/images/view edit delete product.png")));
		btnNewButton.setBounds(356, 35, 305, 39);
		contentPane.add(btnNewButton);
		btnLogout.setBounds(32, 35, 126, 39);
		btnLogout.setIcon(new ImageIcon(Home.class.getResource("/images/logout.png")));
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnLogout);

		JButton btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.setBounds(185, 35, 161, 39);
		btnPlaceOrder.setIcon(new ImageIcon(Home.class.getResource("/images/place order.png")));
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new PlaceOrder(email).setVisible(true);
			}
		});
		btnPlaceOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnPlaceOrder);

		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePassword(email).setVisible(true);
			}
		});
		btnChangePassword.setBounds(671, 35, 209, 39);
		btnChangePassword.setIcon(new ImageIcon(Home.class.getResource("/images/change Password.png")));
		btnChangePassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnChangePassword);

		JButton btnChangeSecQue = new JButton("Change Security Question");
		btnChangeSecQue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangeSecurityQuestion(email).setVisible(true);
			}
		});
		btnChangeSecQue.setBounds(908, 35, 280, 39);
		btnChangeSecQue.setIcon(new ImageIcon(Home.class.getResource("/images/change Security Question.png")));
		btnChangeSecQue.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnChangeSecQue);

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
		btnExit.setIcon(new ImageIcon(Home.class.getResource("/images/exit small.png")));
		btnExit.setBounds(1213, 35, 99, 39);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnExit);

		JButton btnManageCategory = new JButton("Manage Category");
		btnManageCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageCategory().setVisible(true);
			}
		});
		btnManageCategory.setIcon(new ImageIcon(Home.class.getResource("/images/category.png")));
		btnManageCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnManageCategory.setBounds(188, 661, 198, 39);
		contentPane.add(btnManageCategory);

		JButton btnNewProduct = new JButton("New Product");
		btnNewProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddNewProduct().setVisible(true);
			}
		});
		btnNewProduct.setIcon(new ImageIcon(Home.class.getResource("/images/new product.png")));
		btnNewProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewProduct.setBounds(429, 661, 166, 39);
		contentPane.add(btnNewProduct);

		JButton btnViewEditDeleteProd = new JButton("View Edit & Delete Product");
		btnViewEditDeleteProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new ViewBillOrderPlacedDetails().setVisible(true);

			}
		});
		btnViewEditDeleteProd.setIcon(new ImageIcon(Home.class.getResource("/images/view edit delete product.png")));
		btnViewEditDeleteProd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnViewEditDeleteProd.setBounds(655, 661, 279, 39);
		contentPane.add(btnViewEditDeleteProd);

		JButton btnVerifyUser = new JButton("Verify User");
		btnVerifyUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VerifyUsers().setVisible(true);

			}
		});
		btnVerifyUser.setIcon(new ImageIcon(Home.class.getResource("/images/verify users.png")));
		btnVerifyUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVerifyUser.setBounds(954, 661, 166, 39);
		contentPane.add(btnVerifyUser);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/images/home-background-image.png")));
		lblNewLabel.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel);
	}
}
