package cafeManagementSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.*;

import dao.CategoryDao;
import dao.UserDao;
import model.Category;
import model.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;

import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
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

public class ManageCategory extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table3;
	private JTextField txtName;
	JButton btnSave = new JButton("Save");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageCategory frame = new ManageCategory();
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
	public ManageCategory() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table3.getModel();
				ArrayList<Category> list = CategoryDao.getAllRecords();
				Iterator<Category> itr = list.iterator();
				while (itr.hasNext()) {
					Category categoryObj = itr.next();
					dtm.addRow(new Object[] { categoryObj.getId(), categoryObj.getName() });

				}

			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 250, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 350, 134));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);

		JLabel lblManageCategory = new JLabel("Manage Category");
		lblManageCategory.setForeground(new Color(255, 255, 255));
		lblManageCategory.setBounds(10, 11, 187, 30);
		lblManageCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblManageCategory.setIcon(new ImageIcon(ManageCategory.class.getResource("/images/category.png")));
		contentPane.add(lblManageCategory);

		JButton btnExit = new JButton("");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnExit.setBounds(642, 11, 32, 23);
		btnExit.setIcon(new ImageIcon(ManageCategory.class.getResource("/images/close.png")));
		contentPane.add(btnExit);

		JLabel lblNewLabel = new JLabel("View Category");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(427, 38, 132, 16);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane(table3);

		scrollPane.setBounds(358, 65, 281, 260);
		contentPane.add(scrollPane);

		table3 = new JTable();
		table3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table3.getSelectedRow();
				if (index != -1) {
					TableModel model = table3.getModel();
					String id = model.getValueAt(index, 0).toString();
					String name = model.getValueAt(index, 1).toString();

					int a = JOptionPane.showConfirmDialog(null, "Do you want to delete " + name + " Category", "Select",
							JOptionPane.YES_NO_OPTION);
					if (a == JOptionPane.YES_OPTION) {
						CategoryDao.delete(id);
						DefaultTableModel tableModel = (DefaultTableModel) table3.getModel();
						tableModel.removeRow(index);
					}
				}
			}
		});
		table3.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Category" }) {
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table3.getColumnModel().getColumn(0).setPreferredWidth(134);
		table3.getColumnModel().getColumn(1).setPreferredWidth(139);
		scrollPane.setViewportView(table3);

		JLabel lblNewLabel_1 = new JLabel("*Click on row to Delete Category");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(415, 336, 202, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Add New Category");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(96, 105, 147, 16);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_2);

		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();
			}
		});
		txtName.setBounds(41, 134, 240, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Category category = new Category();
				category.setName(txtName.getText());
				CategoryDao.save(category);
				setVisible(false);
				new ManageCategory().setVisible(true);
			}
		});

		btnSave.setBounds(41, 158, 104, 23);
		btnSave.setIcon(new ImageIcon(ManageCategory.class.getResource("/images/save.png")));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnSave);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ManageCategory().setVisible(true);
			}
		});
		btnClear.setBounds(177, 158, 104, 23);
		btnClear.setIcon(new ImageIcon(ManageCategory.class.getResource("/images/clear.png")));
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnClear);

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(ManageCategory.class.getResource("/images/small-page-background.png")));
		lblNewLabel_3.setBounds(0, 0, 700, 400);
		contentPane.add(lblNewLabel_3);

		btnSave.setEnabled(false);

	}

	public void clear() {
		txtName.setText("");
		btnSave.setEnabled(false);
	}

	public void validateField() {
		String category = txtName.getText();
		if (!category.equals("")) {
			btnSave.setEnabled(true);

		} else {
			btnSave.setEnabled(false);
		}
	}
}
