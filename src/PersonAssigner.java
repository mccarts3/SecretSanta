import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PersonAssigner {
    public static void main(String[] args) {
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> assignedTo = new ArrayList<String>();
	    String fileName = "/Users/scott2/Desktop/EclipseProjects/SecretSanta/NameList.txt";
	    Random generator = new Random();
	    FileReader file;
	    BufferedReader reader;
	    
		try {
		    file = new FileReader(fileName);
		    reader = new BufferedReader(file);
		    while (true) {
			    String name = reader.readLine();
			    if (name == null) {
			    	break;
			    } else {
			    	names.add(name);
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
		for(int i=0; i<names.size(); i++) {
			remainingNames.add(names.get(i));
		}
		
		int currAssignee = 0;
		while(remainingNames.size() > 0) {
			int randIndex = generator.nextInt(remainingNames.size());
			
			/* 
			 * Test to see if remainingNames.get(randIndex) is equal to names.get(current number it'd be assigned to)
			 * 	 - If last  one, go back a few steps and try again
			 *   - If not the last one, don't add and try random number again
			 */
			if(remainingNames.get(randIndex) != names.get(currAssignee)) {
				assignedTo.add(remainingNames.remove(randIndex));
				currAssignee++;
			} else if(remainingNames.size() == 1) {
				String tempName = assignedTo.remove(assignedTo.size()-1);
				assignedTo.add(remainingNames.remove(remainingNames.size()-1));
				assignedTo.add(tempName);
			}
		}
		
		System.out.println(names.size());
		System.out.println(assignedTo.size());
		for(int i=0; i<names.size(); i++) {
		    if(names.get(i).equals(assignedTo.get(i))) {
		    	System.out.println(names.get(i) + " --- SAME PERSON");
		    }
		    System.out.println(names.get(i) + " --- " + assignedTo.get(i));
		}
    }
}
