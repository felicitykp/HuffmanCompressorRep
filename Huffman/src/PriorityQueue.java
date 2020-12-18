import java.util.ArrayList;

public class PriorityQueue<E> {

	//VARIABLES
	public ArrayList<Node<E>> queue = new ArrayList<Node<E>>();
	
	
	//METHODS
	public Node<E> pop(){
		return queue.remove(queue.size()-1);
	}
	
	public String toString() {
		return queue.toString();
	}
	
	public void add(Node<E> newNode) {
		
		//if list is empty
		if(queue.size() == 0) {
			queue.add(newNode);
		}
		
		//if it has the highest priority so far
		else if (queue.get(0).priority < newNode.priority) {
			queue.add(0, newNode);
		}
		
		//if it has the smallest priority so far
		else if (queue.get(queue.size()-1).priority > newNode.priority) {
			queue.add(newNode);
		}
		
		//remaining binary search for correct location
		else {
			int start = 0;
			int end = queue.size()-1;
			
			while(start < end) {
				
				//find midpoint
				Node<E> midpoint = queue.get((start+end)/2);
				
				//reset start and ends based on midpoint priority
				if(midpoint.priority > newNode.priority) {
					start = (start+end)/2 + 1;
				} else {
					end = (start+end)/2;
				}
			}
			
			//add it to that point
			queue.add(start, newNode);
		}
	}
	
	public int size() {
		return queue.size();
	}
	
	//MAIN
	public static void main(String[] args) {
		PriorityQueue<Character> myQ = new PriorityQueue<Character>();
		
		for(int i = 0; i < 100; i++) {
			myQ.add(new Node((int)(Math.random()*100), "hi"));
		}
		
		System.out.println(myQ);
		System.out.println(myQ.size());
	}
	
	
}
