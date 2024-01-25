//Mikaela Dobie 300164161
import java.io.*;
import java.util.*;
public class PlaneRANSAC{
	
	double eps = 1.2;
	PointCloud p;
	
	public PlaneRANSAC(PointCloud pc){
		p = pc;
	}
	
	public void setEps(double eps){
		this.eps = eps;
	}

	public double getEps(){
		return eps;
	}

	public int getNumberOfIterations(double confidence, double percentageOfPointsOnPlane){
		double num = (Math.log(1-confidence)/Math.log(1-Math.pow(percentageOfPointsOnPlane,3)));
		
		return (int) num;
	}
	
	public void run(int numberOfIterations, String filename) throws FileNotFoundException, UnsupportedEncodingException {
		filename = filename.substring(0,filename.indexOf("."));
		Plane3D bestPlane;
		ArrayList<Point3D> most = new ArrayList<Point3D>();
		int support = 0;
		int count = 0;
		System.out.println(numberOfIterations);
		for(int i=1; i<4; i++){
			for (int j=0; j< numberOfIterations; j++){
				Plane3D plane = new Plane3D(p.getPoint(), p.getPoint(), p.getPoint());
				Point3D point = p.getPoint();
				while (point != null){
					if(plane.getDistance(point)<eps){
						p.addPoint(point);
						count++;
					}
					point = p.getPoint();
					
				}
				if (count>support){
					bestPlane = plane;
					most = p.getPoints();
					support = count;
				}
				count = 0;
				p.reset();
			}
			PointCloud use = new PointCloud(most);
			try{
				use.save(filename+"_"+Integer.toString(i)+".xyz");
				//System.out.println("here");
			}catch(Exception e){
				e.printStackTrace();
			}
			bestPlane = null;
			most = null;
			support = 0;
		}
		
		
	}



}