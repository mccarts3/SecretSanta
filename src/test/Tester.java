package test;
import secretsanta.Emailer;
import secretsanta.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {
	public static void main(String[] args) {
		ArrayList<Person> people = new ArrayList<Person>();
	    String fileName = "./assignments.csv";
	    FileReader file;
	    BufferedReader reader;
		
		try {
		    file = new FileReader(fileName);
		    reader = new BufferedReader(file);
		    
		    while (true) {
		    	String line = reader.readLine();
		    	
			    if (line == null) {
			    	break;
			    } else {
			    	String[] data = line.split(",");
			    	String name = data[0];
				    String email = data[1];
				    String secretSanta = data[2];
			    	people.add(new Person(name, email, secretSanta));
			    }
		    }
		    
		    // Always close files.
		    reader.close();
		}
		catch(FileNotFoundException ex) {
		    System.out.println("File does not exist");                
		}
		catch(IOException execption) {
		    System.out.println("Error reading from File");                  
		}
		catch(Exception e) {
		    System.out.println(e.getMessage());
		}
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter username: ");
		String username = in.next();
		System.out.print("Enter the email host for " + username + ": @");
		String emailHost = "@" + in.next();
		Emailer email = new Emailer(username, username+emailHost);
		in.close();
		
		for(Person p : people) {
			System.out.println("Sending email to " + p.getEmail());
			email.sendEmail(p.getEmail(), p.getSecretSanta());
		}
	}
}