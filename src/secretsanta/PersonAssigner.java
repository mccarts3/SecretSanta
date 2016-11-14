package secretsanta;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PersonAssigner {
    public static void main(String[] args) {
		ArrayList<Person> people = new ArrayList<Person>();
		ArrayList<String> assignedTo = new ArrayList<String>();
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
				assignedTo.add(remainingNames.remove(randIndex));
				currAssignee++;
			} else if(remainingNames.size() == 1) {
				String tempName = assignedTo.remove(assignedTo.size()-1);
				assignedTo.add(remainingNames.remove(remainingNames.size()-1));
				assignedTo.add(tempName);
			}
		}
		
		int i = 0;
		for(Person p : people) {
		    System.out.println(p.getName() + "   " + p.getEmail() + " --- " + assignedTo.get(i));
		    i++;
		}
    }
}
