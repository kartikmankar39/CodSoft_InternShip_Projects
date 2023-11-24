package p1;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertStudentDAO {
	int k=0;
	public int insert(Student st) {
	try {
		 Connection con = DBConnection.getCon();
		 PreparedStatement ps = 
				 con.prepareStatement("insert into studentdetails values(?,?,?,?,?)");
		    ps.setString(1, st.getRollNo());
		    ps.setString(2, st.getName());
		    ps.setString(3, st.getDept());
		    ps.setInt(4,st.getTotMarks());
		    ps.setString(5, st.getGrade());
		    
		    k = ps.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		return k;
		 }
}
