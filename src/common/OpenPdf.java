package common;

import java.io.File;

import javax.swing.JOptionPane;

public class OpenPdf {
	public static void openById(String id) {
		try {

			if ((new File("C:\\Users\\emirh\\OneDrive\\Masaüstü\\Cafe Image & Icon\\pdfs\\" + id + ".pdf")).exists()) {

				Process p = Runtime.getRuntime().exec(
						"rundll32 url.dll,FileProtocolHandler \"C:\\Users\\emirh\\OneDrive\\Masaüstü\\Cafe Image & Icon\\pdfs\\"
								+ id + ".pdf\"");

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "File does not exist");
		}

	}

}
