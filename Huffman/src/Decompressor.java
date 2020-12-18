import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Decompressor {
	
	//VARIABLES
	public static HashMap<Character, String> codeMap = new HashMap<Character, String>();

	//CONSTRUCTOR
	public Decompressor() throws IOException{
		
		//rebuild code map
		BufferedReader fr = new BufferedReader (new FileReader("CodeMap"));
			
		for(String line = fr.readLine(); line != null; line = fr.readLine()) {	
			String nextLine = fr.readLine();
			codeMap.put(line.charAt(0), nextLine);
		}
			
		fr.close();
		
		System.out.println(codeMap);
		
		//read the compressed file
		BufferedBitReader br = new BufferedBitReader("Test_Compressed");
		BufferedWriter fw = new BufferedWriter(new FileWriter("Test_Decompressed"));
		
		String curr = "";
		
		while (br.hasNext()){ 

			boolean bit = br.readBit(); 
			
			if(bit) {
				curr = curr + "1";
			} else {
				curr = curr + "0";
			}
			
			if(codeMap.containsValue(curr)) {
				
				fw.write(codeMap.get(curr));
				curr = "";
			}
		}
		
		br.close();
		fw.close();
		
		
	}
	
	public static void main(String[] args) throws IOException {
		new Decompressor();
	}
	
}
