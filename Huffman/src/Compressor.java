import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class Compressor {
	
	//VARAIBLES
	public static HashMap<Character, Integer> freqMap = new HashMap<Character, Integer>();
	public static HashMap<Character, String> binaryMap = new HashMap<Character, String>();

	//CONSTRUCTOR
	public Compressor() throws IOException {
		
		PriorityQueue<Branch<Character>> myQ = new PriorityQueue<Branch<Character>>();
		
		//build frequency map
		buildMap();
		System.out.println(freqMap);
		
		//build priority queue
		Set<Character> keys = freqMap.keySet();
		for(char curr : keys) {
			myQ.add(new Node(freqMap.get(curr), new Branch(curr)));
			
		}
		System.out.println(myQ);
		
		//build tree	
		while(myQ.size() > 1) {
			Node<Branch<Character>> right = myQ.pop();
			Node<Branch<Character>> left = myQ.pop();
			Branch<Character> branch = new Branch<Character>(right.info, left.info);
			myQ.add(new Node( right.priority + left.priority, branch));
		}
		System.out.println(myQ);
		
		//assign binary values
		recursion(myQ.pop().info, "");
		System.out.println(binaryMap);
		
		
		//compress file
		compress();
		
		buildCodeMap();
		
		
		
	}
	
	//METHODS
	public void buildMap() throws IOException { 
	
		FileReader fr = new FileReader("Test");
				
		//go through the file and assign frequency's
		for (int current = fr.read(); current != -1; current = fr.read()) {
					
			//change to character
			char curr = (char)current;
					
			//increases the frequency of the character
			if (freqMap.get(curr) == null) {
				freqMap.put(curr, 1);
			} else {
				freqMap.put(curr, freqMap.get(curr) + 1);
				}
			}
				
		//close reader
		fr.close();
	}
	
	public void recursion(Branch<Character> branch, String value) {
	
		//check if leaf
		if(branch.isLeaf == true) {
			binaryMap.put(branch.info, value);
		} else {
			recursion(branch.left, value + "0");
			recursion(branch.right, value + "1");
		}
		
	}
	
	public void compress() throws IOException {
		FileReader fr = new FileReader("Test");
		BufferedBitWriter bbw = new BufferedBitWriter("Test_Compressed");
		
		for (int current = fr.read(); current != -1; current = fr.read()) {
			
			//change to character
			char curr = (char)current;
			
			String temp = binaryMap.get(curr);
			
			for(int i = 0; i < temp.length(); i++) { //0 is false
				if (temp.charAt(i) == '0') {
					bbw.writeBit(false);
				} else {
					bbw.writeBit(true);
				}
			}
		}
		
		fr.close();
		bbw.close();
	}
	
	public void buildCodeMap() throws IOException {
		FileWriter fw = new FileWriter("CodeMap");
		
		Set<Character> keys = binaryMap.keySet();
		for(char curr : keys) {
			fw.write(curr + "\n");
			fw.write(binaryMap.get(curr) + "\n");
		}
		fw.close();
	}
	
	
	public static void main(String[] args) throws IOException {
		new Compressor();
	}
	
	
}
