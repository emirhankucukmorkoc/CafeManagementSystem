package dao;

import java.sql.ResultSet;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Bill;

public class BillDao {
	public static void save(Bill bill) {
		String query = "INSERT INTO bill (id, name, mobileNumber, email, date, total, createdBy) VALUES ('"
				+ bill.getId() + "', '" + bill.getName() + "', '" + bill.getMobileNumber() + "', '" + bill.getEmail()
				+ "', '" + bill.getDate() + "', '" + bill.getTotal() + "', '" + bill.getCreatedBy() + "')";

		DbOperations.setDataOrDelete(query, "Bill Added Successfully");
	}

	public static String getId() {
		int id = 1;

		try {
			ResultSet rs = DbOperations.getData("select max(id) from bill");
			while (rs.next()) {
				id = rs.getInt(1);
				id = id + 1;

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return String.valueOf(id);
	}

	public static void update(Bill bill) {
		String query = "update bill set name ='" + bill.getName() + "', mobileNumber ='" + bill.getMobileNumber()
				+ "' , email ='" + bill.getEmail() + "', date ='" + bill.getDate() + "', total ='" + bill.getTotal()
				+ "', createdBy ='" + bill.getCreatedBy() + "' where id ='" + bill.getId() + "'";
		DbOperations.setDataOrDelete(query, "Bill Updated Successfully");

	}

	public static void delete(String id) {
		String query = "delete from bill where id ='" + id + "'";
		DbOperations.setDataOrDelete(query, "Bill Deleted Successfully");

	}

	public static ArrayList<Bill> getAllRecordsInc(String date) {
		ArrayList<Bill> arrayList = new ArrayList<>();
		try {
			ResultSet rs = DbOperations.getData("select *from bill where date like '%" + date + "%' ");
			while (rs.next()) {
				Bill bill = new Bill();
				bill.setId(rs.getInt("id"));
				bill.setName(rs.getString("name"));
				bill.setMobileNumber(rs.getString("mobileNumber"));
				bill.setEmail(rs.getString("email"));
				bill.setDate(rs.getString("date"));
				bill.setTotal(rs.getString("total"));
				bill.setCreatedBy(rs.getString("createdBy"));

				arrayList.add(bill);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return arrayList;
	}

	public static ArrayList<Bill> getAllRecordsByDesc(String date) {
		ArrayList<Bill> arrayList = new ArrayList<>();
		try {
			ResultSet rs = DbOperations.getData("select *from bill where date like '%" + date + "%'order By id DESC");
			while (rs.next()) {
				Bill bill = new Bill();
				bill.setId(rs.getInt("id"));
				bill.setName(rs.getString("name"));
				bill.setMobileNumber(rs.getString("mobileNumber"));
				bill.setEmail(rs.getString("email"));
				bill.setDate(rs.getString("total"));
				bill.setTotal(rs.getString("total"));
				bill.setCreatedBy(rs.getString("createdBy"));

				arrayList.add(bill);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return arrayList;
	}
}
