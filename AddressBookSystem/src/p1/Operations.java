package p1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Operations {
	Scanner s = new Scanner(System.in);
	Connection con = DBConnection.getCon();
	
	
	public void addContact() throws SQLException {
		System.out.println("Enter the Name:");
		String name = s.nextLine();
		System.out.println("Enter the Phone Number:");
		long phno = Long.parseLong(s.nextLine());
		System.out.println("Enter the Email Address");
		String eid = s.nextLine();
		System.out.println("Enter the Pin Code");
		int pc = Integer.parseInt(s.nextLine());
		System.out.println("Enter the City");
		String city = s.nextLine();
		System.out.println("Enter the Country");
		String country = s.nextLine();

         Contact ct = new Contact();
         ct.setName(name);
         ct.setPhNo(phno);
         ct.setEmailId(eid);
         ct.setCity(city);
         ct.setCountry(country);
	     int k = new InsertStudentDAO().insert(ct);
	
		if(k>0) {
			System.out.println("Contact Details inserted Successfully...");
		}
		
	}
	public void displayAllContacts() throws SQLException {
		PreparedStatement ps2=con.prepareStatement(
    			"select* from contactDeatails");
		
		System.out.println("*****ContactDetails****");
		ResultSet rs =ps2.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString(1)+"\t"
					+rs.getLong(2)+"\t"
					+rs.getString(3)+"\t"
					+rs.getString(4)+"\t"
					+rs.getInt(5)+"\t"
					+rs.getString(6));
		}
	}
	
	public void findContactByName() throws SQLException {
		PreparedStatement ps3 = con.prepareStatement(
    			"select* from contactDeatails where name=?");
		
		System.out.println("Enter the name");
		String name= s.nextLine();
		 ps3.setString(1, name);
		 
		 ResultSet rs2 = ps3.executeQuery();
		 if(rs2.next()) {
			 System.out.println(rs2.getString(1)+"\t"
						+rs2.getLong(2)+"\t"
						+rs2.getString(3)+"\t"
						+rs2.getString(4)+"\t"
						+rs2.getInt(5)+"\t"
						+rs2.getString(6));
		 }else {
			 System.out.println("Invalid name.");
		 }
	}
	public void deleteContact() throws SQLException {
		PreparedStatement ps4 =con.prepareStatement("select* from contactDeatails where name=?");
		PreparedStatement ps5 = con.prepareStatement("delete from contactDeatails where name=?");
		System.out.println("Enter the name:");
		String name = s.nextLine();
		ps4.setString(1, name);
		ResultSet rs4 = ps4.executeQuery();
		if(rs4.next()) {
			ps5.setString(1, name);
			int k3 = ps5.executeUpdate();
			if(k3>0) {
				System.out.println("contact Details deleted Successfully...");
			}else {
				System.out.println("Invalid name.");
			}
		}
	}
	
	
	
	
}
