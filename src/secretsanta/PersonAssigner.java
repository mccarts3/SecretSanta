package secretsanta;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PersonAssigner {
    public static void main(String[] args) {
		ArrayList<Person> people = new ArrayList<Person>();
	    String fileName = "./names.csv";
	    Random generator = new Random();
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
			    	people.add(new Person(name, email));
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
		
		ArrayList<String> remainingNames = new ArrayList<String>();
		for(Person p : people) {
			remainingNames.add(p.getName());
		}
		
		int currAssignee = 0;
		while(remainingNames.size() > 0) {
			int randIndex = generator.nextInt(remainingNames.size());

			if(remainingNames.get(randIndex) != people.get(currAssignee).getName()) {
				people.get(currAssignee).setSecretSanta(remainingNames.remove(randIndex));
				currAssignee++;
			} else if(remainingNames.size() == 1) {
				String tempName = people.get(currAssignee-1).getSecretSanta();
				people.get(currAssignee-1).setSecretSanta(remainingNames.remove(remainingNames.size()-1));
				people.get(currAssignee).setSecretSanta(tempName);
			}
		}
		
		/*
		 * Comment out the following to add email capabilities
		 */
//		Scanner in = new Scanner(System.in);
//		System.out.print("Enter username: ");
//		String username = in.next();
//		System.out.print("Enter the email host for " + username + ": @");
//		String emailHost = "@" + in.next();
//		Emailer email = new Emailer(username, username+emailHost);
//		in.close();
		
		for(Person p : people) {
		    System.out.println(p.getName() + "," + p.getEmail() + "," + p.getSecretSanta());
//			email.sendEmail(p.getEmail(), p.getSecretSanta());
		}
    }
}
