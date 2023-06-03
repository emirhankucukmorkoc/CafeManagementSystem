package cafeManagementSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.CategoryDao;
import dao.ProductDao;
import model.Category;
import model.Product;

import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewEditDeleteProduct extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtPrice;
	private JTable table;
	JButton btnUpdate = new JButton("Update");
	JButton btnDelete = new JButton("Delete");
	JComboBox comboBoxCategory = new JComboBox();
	private JTable table_1;
	JLabel lblId = new JLabel("ID");
	JLabel lblNewLabel_3 = new JLabel("00");








	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEditDeleteProduct frame = new ViewEditDeleteProduct();
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
	public ViewEditDeleteProduct() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				DefaultTableModel dtm = (DefaultTableModel)table_1.getModel();
				ArrayList<Product> list = ProductDao.getAllRecords();
				Iterator<Product> itr = list.iterator();
				while(itr.hasNext()) {
					Product productObj = itr.next();
					dtm.addRow(new Object[] {productObj.getId(), productObj.getName(), productObj.getCategory(), productObj.getPrice()});
					
					
					
				}
				
			}
		});
        setUndecorated(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(ViewEditDeleteProduct.class.getResource("/images/close.png")));
		btnNewButton.setBounds(1294, 11, 46, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(763, 152, 497, 523);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Category", "Price"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table_1.getSelectedRow();
				TableModel model = table_1.getModel();
				String id = model.getValueAt(index, 0).toString();
				lblNewLabel_3.setText(id);
				String name = model.getValueAt(index , 1).toString();
				txtName.setText(name);
				String category = model.getValueAt(index, 2).toString();
				String price = model.getValueAt(index, 3).toString();
				txtPrice.setText(category);
				
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				
				comboBoxCategory.removeAllItems();
				comboBoxCategory.addItem(category);
				
				ArrayList<Category>  categories = CategoryDao.getAllRecords();
				Iterator<Category> categitr = categories.iterator();
				while(categitr.hasNext()) {
					Category categoryObj = categitr.next();
					if (!categoryObj.getName().equals(category)) {
						comboBoxCategory.addItem(categoryObj.getName());
						
						
					}
					
				}
				
				
				
			}
		});
		
		comboBoxCategory.setBounds(216, 332, 370, 22);
		contentPane.add(comboBoxCategory);
		
		JLabel lblNewLabel = new JLabel("View, Edit  & Delete Product");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon(ViewEditDeleteProduct.class.getResource("/images/view edit delete product.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 238, 28);
		contentPane.add(lblNewLabel);
		
		lblId.setForeground(new Color(255, 255, 255));
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(114, 253, 106, 14);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(114, 292, 92, 14);
		contentPane.add(lblName);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(new Color(255, 255, 255));
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCategory.setBounds(114, 334, 92, 23);
		contentPane.add(lblCategory);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(255, 255, 255));
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrice.setBounds(114, 379, 99, 17);
		contentPane.add(lblPrice);
		
		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
				
			}
		});
		txtName.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtName.setColumns(10);
		txtName.setBounds(216, 289, 370, 20);
		contentPane.add(txtName);
		
		txtPrice = new JTextField();
		txtPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPrice.setColumns(10);
		txtPrice.setBounds(216, 376, 370, 20);
		contentPane.add(txtPrice);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product product = new Product();
				int id = Integer.parseInt(lblNewLabel_3.getText());
				product.setId(id);
				product.setName(txtName.getText());
				product.setCategory((String)comboBoxCategory.getSelectedItem());
				product.setPrice(txtPrice.getText());
				
				ProductDao.update(product);
				setVisible(false);
				new ViewEditDeleteProduct().setVisible(true);;
				
				
			}
		});
		
		btnUpdate.setIcon(new ImageIcon(ViewEditDeleteProduct.class.getResource("/images/save.png")));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.setBounds(216, 421, 115, 23);
		contentPane.add(btnUpdate);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = lblNewLabel_3.getText();
				int a = JOptionPane.showConfirmDialog(null, "Do you want to Delete this product","Select",JOptionPane.YES_NO_OPTION);
				if (a==0) {
					ProductDao.delete(id);
					setVisible(false);
					new ViewEditDeleteProduct().setVisible(true);
					
					
				}
			}
		});
		
		btnDelete.setIcon(new ImageIcon(ViewEditDeleteProduct.class.getResource("/images/delete.png")));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBounds(341, 421, 118, 23);
		contentPane.add(btnDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ViewEditDeleteProduct().setVisible(true);
				
			}
		});
		btnClear.setIcon(new ImageIcon(ViewEditDeleteProduct.class.getResource("/images/clear.png")));
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBounds(471, 421, 115, 23);
		contentPane.add(btnClear);
		
		
		
		
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(216, 255, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ViewEditDeleteProduct.class.getResource("/images/full-page-background.PNG")));
		lblNewLabel_2.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel_2);
		
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		

	}
	public  void validateFields() {
		String name = txtName.getText();
		String price = txtPrice.getText();
		String category = (String)comboBoxCategory.getSelectedItem();
		if (!name.equals("")&& !price.equals("") && category !=null) {
			btnUpdate.setEnabled(true);
			
		}else {
			btnUpdate.setEnabled(false);
		}
		
	}
}
