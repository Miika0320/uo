// Mikaela Dobie 300164161

public class DBScan{
	private double eps;
	private double minPts;
	public DBScan(List<Point3D>){
		
	}
	
	public DBScan(List<Point3D> DB, double eps1, double minPts1) {
		eps = eps1;
		minPts = minPts1;
		
	}


	public double setEps(double epsNew){
		eps = epsNew;
		return eps;
	}
	public double setMinPts(double minPts){
		this.minPts = minPts;
		return this.minPts;
	}
	
	public void findClusters(){
		Stack S = new Stack();
		Point3D P;
		int C = 0 /* Cluster counter */
		List<Point3D> list = this.getPoints();
		NearestNeighbors neigh = new NearestNeighbors(list);
		List<Point3D> neighbours;
		for (int i=0; i<list.size(); i++) { // for each point P in Sequence DB
			P = list[i];
			if (p.getLabel() != null) /* Already processed */
				neighbours = neigh.rangeQuery(eps,P) ;/* Find neighbors */
				if (neighbours.size() < minPts) then { /* Density check */
					P.setLabel(-1); /* Label as Noise = -1 */
				}
				C += 1 /* next cluster label */
				P.setLabel(C); /* Label initial point */
				for (int j=0; j<neighbours.size(); j++) {
					S.push(neighbours[j]); /* Neighbors to expand */
				while (!S.isEmpty()) {
					curr = S.pop(); /* Process point Q */
					if (curr.getLabel() == null)
						curr.setLabel(C); /* Noise becomes border pt */
					if (curr.getLabel() != null) 
						continue;/* Previously processed */
					curr.setLabel(C); /* Label neighbor */
					neighbours = neigh.rangeQuery(eps,curr) /* Find neighbors */
					if (n.size() ≥ minPts){ /* Density check */
						for (int k=0; k<neighbours.size(); k++)
							S.push(neighbours[k]) /* Add neighbors to stack */
					}
				}
		}
		
	}
	
	public int getNumberOfClusters(){
	
		return 0;
	
	}
	
	public List<Point3D> getPoints(){
		
		return null;
	}
	
	public static List<Point3D> read(String filename){
		return null;
	}
	
	public void save(String filename){
		
	}
}