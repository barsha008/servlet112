
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

public class ProfileServlet{
	private static final long serialversionUID=1L;
	
	public int save(Profile profile) throws IOException {
		Connection con = null;
		Statement st = null;
		int res = 0;
		try {
			con = ConnectDB.dbconn();
			st = con.createStatement();
			res = st.executeUpdate("INSERT INTO profile (userid, name, email, mobile) VALUES ('"+profile.getUserid()+"', '"+profile.getName()+"', '"+profile.getEmail()+"', "+profile.getMobile()+")"); 
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally { 
			try {
				st.close();
				con.close();
			}
			
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public List<Profile> fetchAll() throws IOException {
		List<Profile> list=null;
		Connection con = null;
		Statement st = null;
		try {
			con = ConnectDB.dbconn();
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM profile");
			if(rs != null)
				list = new ArrayList<>();
			while(rs.next()) {
				Profile pr = new Profile();
				pr.setUserid(rs.getString("userid"));
				pr.setName(rs.getString("name"));
				pr.setEmail(rs.getString("email"));
				pr.setMobile(rs.getLong("mobile"));
				list.add(pr);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return list;
	}
	public static void main(String[] args) throws IOException {
		Connection con = null;
		Statement st = null;
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//con = DriverManager.getConnection("jdbc.oracle.thin.@localhost:1521:XE\",\"admin\",\"admin");
			con = ConnectDB.dbconn();
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM profile");
			while(rs.next()) {
				String id = rs.getString("userid");
				String name = rs.getString("name");
				String email = rs.getString("email");
				long mobile = rs.getLong("mobile");
				System.out.println("Id : " +id + "\tName : "+ name+ "\tEmail : " +email + "\tMobile : " +mobile );
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		

	}
	/*public List<Profile> fetchAll() throws IOException {
		Connection con = null;
		Statement st = null;
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//con = DriverManager.getConnection("jdbc.oracle.thin.@localhost:1521:XE\",\"admin\",\"admin");
			con = ConnectDB.dbconn();
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM profile");
			while(rs.next()) {
				String id = rs.getString("userid");
				String name = rs.getString("name");
				String email = rs.getString("email");
				long mobile = rs.getLong("mobile");
				System.out.println("Id : " +id + "\tName : "+ name+ "\tEmail : " +email + "\tMobile : " +mobile );
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}*/

}
