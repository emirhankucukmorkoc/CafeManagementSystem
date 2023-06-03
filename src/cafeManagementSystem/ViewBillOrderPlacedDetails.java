package cafeManagementSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import common.OpenPdf;
import dao.BillDao;
import model.Bill;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ViewBillOrderPlacedDetails extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField txtFilterByDate;
	private JTable table;
	JComboBox cmbChangeOrderById = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBillOrderPlacedDetails frame = new ViewBillOrderPlacedDetails();
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
	public ViewBillOrderPlacedDetails() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				tableDetails();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("View Bill & Order Placed Details");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setIcon(
				new ImageIcon(ViewBillOrderPlacedDetails.class.getResource("/images/view edit delete product.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 252, 35);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ViewBillOrderPlacedDetails.class.getResource("/images/close.png")));
		btnNewButton.setBounds(1315, 7, 41, 23);
		contentPane.add(btnNewButton);

		JLabel lblFilter = new JLabel("Filter  By Date");
		lblFilter.setForeground(new Color(255, 255, 255));
		lblFilter.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFilter.setBounds(265, 140, 114, 19);
		contentPane.add(lblFilter);

		JLabel lblNewLabel_1_1 = new JLabel("Change Order By Id");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(707, 140, 151, 19);
		contentPane.add(lblNewLabel_1_1);

		txtFilterByDate = new JTextField();
		txtFilterByDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableDetails();
			}
		});
		txtFilterByDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtFilterByDate.setBounds(377, 138, 262, 20);
		contentPane.add(txtFilterByDate);
		txtFilterByDate.setColumns(10);

		cmbChangeOrderById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableDetails();
			}
		});
		cmbChangeOrderById.setFont(new Font("Tahoma", Font.BOLD, 13));
		cmbChangeOrderById.setModel(new DefaultComboBoxModel(new String[] { "INC", "DESC" }));
		cmbChangeOrderById.setBounds(880, 137, 262, 22);
		contentPane.add(cmbChangeOrderById);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 189, 1315, 448);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = table.getSelectedRow();
				if (index != -1) {
					TableModel model = table.getModel();
					String id = model.getValueAt(index, 0).toString();
					OpenPdf.openById(id);
				}
			}

		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Name", "Mobile Number", "Email", "Date", "Total", "Created By" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);

		JLabel lblNewLabel_1 = new JLabel("*CLICK ON THE ROW  TO OPEN BILL");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Imprint MT Shadow", Font.BOLD, 30));
		lblNewLabel_1.setBounds(393, 672, 663, 61);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setIcon(
				new ImageIcon(ViewBillOrderPlacedDetails.class.getResource("/images/full-page-background.PNG")));
		lblNewLabel_2.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel_2);
		setUndecorated(true);

		SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String todayDate = dFormat.format(date);
		txtFilterByDate.setText(todayDate);

	}

	public void tableDetails() {
		String date = txtFilterByDate.getText();
		String incDec = (String) cmbChangeOrderById.getSelectedItem();

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);

		if (incDec.equals("INC")) {
			ArrayList<Bill> list = BillDao.getAllRecordsInc(date);
			Iterator<Bill> itr = list.iterator();
			while (itr.hasNext()) {
				Bill billObj = itr.next();
				dtm.addRow(new Object[] { billObj.getId(), billObj.getName(), billObj.getMobileNumber(),
						billObj.getEmail(), billObj.getDate(), billObj.getTotal(), billObj.getCreatedBy() });

			}
		} else {
			ArrayList<Bill> list = BillDao.getAllRecordsByDesc(date);
			Iterator<Bill> itr = list.iterator();
			while (itr.hasNext()) {
				Bill billObj = itr.next();
				dtm.addRow(new Object[] { billObj.getId(), billObj.getName(), billObj.getMobileNumber(),
						billObj.getEmail(), billObj.getDate(), billObj.getTotal(), billObj.getCreatedBy() });

			}
		}
	}
}
