//Mikaela Dobie 300164161


import java.io.*;
import java.util.*;

public class PointCloud{

	ArrayList<Point3D> pointCloud;
	ArrayList<Point3D> pointCloudP;
	Iterator<Point3D> it;
	public PointCloud(String filename) throws FileNotFoundException {
		try{
		pointCloud = read(filename);
		it = this.iterator();
		pointCloudP = new ArrayList<Point3D>();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		try{
			saveAll("PointCloud0.xyz");
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	public PointCloud(){
		pointCloud = new ArrayList<Point3D>();
		pointCloudP = new ArrayList<Point3D>();
		it = this.iterator();
	}
	
	public PointCloud(ArrayList<Point3D> use){
		pointCloud = new ArrayList<Point3D>();
		pointCloudP = use;
		it = this.iterator();
	}
	
	public ArrayList<Point3D> getPoints(){
		return pointCloudP;
	}
	
	public void addPoint(Point3D pt){
		
		pointCloudP.add(pt);
	}
	
	public Point3D getPoint(){
		Collections.shuffle(pointCloud);
		Point3D next;
		if (it.hasNext()){
			next = it.next();
			//it.remove();
			//pointCloud.remove(next);
			return next;
		}
		return null;
	}
	
	public void save(String filename) throws FileNotFoundException, UnsupportedEncodingException {
		
		try{
			PrintWriter writer = new PrintWriter(filename, "UTF-8");
			
			writer.println("x,y,z");

			for(Point3D p: pointCloudP){
				String xyz = p.getX()+","+p.getY()+","+p.getZ();
				writer.println(xyz);
			}
			writer.close();
			pointCloudP.clear();
		} catch( Exception e){
			e.printStackTrace();
		}it = this.iterator();
		
	}
	
	public void reset(){
		pointCloudP.clear();
		it = this.iterator();
	}
	
	public static ArrayList<Point3D> read (String filename)throws FileNotFoundException{
		ArrayList<Point3D> points =  new ArrayList<Point3D>();
		try{
			File cloud = new File(filename);
			Scanner reader = new Scanner(cloud);
			
			reader.nextLine();
			
			while (reader.hasNextLine()) {
				//get next line and split into x,y,z
				String[] tokens = reader.nextLine().trim().split("	");
				//make into point

				Point3D p = new Point3D(
				Double.parseDouble(tokens[0].trim()), 
				Double.parseDouble(tokens[1].trim()), 
				Double.parseDouble(tokens[2].trim())
				);
				//add point to list
				points.add(p);
			}
			reader.close();
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
        return points;
    }
	
	
	
	Iterator<Point3D> iterator(){
		Iterator it = pointCloud.iterator();
		return it;
	
	}
	
	private void saveAll(String filename)throws FileNotFoundException, UnsupportedEncodingException {
		
		try{
			PrintWriter writer = new PrintWriter(filename, "UTF-8");
			
			writer.println("x,y,z");
			ArrayList<Point3D> pointCloudC = new ArrayList<Point3D>(pointCloud);
			//pointCloudC = pointCloud;
			for(Point3D p: pointCloudC){
				String xyz = p.getX()+","+p.getY()+","+p.getZ();
				writer.println(xyz);
			}
			writer.close();
		} catch( Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		PointCloud points = new PointCloud(args[0]);
		PlaneRANSAC ran = new PlaneRANSAC(points);
		//if (args[1]!= null)
			//ran.setEps(Double.parseDouble(args[1]));
		//Plane3D plane = new Plane3D(this.getPoint(), this.getPoint(), this.getPoint);
		int num = ran.getNumberOfIterations(.99,.45);
		try{
			ran.run(num, args[0]);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}