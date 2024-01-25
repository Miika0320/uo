// Mikaela Dobie 300164161

import java.util.concurrent.ThreadLocalRandom;



public class Point3D{
	private double x;
	private double y;
	private double z;
	private int label = null;
	
	public Point3D(){
		
	}
	
	public Point3D(double xx, double yy, double zz, int label2){
		x=xx;
		y=yy;
		z=zz;
		label = label2;
	}
	
	public int getLabel(){
		return label;
	}
	
	public void setLabel(int p){
		label = p;
	}
	public void setX(double a){
		x = a;
	}
	
	public void setY(double a){
		y = a;
	}
	
	public void setZ(double a){
		z = a;
	}

	public double distance(Point3D pt){
		double distance = 0;
		return distance;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getZ(){
		return z;
	}

}