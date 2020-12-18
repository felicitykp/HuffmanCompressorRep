
public class Branch<E> {

	//VARIABLES
	public E info;
	public Branch<E> left, right;
	public Boolean isLeaf;
	
	//CONSTRUCTOR
	public Branch(E i) {
		info = i;
		left = right = null;
		isLeaf = true;
	}
	
	public Branch(Branch<E> l, Branch<E> r) {
		left = l;
		right = r;
		info = null;
		isLeaf = false;
	}
	
}
