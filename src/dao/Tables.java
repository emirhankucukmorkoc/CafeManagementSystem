package dao;

import javax.swing.JOptionPane;

public class Tables {
	public static void main(String[] args) {
		try {
			String userTable = "CREATE TABLE user (id int AUTO_INCREMENT PRIMARY KEY, name varchar(200), email varchar(200), mobileNumber varchar(20), address varchar(200), password varchar(200), securityQuestion varchar(200), answer varchar(200), status varchar(20), UNIQUE (email))";
			String adminDetails = "insert into user (name,email,mobileNumber,address,password,securityQuestion,answer,status) values ('Admin','admi3n@gmail.com','1234567890','Turkey','123','What is your pet name ?','tom','true')";
			String categoryTable = "create table category(id int AUTO_INCREMENT primary key,name varchar(200))";
			String productTable = "CREATE TABLE product (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(200), category VARCHAR(200), price VARCHAR(200))";
			String billTable = "CREATE TABLE bill (id INT PRIMARY KEY, name VARCHAR(200), mobileNumber VARCHAR(20), email VARCHAR(200), date VARCHAR(50), total VARCHAR(200), createdBy VARCHAR(200))";

			DbOperations.setDataOrDelete(userTable, "User Table Created Successfully"); 
			DbOperations.setDataOrDelete(adminDetails, "Admin Details Added Successfully"); 
			DbOperations.setDataOrDelete(categoryTable, "Category Table Created Successfully"); 
			DbOperations.setDataOrDelete(productTable, "Product Table Created Successfully");
			DbOperations.setDataOrDelete(billTable, "Bill Table Created Successfully"); 



		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}

 