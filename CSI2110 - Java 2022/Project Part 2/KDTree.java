//Mikaela Dobie 300164161
//creates an object KDTree which is a binary tree made up of KDnodes
//each level gets segmented by a different axis
public class KDTree{
	//nested class KDnode: node object of KDTree 
	//takes in Point3D, int
	public class KDnode{
		
		public Point3D pt;
		public int axis = 0;
		public double value;
		public KDnode left;
		public KDnode right;
		
		public KDnode(Point3D pt, int axis){
			this.pt = pt;
			this.axis = axis;
			this.value = pt.get(axis);
			left = right = null;
		}
	}
	private KDnode root;
	
	//contructs an empty tree
	public KDTree(){
		root = null;
	}
	
	public KDTree(KDnode root){
		this.root = root;
	}
	//calls insert method using root as a starting point
	public void add(Point3D P){
		//KDnode temp = null;
		root = this.insert(P, root, 0);
		//return temp;
	}
	public KDnode root(){
		return root;
	}
	//populates tree with nodes
	//requires point to be added, starting node ie. root, and axis it spilts on
	public KDnode insert(Point3D P, KDnode node, int axis) {
		if (node == null)
			node = new KDnode(P, axis);
		else if (P.get(axis) <= node.value)
			node.left = insert(P, node.left, (axis+1) % 3);
		else
			node.right = insert(P, node.right, (axis+1) % 3);
		return node;
	}
}