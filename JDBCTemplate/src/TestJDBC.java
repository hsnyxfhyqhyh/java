import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestJDBC {

	public static void main(String[] args) {
		TestJDBC mc = new TestJDBC();

	}

	public TestJDBC() {

		String driverName = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
		String connName = "jdbc:microsoft:sqlserver://localhost:1433;User=sa;Password=password;DatabaseName=dbTest";
		Connection conn = null;
		PreparedStatement pstmt1 = null;

		ResultSet rs = null;
		PrintWriter out = null;

		String query = "SELECT ?????????????";
		try {
			//prepare db related objects
			Class.forName(driverName).newInstance();

			conn = DriverManager.getConnection(connName);
			pstmt1 = conn.prepareStatement(query);
			rs = pstmt1.executeQuery();

			out = new PrintWriter(new FileOutputStream("members.csv"));

			if (rs != null) {
				
				while (rs.next()) {
					//rs.getString("NAME");
				}
			}
			
			//destroy all db related objects
			rs.close();
			pstmt1.close();
			conn.close();

			rs = null;
			pstmt1 = null;
			conn = null;
			
			//destroy all file stream related objects
			out.flush();
			out.close();
			out = null;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");
	}
}
