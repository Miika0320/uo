//Mikaela Dobie 300164161

import java.util.List;
import java.util.ArrayList;


public class NearestNeighboursKD{
	public KDTree kdTree;
	public List<Point3D> neighbors;
	
	//NearestNeighboursKD class that uses RangeQuery and KDTree to find nearest neighbours
	//takes in a list of Point3D objects
	public NearestNeighboursKD(java.util.List<Point3D> list){
		kdTree = new KDTree();
		neighbors =  new ArrayList();
		for (Point3D p : list) {
			kdTree.add(p);
		}
	}

	//public RangeQuery method, takes in Point3D object and double value
	// uses private method to find nearest neighbours starting from root
	// returns list of neighbours
	public List<Point3D> rangeQuery(Point3D p, double eps) {
		rangeQuery(p, eps, neighbors, kdTree.root());
		return neighbors;
	}
	//private RangeQuery method, takes in Point3D object, double value, Point3D list, and a KDTree.KDnode
	//populates list N with neighbours within eps value
	private void rangeQuery(Point3D P, double eps, java.util.List<Point3D> N, KDTree.KDnode node) {
		if (node == null)
			return;
		if (P.distance(node.pt) < eps)
			N.add(node.pt);
		if (P.get(node.axis) - eps <= node.value)
			rangeQuery(P, eps, N, node.left);
		if (P.get(node.axis) + eps > node.value)
			rangeQuery(P, eps, N, node.right);
		return ;
	}
	

}