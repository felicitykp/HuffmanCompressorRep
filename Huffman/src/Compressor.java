import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Compressor {
	
	//VARAIBLES
	public static HashMap<Character, Integer> frequencyMap = new HashMap<Character, Integer>();

	//CONSTRUCTOR
	public Compressor() throws IOException {
		
		//create file reader
		FileReader fr = new FileReader("Test");
		
		//go through the file and assign frequency's
		for (int current = fr.read(); current != -1; current = fr.read()) {
			
			//change to character
			char curr = (char)current;
			
			//increases the frequency of the character
			if (frequencyMap.get(curr) == null) {
				frequencyMap.put(curr, 1);
			} else {
				frequencyMap.put(curr, frequencyMap.get(curr) + 1);
			}
		}
		
		//close reader
		fr.close();
		
		System.out.println(frequencyMap);
		
	}
	
	public static void main(String[] args) throws IOException {
		new Compressor();
	}
	
	
}
