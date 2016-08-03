import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PersonAssigner {
    public static void main(String[] args) {
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> assignedTo = new ArrayList<String>();
		int[] indexesUsed = new int[78];
			
		try {
		    String fileName = "NameList.txt";
		    FileReader fileReader = new FileReader(fileName);
		    BufferedReader reader = new BufferedReader(fileReader);
	
		    for(int i=0; i<names.size(); i++) {
		    	names.add(reader.readLine());
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
			 
		for(int i=0; i<names.size(); i++) {
		    Random generator = new Random();
		    int ranNum = -1;
		    boolean isUsed = false;
				 
		    do {
			ranNum = generator.nextInt(names.size());
			
			isUsed = false;
					 
			for(int j = 0; j<i; j++) {
			    if(ranNum == indexesUsed[j]) 
				isUsed = true;
			}
		    } while (isUsed == true);
				 
		    assignedTo.add(names.get(i));
		    indexesUsed[i] = ranNum;
		}
			 
		for(int i=0; i<names.size(); i++) {
		    if(names.get(i).equals(assignedTo.get(i))) {
		    	System.out.println(names.get(i) + " --- SAME PERSON");
		    }
		    System.out.println(names.get(i) + " --- " + assignedTo.get(i));
		}
    }
}
