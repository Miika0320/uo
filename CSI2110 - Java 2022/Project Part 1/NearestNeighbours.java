//Mikaela Dobie 300164161

public class NearestNeighbours{
	private List<Point3D> full;
	NearestNeighbors(List<Point3D> fill){
		full = fill;
	}
	
	public List<Point3D> rangeQuery(double eps, Point3D P){
		List<Point3D> N = new List<Point3D>();
		Point3D curr;
		for (i = 0; i<full.size(); i++) { /* Scan all points in DB */
			curr = full[i];
			if distance(curr, P) ≤ eps then { /* Compute distance */
				N.add(P) /* Add to result */
			}
		}
		return N
	}
	

}