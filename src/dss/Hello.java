package dss;

import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

class Hello {
	
	public static <T> Set<T> convertArrayToSet(T array[]) 
    { 
  
        // Create an empty Set 
        Set<T> set = new HashSet<>(); 
  
        // Iterate through the array 
        for (T t : array) { 
            // Add each element into the set 
            set.add(t); 
        } 
  
        // Return the converted Set 
        return set; 
    } 

	public void stopClass1() {
		String[] stopWrds = { "i", "a", "about", "an", "are", "as", "at", "be", "by", "com", "for", "from", "how", "in",
				"is", "it", "of", "on", "or", "that", "the", "this", "to", "was", "what", "when", "where", "who",
				"will", "with" };
		
		Set<String> stopWords = convertArrayToSet(stopWrds); 

		try {
			
			
			Scanner scanner = new Scanner(new File("input.txt"));
			FileOutputStream out = new FileOutputStream("output.txt");

			while (scanner.hasNext()) {
				String next_word = scanner.next();
				next_word = next_word.toLowerCase();
				if ( !stopWords.contains(next_word) ) {
					PrintStream p = new PrintStream(out);
					p.println(next_word);
				}

			}
			
			
			scanner.close();
			out.close();

		} catch (Exception e) {
			System.err.println("cannot read file");
		}
	}
	
	public static void main(String[] args) {
		Hello obj = new Hello();
		obj.stopClass1();

	}
}