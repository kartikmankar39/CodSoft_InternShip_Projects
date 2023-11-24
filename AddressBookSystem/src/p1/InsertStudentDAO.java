package p1;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertStudentDAO {
	int k=0;
	public int insert(Contact ct) {
	try {
		 Connection con = DBConnection.getCon();
		 PreparedStatement ps = 
				 con.prepareStatement("insert into contactDeatails values(?,?,?,?,?,?)");
		    ps.setString(1, ct.getName());
		    ps.setLong(2, ct.getPhNo());
		    ps.setString(3, ct.getEmailId());
		    ps.setString(4,ct.getCity());
		    ps.setInt(5, ct.getPinCode());
		    ps.setString(6, ct.getCountry());
		    k = ps.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		return k;
		 }
}
