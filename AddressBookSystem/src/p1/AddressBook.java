package p1;

import java.util.Scanner;

public class AddressBook {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Operations op = new Operations();
        try(s;){
        	try {
        	Contact sb= new Contact();
        	while(true) {
        		System.out.println("****Choice****");
        		System.out.println("\t1.AddContactDetails"
        				+ "\n\t2.ViewContactDetails"
        				+"\n\t3.ViewContactDetailsByName"
        				+"\n\t4.DeleteContactDetailsByName"
        				+"\n\t5.Exit Aplication");
        		
        		System.out.println("Enter the Choice");
        		int choice = Integer.parseInt(s.nextLine());
        		
        		switch(choice) {
        		case 1:
        			   op.addContact();
        			break;
        		case 2:
    				op.displayAllContacts();
        			break;
        		case 3:
        			op.findContactByName();
        			break;
        		
        		case 4 :
        		     op.deleteContact();
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
        }//end o

	}

}
