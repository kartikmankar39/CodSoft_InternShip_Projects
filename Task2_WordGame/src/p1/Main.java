package p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

	public static void main(String[] arg) throws IOException {
		System.out.println("*********Word Counter*********");
		System.out.println("Enter the text or provide the text file path:");
		Scanner s = new Scanner(System.in);
		String s1 = s.nextLine();
		int count = 0;
	 
		if (s1!=null) {
			if (s1.endsWith("txt")) {
				String s2 = new String(Files.readAllBytes(Paths.get(s1)));
				String[] str1 = s2.split(" ");
				for (int i = 0; i < str1.length; i++) {
					count++;

				}
			} else {
				String[] str1 = s1.split(" ");

				for (int i = 0; i < str1.length; i++) {
					count++;
				}
			}
			System.out.println("The total Number of word is:" + count);

		} else {
			System.out.println("Please provide valid input");
		
		}
	}
}
