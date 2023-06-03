package cafeManagementSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.multi.MultiTreeUI;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import common.OpenPdf;
import dao.BillDao;
import dao.CategoryDao;
import dao.ProductDao;
import dao.UserDao;
import model.Bill;
import model.Category;
import model.Product;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class PlaceOrder extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtMobileNumber;
	private JTable table;
	private JTextField txtSearch;
	private JTextField txtName2;
	private JTextField txtPrice;
	private JTextField txtTotal;
	private JTable table_1;
	public String userEmail;
	public int billId = 1;
	public int grandTotal = 0;
	public int productPrice = 1;
	public int productTotal = 0;
	JLabel lblc5 = new JLabel("Bill ID : ");
	JSpinner spinner = new JSpinner();
	JComboBox cmbCategory = new JComboBox();
	public String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	public String mobileNumberPattern = "^[0-9]*$";
	JButton btnAddToCart = new JButton("Add to Cart");
	JButton btnGenerateBill = new JButton("Generate Bill & Print");
	JLabel lblBillId = new JLabel("--");
	JLabel lblGrandTotal = new JLabel("00");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlaceOrder frame = new PlaceOrder();
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

	public PlaceOrder(String email) {

		btnAddToCart.setEnabled(false);
		btnGenerateBill.setEnabled(false);
		JFormattedTextField tf = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
		tf.setEnabled(false);
		userEmail = email;

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				billId = Integer.parseInt(BillDao.getId());
				lblBillId.setText(BillDao.getId());

				ArrayList<Category> list = CategoryDao.getAllRecords();
				Iterator<Category> itr = list.iterator();

				while (itr.hasNext()) {
					Category categoryObj = itr.next();
					cmbCategory.addItem(categoryObj.getName());
				}
				String category = (String) cmbCategory.getSelectedItem();
				productNameByCategory(category);
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int quantity = (Integer) spinner.getValue();
				if (quantity <= 1) {
					spinner.setValue(1);
					quantity = 1;

				}
				productTotal = productPrice * quantity;
				txtTotal.setText(String.valueOf(productTotal));
			}
		});

		spinner.setBounds(697, 240, 202, 20);
		contentPane.add(spinner);

		lblGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGrandTotal.setBounds(814, 697, 46, 14);
		contentPane.add(lblGrandTotal);

		JLabel lblNewLabel_4 = new JLabel("Grand Total: Rs:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(697, 695, 114, 14);
		contentPane.add(lblNewLabel_4);

		btnGenerateBill.setIcon(new ImageIcon(PlaceOrder.class.getResource("/images/generate bill & print.png")));
		btnGenerateBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customerName = txtName.getText();
				String customerMobileNumber = txtMobileNumber.getText();
				String customerEmail = txtEmail.getText();

				SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date date = new Date();
				String todayDate = dFormat.format(date);
				String total = String.valueOf(grandTotal);
				String createdBy = userEmail;

				Bill bill = new Bill();
				bill.setId(billId);
				bill.setName(customerName);
				bill.setMobileNumber(customerMobileNumber);
				bill.setEmail(customerEmail);
				bill.setDate(todayDate);
				bill.setTotal(total);
				bill.setCreatedBy(createdBy);
				BillDao.save(bill);

				// Creating Document
				String path = "C:\\Users\\emirh\\OneDrive\\Masaüstü\\Cafe Image & Icon\\pdfs\\";
				com.itextpdf.text.Document doc = new com.itextpdf.text.Document();

				try {
					PdfWriter.getInstance(doc, new FileOutputStream(path + "" + billId + ".pdf"));
					doc.open();

					Paragraph cafeName = new Paragraph(
							"                                                                Cafe Management System\n");
					doc.add(cafeName);

					Paragraph starLine = new Paragraph(
							"******************************************************************************************************************");
					doc.add(starLine);

					Paragraph paragraph3 = new Paragraph("\tBill ID : " + billId + "\nCustomer Name : " + customerName
							+ "\n Total Paid : " + grandTotal);
					doc.add(paragraph3);
					doc.add(starLine);

					PdfPTable tb1 = new PdfPTable(4);
					tb1.addCell("Name");
					tb1.addCell("Price");
					tb1.addCell("Quantity");
					tb1.addCell("Total");

					for (int i = 0; i < table_1.getRowCount(); i++) {
						String n = table_1.getValueAt(i, 0).toString();
						String d = table_1.getValueAt(i, 0).toString();
						String j = table_1.getValueAt(i, 0).toString();
						String r = table_1.getValueAt(i, 0).toString();

						tb1.addCell(n);
						tb1.addCell(d);
						tb1.addCell(j);
						tb1.addCell(r);

					}

					doc.add(tb1);
					doc.add(starLine);

					Paragraph thankMessag = new Paragraph("Thank you, Please visit again");
					doc.add(thankMessag);

					// open pdf
					OpenPdf.openById(String.valueOf(billId));

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				doc.close();
				setVisible(false);
				new PlaceOrder(createdBy).setVisible(true);
			}
		});

		btnGenerateBill.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGenerateBill.setBounds(1109, 689, 202, 23);
		contentPane.add(btnGenerateBill);

		JLabel lblNewLabel = new JLabel("Place Order");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon(PlaceOrder.class.getResource("/images/place order.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 123, 30);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Home(userEmail).setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(PlaceOrder.class.getResource("/images/close.png")));
		btnNewButton.setBounds(1315, 17, 41, 23);
		contentPane.add(btnNewButton);

		lblc5.setForeground(new Color(255, 255, 255));
		lblc5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblc5.setBounds(54, 95, 58, 14);
		contentPane.add(lblc5);

		JLabel lblNewLabel_1_1 = new JLabel("Customer Details :");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(54, 155, 139, 23);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Name");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(54, 205, 92, 24);
		contentPane.add(lblNewLabel_1_1_1);

		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtName.setColumns(10);
		txtName.setBounds(54, 240, 201, 20);
		contentPane.add(txtName);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();

			}
		});
		txtEmail.setColumns(10);
		txtEmail.setBounds(54, 410, 201, 20);
		contentPane.add(txtEmail);

		txtMobileNumber = new JTextField();
		txtMobileNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtMobileNumber.setColumns(10);
		txtMobileNumber.setBounds(54, 320, 201, 20);
		contentPane.add(txtMobileNumber);

		lblBillId.setForeground(new Color(255, 255, 255));
		lblBillId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBillId.setBounds(114, 95, 46, 14);
		contentPane.add(lblBillId);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Category");
		lblNewLabel_1_1_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2.setBounds(374, 95, 92, 30);
		contentPane.add(lblNewLabel_1_1_1_2);

		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Search");
		lblNewLabel_1_1_1_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_1.setBounds(374, 205, 92, 27);
		contentPane.add(lblNewLabel_1_1_1_2_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(374, 304, 236, 437);
		contentPane.add(scrollPane_1);

		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				String productName = model.getValueAt(index, 0).toString();
				txtSearch.setText(productName);
				Product product = ProductDao.getProductByName(productName);
				txtName2.setText(product.getName());
				txtPrice.setText(product.getPrice());
				txtTotal.setText(product.getPrice());
				spinner.setValue(1);

				productPrice = Integer.parseInt(product.getPrice());
				productTotal = Integer.parseInt(product.getPrice());
				btnAddToCart.setEnabled(true);

			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Name" }));

		JLabel lblNewLabel_1_1_1_2_2 = new JLabel("Name");
		lblNewLabel_1_1_1_2_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_2.setBounds(697, 95, 92, 30);
		contentPane.add(lblNewLabel_1_1_1_2_2);

		JLabel lblNewLabel_1_1_1_2_2_1 = new JLabel("Quantity");
		lblNewLabel_1_1_1_2_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_2_1.setBounds(697, 207, 92, 25);
		contentPane.add(lblNewLabel_1_1_1_2_2_1);

		JLabel lblNewLabel_1_1_1_3 = new JLabel("Email");
		lblNewLabel_1_1_1_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_3.setBounds(54, 375, 92, 24);
		contentPane.add(lblNewLabel_1_1_1_3);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Mobile Number");
		lblNewLabel_1_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(54, 286, 108, 24);
		contentPane.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_2_2_2 = new JLabel("Price");
		lblNewLabel_1_1_1_2_2_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_2_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_2_2.setBounds(1108, 87, 92, 30);
		contentPane.add(lblNewLabel_1_1_1_2_2_2);

		JLabel lblNewLabel_1_1_1_2_2_2_1 = new JLabel("Total");
		lblNewLabel_1_1_1_2_2_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_2_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_2_2_1.setBounds(1108, 202, 92, 30);
		contentPane.add(lblNewLabel_1_1_1_2_2_2_1);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String name = txtSearch.getText();
				String category = (String) cmbCategory.getSelectedItem();
				filterByProductName(name, category);
			}
		});
		txtSearch.setColumns(10);
		txtSearch.setBounds(374, 240, 236, 20);
		contentPane.add(txtSearch);

		txtName2 = new JTextField();
		txtName2.setColumns(10);
		txtName2.setBounds(693, 128, 201, 20);
		contentPane.add(txtName2);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(1109, 128, 201, 20);
		contentPane.add(txtPrice);

		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(1109, 240, 201, 20);
		contentPane.add(txtTotal);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int index = table_1.getSelectedRow();
				int a = JOptionPane.showConfirmDialog(null, "Do you want to delete this product", "Select",
						JOptionPane.YES_NO_OPTION);
				if (a == 0) {
					TableModel model = table_1.getModel();
					String total = model.getValueAt(index, 3).toString();
					grandTotal = grandTotal - Integer.parseInt(total);
					lblGrandTotal.setText(String.valueOf(grandTotal));

					((DefaultTableModel) table_1.getModel()).removeRow(index);

				}

			}
		});
		scrollPane.setBounds(696, 333, 614, 328);
		contentPane.add(scrollPane);

		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Price", "Quantity", "Total" }));

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearProductFields();
			}
		});
		btnClear.setIcon(new ImageIcon(PlaceOrder.class.getResource("/images/clear.png")));
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBounds(697, 271, 114, 23);
		contentPane.add(btnClear);
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = txtName2.getText();
				String price = txtPrice.getText();
				String total = txtTotal.getText();
				String quantity = String.valueOf(spinner.getValue());

				DefaultTableModel dtm = (DefaultTableModel) table_1.getModel();
				dtm.addRow(new Object[] { name, price, quantity, productTotal });
				grandTotal += productTotal;
				lblGrandTotal.setText(String.valueOf(grandTotal));
				
				clearProductFields();
				validateFields();

			}
		});

		btnAddToCart.setIcon(new ImageIcon(PlaceOrder.class.getResource("/images/add to cart.png")));
		btnAddToCart.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddToCart.setBounds(1172, 271, 139, 23);
		contentPane.add(btnAddToCart);
		cmbCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String category = (String) cmbCategory.getSelectedItem();
				productNameByCategory(category);
			}

		});

		cmbCategory.setBounds(374, 138, 236, 22);
		contentPane.add(cmbCategory);

		JLabel back = new JLabel("");
		back.setIcon(new ImageIcon(PlaceOrder.class.getResource("/images/full-page-background.PNG")));
		back.setBounds(0, 0, 1366, 768);
		contentPane.add(back);
		setUndecorated(true);

	}

	public PlaceOrder() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				billId = Integer.parseInt(BillDao.getId());
				lblBillId.setText(BillDao.getId());

				ArrayList<Category> list = CategoryDao.getAllRecords();
				Iterator<Category> itr = list.iterator();

				while (itr.hasNext()) {
					Category categoryObj = itr.next();
					cmbCategory.addItem(categoryObj.getName());
				}
				String category = (String) cmbCategory.getSelectedItem();
				productNameByCategory(category);
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int quantity = (Integer) spinner.getValue();
				if (quantity <= 1) {
					spinner.setValue(1);
					quantity = 1;

				}
				productTotal = productPrice * quantity;
				txtTotal.setText(String.valueOf(productTotal));
			}
		});

		spinner.setBounds(697, 240, 202, 20);
		contentPane.add(spinner);
		lblGrandTotal.setForeground(new Color(255, 255, 255));

		lblGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGrandTotal.setBounds(821, 696, 46, 14);
		contentPane.add(lblGrandTotal);

		JLabel lblNewLabel_4 = new JLabel("Grand Total: Rs:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(697, 696, 114, 14);
		contentPane.add(lblNewLabel_4);

		btnGenerateBill.setIcon(new ImageIcon(PlaceOrder.class.getResource("/images/generate bill & print.png")));
		btnGenerateBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customerName = txtName.getText();
				String customerMobileNumber = txtMobileNumber.getText();
				String customerEmail = txtEmail.getText();

				SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date date = new Date();
				String todayDate = dFormat.format(date);
				String total = String.valueOf(grandTotal);
				String createdBy = userEmail;

				Bill bill = new Bill();
				bill.setId(billId);
				bill.setName(customerName);
				bill.setMobileNumber(customerMobileNumber);
				bill.setEmail(customerEmail);
				bill.setDate(todayDate);
				bill.setTotal(total);
				bill.setCreatedBy(createdBy);
				BillDao.save(bill);

				// Creating Document
				String path = "C:\\Users\\emirh\\OneDrive\\Masaüstü\\Cafe Image & Icon\\pdfs\\";
				com.itextpdf.text.Document doc = new com.itextpdf.text.Document();

				try {
					PdfWriter.getInstance(doc, new FileOutputStream(path + "" + billId + ".pdf"));
					doc.open();

					Paragraph cafeName = new Paragraph(
							"                                                                                        Cafe Management System\n");
					doc.add(cafeName);

					Paragraph starLine = new Paragraph(
							"****************************************************************************************************************************************************");
					doc.add(starLine);

					Paragraph paragraph3 = new Paragraph("\tBill ID : " + billId + "\nCustomer Name : " + customerName
							+ "\n Total Paid : " + grandTotal);
					doc.add(paragraph3);
					doc.add(starLine);

					PdfPTable tb1 = new PdfPTable(4);
					tb1.addCell("Name");
					tb1.addCell("Price");
					tb1.addCell("Quantity");
					tb1.addCell("Total");

					for (int i = 0; i < table_1.getRowCount(); i++) {
						String n = table_1.getValueAt(i, 0).toString();
						String d = table_1.getValueAt(i, 0).toString();
						String j = table_1.getValueAt(i, 0).toString();
						String r = table_1.getValueAt(i, 0).toString();

						tb1.addCell(n);
						tb1.addCell(d);
						tb1.addCell(j);
						tb1.addCell(r);

					}

					doc.add(tb1);
					doc.add(starLine);

					Paragraph thankMessag = new Paragraph("Thank you, Please visit again");
					doc.add(thankMessag);

					// open pdf
					OpenPdf.openById(String.valueOf(billId));

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				doc.close();
				setVisible(false);
				new PlaceOrder(createdBy).setVisible(true);
			}
		});

		btnGenerateBill.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGenerateBill.setBounds(1109, 689, 202, 23);
		contentPane.add(btnGenerateBill);

		JLabel lblNewLabel = new JLabel("Place Order");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon(PlaceOrder.class.getResource("/images/place order.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 123, 30);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Home(userEmail).setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(PlaceOrder.class.getResource("/images/close.png")));
		btnNewButton.setBounds(1315, 17, 41, 23);
		contentPane.add(btnNewButton);

		lblc5.setForeground(new Color(255, 255, 255));
		lblc5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblc5.setBounds(54, 95, 58, 14);
		contentPane.add(lblc5);

		JLabel lblNewLabel_1_1 = new JLabel("Customer Details :");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(54, 155, 139, 23);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Name");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(54, 205, 92, 24);
		contentPane.add(lblNewLabel_1_1_1);

		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtName.setColumns(10);
		txtName.setBounds(54, 240, 201, 20);
		contentPane.add(txtName);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();

			}
		});
		txtEmail.setColumns(10);
		txtEmail.setBounds(54, 410, 201, 20);
		contentPane.add(txtEmail);

		txtMobileNumber = new JTextField();
		txtMobileNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtMobileNumber.setColumns(10);
		txtMobileNumber.setBounds(54, 320, 201, 20);
		contentPane.add(txtMobileNumber);

		lblBillId.setForeground(new Color(255, 255, 255));
		lblBillId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBillId.setBounds(114, 95, 46, 14);
		contentPane.add(lblBillId);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Category");
		lblNewLabel_1_1_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2.setBounds(374, 95, 92, 30);
		contentPane.add(lblNewLabel_1_1_1_2);

		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Search");
		lblNewLabel_1_1_1_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_1.setBounds(374, 205, 92, 27);
		contentPane.add(lblNewLabel_1_1_1_2_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(374, 304, 236, 437);
		contentPane.add(scrollPane_1);

		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				String productName = model.getValueAt(index, 0).toString();
				txtSearch.setText(productName);
				Product product = ProductDao.getProductByName(productName);
				txtName2.setText(product.getName());
				txtPrice.setText(product.getPrice());
				txtTotal.setText(product.getPrice());
				spinner.setValue(1);

				productPrice = Integer.parseInt(product.getPrice());
				productTotal = Integer.parseInt(product.getPrice());
				btnAddToCart.setEnabled(true);

			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Name" }));

		JLabel lblNewLabel_1_1_1_2_2 = new JLabel("Name");
		lblNewLabel_1_1_1_2_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_2.setBounds(697, 95, 92, 30);
		contentPane.add(lblNewLabel_1_1_1_2_2);

		JLabel lblNewLabel_1_1_1_2_2_1 = new JLabel("Quantity");
		lblNewLabel_1_1_1_2_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_2_1.setBounds(697, 207, 92, 25);
		contentPane.add(lblNewLabel_1_1_1_2_2_1);

		JLabel lblNewLabel_1_1_1_3 = new JLabel("Email");
		lblNewLabel_1_1_1_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_3.setBounds(54, 375, 92, 24);
		contentPane.add(lblNewLabel_1_1_1_3);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Mobile Number");
		lblNewLabel_1_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(54, 286, 108, 24);
		contentPane.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_2_2_2 = new JLabel("Price");
		lblNewLabel_1_1_1_2_2_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_2_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_2_2.setBounds(1108, 87, 92, 30);
		contentPane.add(lblNewLabel_1_1_1_2_2_2);

		JLabel lblNewLabel_1_1_1_2_2_2_1 = new JLabel("Total");
		lblNewLabel_1_1_1_2_2_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_2_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_2_2_1.setBounds(1108, 202, 92, 30);
		contentPane.add(lblNewLabel_1_1_1_2_2_2_1);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String name = txtSearch.getText();
				String category = (String) cmbCategory.getSelectedItem();
				filterByProductName(name, category);
			}
		});
		txtSearch.setColumns(10);
		txtSearch.setBounds(374, 240, 236, 20);
		contentPane.add(txtSearch);

		txtName2 = new JTextField();
		txtName2.setColumns(10);
		txtName2.setBounds(693, 128, 201, 20);
		contentPane.add(txtName2);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(1109, 128, 201, 20);
		contentPane.add(txtPrice);

		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(1109, 240, 201, 20);
		contentPane.add(txtTotal);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int index = table_1.getSelectedRow();
				int a = JOptionPane.showConfirmDialog(null, "Do you want to delete this product", "Select",
						JOptionPane.YES_NO_OPTION);
				if (a == 0) {
					TableModel model = table_1.getModel();
					String total = model.getValueAt(index, 3).toString();
					grandTotal = grandTotal - Integer.parseInt(total);
					lblGrandTotal.setText(String.valueOf(grandTotal));
					((DefaultTableModel) table_1.getModel()).removeRow(index);

				}

			}
		});
		scrollPane.setBounds(696, 333, 614, 328);
		contentPane.add(scrollPane);

		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Price", "Quantity", "Total" }));

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearProductFields();
			}
		});
		btnClear.setIcon(new ImageIcon(PlaceOrder.class.getResource("/images/clear.png")));
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBounds(697, 271, 114, 23);
		contentPane.add(btnClear);
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = txtName2.getText();
				String price = txtPrice.getText();
				String total = txtTotal.getText();
				String quantity = String.valueOf(spinner.getValue());

				DefaultTableModel dtm = (DefaultTableModel) table_1.getModel();
				dtm.addRow(new Object[] { name, price, quantity, productTotal });
				grandTotal += productTotal;
				lblGrandTotal.setText(String.valueOf(grandTotal));
				
				clearProductFields();
				validateFields();

			}
		});

		btnAddToCart.setIcon(new ImageIcon(PlaceOrder.class.getResource("/images/add to cart.png")));
		btnAddToCart.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddToCart.setBounds(1172, 271, 139, 23);
		contentPane.add(btnAddToCart);
		cmbCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String category = (String) cmbCategory.getSelectedItem();
				productNameByCategory(category);
			}

		});

		cmbCategory.setBounds(374, 138, 236, 22);
		contentPane.add(cmbCategory);

		JLabel back = new JLabel("");
		back.setIcon(new ImageIcon(PlaceOrder.class.getResource("/images/full-page-background.PNG")));
		back.setBounds(0, 0, 1366, 768);
		contentPane.add(back);
		setUndecorated(true);

	}

	public void productNameByCategory(String category) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		ArrayList<Product> list = ProductDao.getAllRecordsByCategory(category);
		Iterator<Product> itr = list.iterator();
		while (itr.hasNext()) {
			Product productObj = itr.next();
			dtm.addRow(new Object[] { productObj.getName() });

		}

	}

	public void filterByProductName(String name, String category) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		ArrayList<Product> list = ProductDao.filterByProductName(name, category);
		Iterator<Product> itr = list.iterator();
		while (itr.hasNext()) {
			Product productObj = itr.next();
			dtm.addRow(new Object[] { productObj.getName() });

		}

	}

	public void clearProductFields() {
		txtName2.setText("");
		txtPrice.setText("");
		txtTotal.setText("");
		spinner.setValue(1);
		btnAddToCart.setEnabled(false);
	}

	public void validateFields() {
		String customerName = txtName.getText();
		String customerMobileNumber = txtMobileNumber.getText();
		String customerEmail = txtEmail.getText();
		if (!customerName.equals("") && customerMobileNumber.matches(mobileNumberPattern)
				&& customerEmail.matches(emailPattern) && customerMobileNumber.length() == 10) {

			btnGenerateBill.setEnabled(true);

		} else {
			btnGenerateBill.setEnabled(false);

		}
	}
}
