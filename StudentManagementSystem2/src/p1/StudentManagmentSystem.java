package p1;

import java.util.Scanner;

public class StudentManagmentSystem {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Operations op = new Operations();
        try(s;){
        	try {
        	Student sb= new Student();
        	while(true) {
        		System.out.println("****Choice****");
        		System.out.println("\t1.AddStudentDetails"
        				+ "\n\t2.ViewStudentDetails"
        				+"\n\t3.ViewStudentDetailsByName"
        				+"\n\t4.DeleteStudentDetailsByName"
        				+"\n\t5.Exit Aplication");
        		
        		System.out.println("Enter the Choice");
        		int choice = Integer.parseInt(s.nextLine());
        		
        		switch(choice) {
        		case 1:
        			   op.addStudent();
        			break;
        		case 2:
    				op.displayAllStudent();
        			break;
        		case 3:
        			op.findStudentByRollNo();
        			break;
        		
        		case 4 :
        		     op.deleteStudent();
        			break;
        		case 5:
        			System.out.println("Operation on Db Stopped...");
        			System.exit(0);
        			break;
        			default:
        				System.out.println("Invalid choice..");
        		}//end of switch
        		
        	}//end of loop
        	
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }

	}

}
