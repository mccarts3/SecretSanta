package test;
import secretsanta.Emailer;
import java.util.Scanner;

public class Tester {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter username: ");
		String username = in.next();
		System.out.print("Enter the email host for " + username + ": @");
		String emailHost = "@" + in.next();
		Emailer email = new Emailer(username, username+emailHost);
		
		email.sendEmail("mccarts3@miamioh.edu", "Kevin Kane");
		email.sendEmail("smccartney09@gmail.com", "Scott McCartney");
	}
}