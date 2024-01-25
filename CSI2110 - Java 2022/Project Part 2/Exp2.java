//Mikaela Dobie 300164161
import java.util.List;
import java.util.ArrayList;

import java.io.*;  
import java.util.Scanner;  

public class Exp2{
	// reads a csv file of 3D points (rethrow exceptions!)
    public static List<Point3D> read(String filename) throws Exception {
	  
		List<Point3D> points= new ArrayList<Point3D>(); 
		double x,y,z;
		
		Scanner sc = new Scanner(new File(filename));  
		// sets the delimiter pattern to be , or \n \r
		sc.useDelimiter(",|\n|\r");  

		// skipping the first line x y z
		sc.next(); sc.next(); sc.next();
		
		// read points
		while (sc.hasNext())  
		{  
			x= Double.parseDouble(sc.next());
			y= Double.parseDouble(sc.next());
			z= Double.parseDouble(sc.next());
			points.add(new Point3D(x,y,z));  
		}   
		
		sc.close();  //closes the scanner  
		
		return points;
    }

	public static void main(String[] args)throws Exception{
		double eps= Double.parseDouble(args[1]);
  
		// reads the csv file
		List<Point3D> points= Exp2.read(args[2]);
		
		List<Point3D> neighbors = new ArrayList<Point3D>();
		int skip = Integer.valueOf(args[3]);
		int cycle = points.size()/skip;
		Point3D point;
		long startTime = 0;
		long endTime = 0;
		long duration = 0;
		int next = skip;
		if (args[0].equals("lin"))	{
			// creates the NearestNeighbor instance
			for(int i=0; i<cycle; i++){
				point = points.get(skip);
				NearestNeighbors nn= new NearestNeighbors(points);
				startTime = System.nanoTime();
				nn.rangeQuery(point, eps);
				endTime = System.nanoTime();
				
				duration += (endTime - startTime);
				skip += next;
			}
		}else if (args[0].equals("kd")){
			//creates the NearestNeighboursKD instance
			for(int i=0; i<cycle; i++){
				point = points.get(skip);
				NearestNeighboursKD nk= new NearestNeighboursKD(points);
				startTime = System.nanoTime();
				nk.rangeQuery(point, eps);
				endTime = System.nanoTime();
				
				duration += (endTime - startTime);
				skip += next;
			}
		}else{
			System.exit(0);
		}
		System.out.println("Total elasped time "+duration + " nanoseconds");
		System.out.println("Total elasped time "+duration/1000000 + " milliseconds");

	}

}