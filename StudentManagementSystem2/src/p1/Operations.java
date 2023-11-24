package p1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Operations {
	Scanner s = new Scanner(System.in);
	Connection con = DBConnection.getCon();
	
	
	public void addStudent() throws SQLException {
		System.out.println("Enter the Roll Number:");
		String sr = s.nextLine();
		System.out.println("Enter the Name:");
		String sn = s.nextLine();
		System.out.println("Enter the Department:");
		String sd = s.nextLine();
		System.out.println("Enter the Total Marks:");
		int tm= Integer.parseInt(s.nextLine());
		System.out.println("Enter the Grade");
		String sg = s.nextLine();
		

         Student st = new Student();
         st.setRollNo(sr);
         st.setName(sn);
         st.setDept(sd);
         st.setTotMarks(tm);
         st.setGrade(sg);
	     int k = new InsertStudentDAO().insert(st);
	
		if(k>0) {
			System.out.println("Student Details inserted Successfully...");
		}
		
	}
	public void displayAllStudent() throws SQLException {
		PreparedStatement ps2=con.prepareStatement(
    			"select* from studentDetails");
		
		System.out.println("*****StudentDetails****");
		ResultSet rs =ps2.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString(1)+"\t"
					+rs.getString(2)+"\t"
					+rs.getString(3)+"\t"
					+rs.getInt(4)+"\t"
					+rs.getString(5)
					);
		}
	}
	
	public void findStudentByRollNo() throws SQLException {
		PreparedStatement ps3 = con.prepareStatement(
    			"select* from studentDetails where rollno=?");
		
		System.out.println("Enter the rollno");
		String rollno= s.nextLine();
		 ps3.setString(1, rollno);
		 
		 ResultSet rs2 = ps3.executeQuery();
		 if(rs2.next()) {
			 System.out.println(rs2.getString(1)+"\t"
						+rs2.getString(2)+"\t"
						+rs2.getString(3)+"\t"
						+rs2.getInt(4)+"\t"
						+rs2.getString(5)
						);
		 }else {
			 System.out.println("Invalid Roll No.");
		 }
	}
	public void deleteStudent() throws SQLException {
		PreparedStatement ps4 =con.prepareStatement("select* from studentDetails where rollno=?");
		PreparedStatement ps5 = con.prepareStatement("delete from studentDetails where rollno=?");
		System.out.println("Enter the roll No:");
		String rollno = s.nextLine();
		ps4.setString(1, rollno);
		ResultSet rs4 = ps4.executeQuery();
		if(rs4.next()) {
			ps5.setString(1, rollno);
			int k3 = ps5.executeUpdate();
			if(k3>0) {
				System.out.println("student Details deleted Successfully...");
			}else {
				System.out.println("Invalid Roll No.");
			}
		}
	}
	
	
	
	
}
