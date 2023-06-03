package cafeManagementSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CategoryDao;
import dao.ProductDao;
import dao.UserDao;
import model.Category;
import model.Product;
import model.User;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddNewProduct extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtPrice;
	JButton btnSave = new JButton("Save");
	JComboBox<String> comBoxCategory = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewProduct frame = new AddNewProduct();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddNewProduct() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				ArrayList<Category> list = CategoryDao.getAllRecords();
				Iterator<Category> itr = list.iterator();
				while (itr.hasNext()) {
					Category categoryObj = itr.next();
					comBoxCategory.addItem(categoryObj.getName());

				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 250, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewProduct = new JLabel("New Product\r\n");
		lblNewProduct.setIcon(new ImageIcon(AddNewProduct.class.getResource("/images/new product.png")));
		lblNewProduct.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewProduct.setBounds(10, 11, 151, 42);
		contentPane.add(lblNewProduct);

		JButton btnExit = new JButton("");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnExit.setFont(new Font("Dialog", Font.BOLD, 14));
		btnExit.setIcon(new ImageIcon(AddNewProduct.class.getResource("/images/close.png")));
		btnExit.setBounds(662, 11, 28, 23);
		contentPane.add(btnExit);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(132, 127, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCategory.setBounds(132, 167, 102, 17);
		contentPane.add(lblCategory);

		JLabel lblNewLabel_1_1 = new JLabel("Price");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(132, 217, 46, 14);
		contentPane.add(lblNewLabel_1_1);

		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtName.setFont(new Font("Dialog", Font.BOLD, 14));
		txtName.setBounds(244, 124, 271, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtPrice = new JTextField();
		txtPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();

			}
		});
		txtPrice.setFont(new Font("Dialog", Font.BOLD, 14));
		txtPrice.setColumns(10);
		txtPrice.setBounds(244, 214, 271, 20);
		contentPane.add(txtPrice);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Product product = new Product();
				product.setName(txtName.getText());
				product.setCategory((String) comBoxCategory.getSelectedItem());
				product.setPrice(txtPrice.getText());
				ProductDao.save(product);
				setVisible(false);
				new AddNewProduct().setVisible(true);
			}
		});

		btnSave.setIcon(new ImageIcon(AddNewProduct.class.getResource("/images/save.png")));
		btnSave.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSave.setBounds(241, 245, 102, 23);
		contentPane.add(btnSave);

		JButton brnClear = new JButton("Clear");
		brnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new AddNewProduct().setVisible(true);
			}
		});
		brnClear.setIcon(new ImageIcon(AddNewProduct.class.getResource("/images/clear.png")));
		brnClear.setFont(new Font("Dialog", Font.BOLD, 14));
		brnClear.setBounds(419, 245, 96, 23);
		contentPane.add(brnClear);

		comBoxCategory.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();

			}
		});
		comBoxCategory.setBounds(244, 166, 271, 22);
		contentPane.add(comBoxCategory);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(AddNewProduct.class.getResource("/images/full-page-background.PNG")));
		lblNewLabel_1.setBounds(0, 0, 700, 400);
		contentPane.add(lblNewLabel_1);
		setUndecorated(true);

		btnSave.setEnabled(false);

	}

	public void clear() {

	}

	public void validateFields() {
		String name = txtName.getText();
		String price = txtPrice.getText();

		if (!name.equals("") && !price.equals("")) {
			btnSave.setEnabled(true);
		} else {

			btnSave.setEnabled(false);
		}
	}
}
