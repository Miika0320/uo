// Mikaela Dobie 300164161

public class Plane3D{
	
	//points that exist on plane
	Point3D p1;
	Point3D p2;
	Point3D p3;
	
	// parametric values for plane ie. ax+by+cz+d=0
	double a;
	double b;
	double c;
	double d;
	
	//construct plane using points
	public Plane3D(Point3D p1, Point3D p2, Point3D p3){
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	
		construct();
	}
	
	private void construct(){
		Point3D line1 = new Point3D(p1.getX()-p2.getX(), p1.getY()-p2.getY(), p1.getZ()-p2.getZ());
		Point3D line2 = new Point3D(p3.getX()-p2.getX(), p3.getY()-p2.getY(), p3.getZ()-p2.getZ());
	
		double i = (line1.getY()*line2.getZ()) - (line1.getZ()*line2.getY());
		double j = -((line1.getX()*line2.getZ()) - (line1.getZ()*line2.getX()));
		double k = (line1.getX()*line2.getY()) - (line1.getY()*line2.getX());
		
		this.a = i;
		this.b = j;
		this.c = k;
		this.d = i*p1.getX()+j*p1.getY()+k*p1.getZ();
	}

	//construct plane using parameters
	public Plane3D(double a, double b, double c, double d){
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		
	}

	public double getDistance(Point3D pt){
	
		double dist = ((a*p1.getX()+b*p1.getY()+c*p1.getZ()+d)/ Math.sqrt(Math.pow(pt.getX(),2)+Math.pow(pt.getY(),2)+Math.pow(pt.getZ(),2)));
	
		return dist;
	}

}