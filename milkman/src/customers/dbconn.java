package customers;

import java.sql.DriverManager;

import java.sql.Connection;

public class dbconn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
makeconnection();
	}
		public static Connection makeconnection()
		{
		Connection con=null;
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost/custform","root","");
				System.out.println("printedddddddddd");
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			return con;
		}
		
}
