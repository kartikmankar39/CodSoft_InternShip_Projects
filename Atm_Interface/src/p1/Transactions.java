package p1;

public class Transactions {
    
	BankAccount b = new BankAccount();
	public void withdraw(int amt) {
		if(amt<b.bal) {
			System.out.println("Balance Amt:"+amt);
			b.bal=b.bal-amt;
			System.out.println("Balance amt:"+b.getBalance());
			System.out.println("Transaction Successful..");
		  }else {
			  System.out.println("Isufficient fund...");
		   }
	}
	
	public void deposit (int amt) {
		System.out.println("Amt deposited:"+amt);
		b.bal=b.bal+amt;
		System.out.println("Balance amt:"+b.getBalance());
		System.out.println("Transaction Successful..");
	}
	
	public void checkBalance() {
		System.out.println("Available Balance:"+b.getBalance());
	}

}
