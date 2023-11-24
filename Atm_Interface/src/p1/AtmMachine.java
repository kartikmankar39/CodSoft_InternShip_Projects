package p1;

import java.util.Scanner;

public class AtmMachine {

	public static void main(String[] args) {
		System.out.println("********WElCOME********");
		Scanner s = new Scanner(System.in);
		int count = 0;
		pqr: while (true) {
			System.out.println("Enter the pinNo:");
			int pinNo = s.nextInt();
			if (pinNo >= 1111 && pinNo <= 9999) {
				CheckPinNo cpn = new CheckPinNo();
				boolean k = cpn.verify(pinNo);
				if (k) {
					System.out
							.println("Chosse Transaction:\n\t1.Withdrawl Ammount\n\t2.Deposit Ammount\n\t3.Check Balance");
					switch (s.nextInt()) {
					case 1:
						System.out.println("Enter Amount of withdraw:");
						int a1 = s.nextInt();
						if (a1 > 0 && a1 % 100 == 0) {
							Transactions t = new Transactions();
							t.withdraw(a1);
						} else {
							System.out.println("Invalid amt for Withdraw..");
						}

						break pqr;
					case 2:
						System.out.println("Enter the amt to deposit");
						int a2 = s.nextInt();
						if (a2 > 0 && a2 % 100 == 0) {
							Transactions t = new Transactions();
							t.deposit(a2);

						} else {
							System.out.println("Invalid amt for Deposit..");
						}
						break pqr;
					case 3:
						Transactions t = new Transactions();
						t.checkBalance();
						break pqr;
					default:
						System.out.println("Please choose option from above.");
						break;
					}
				} else {
					System.out.println("PinNo donot exists..");
					count++;
				}

			} else {
				System.out.println("Invalid pinNo..");
				count++;
			}
			if (count == 3) {
				System.out.println("Sorry! Transaction blocked");
				break;
			}
		}
	}
}
