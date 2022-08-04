//*******************************************************
//Collin Paddock
// Circle.java Lab 4
//
// 
//*******************************************************
public class Circle {
	private double radius;
	private double x ;
	private double y ;
	//----------------------------------------------
	// getX - returns the value of x
	//----------------------------------------------
	public double getCenter_x() {
		return x ;
	}
	//----------------------------------------------
	// getY - returns the value of y
	//----------------------------------------------
	public double getCenter_y() {
        return y ;
		// Your code goes here	
	}
	
	//----------------------------------------------
	// getRadius - returns the value of radius
	//----------------------------------------------
	public double getRadius() {
		return (radius);
	}
	//----------------------------------------------
	// setX - assigns a new value to x
	//----------------------------------------------
	public void setCenter_x(double xCoordinate) {//input parameter of x value of center
		x =xCoordinate;// set the Coordinate for X
	//----------------------------------------------	
	}
	//----------------------------------------------
	// setY - assigns a new value to y
	//----------------------------------------------
	public void setCenter_y(double yCoordinate) {
		y =yCoordinate;// set thie coordinate for Y
	//----------------------------------------------
	}
	//----------------------------------------------
	// setRadius - assigns a new value to radius
	//----------------------------------------------
	public void setRadius(double claudius){		
		radius=Math.abs(claudius);	
	}
	//--------------------------------------------------------
	// diameter - calculates the diameter of the circle
	//--------------------------------------------------------
	public double diameter() {// Your code goes here	
		return radius*2; //radius times two 
		
	}
	//--------------------------------------------------------
	// area - returns the area of the circle
	//--------------------------------------------------------
	public double area() {	
		double area=Math.PI*Math.pow(radius,2);
		return area;
		
	}
	//------------------------------------------------
	//perimeter - returns the perimeter of the circle
	//------------------------------------------------
	public double perimeter() {
		return 2*Math.PI*radius;
			
	}
	//--------------------------------------------------------
	// isUnitCircle - return true if the radius of this circle is 1
	//--------------------------------------------------------
	public Boolean isUnitCircle() { 
        if (0!= x || y !=0 || radius!=1){ 
			//----------------------------------------------
			// SO IF EITHER OF THE VALUES ARENT ZERO(and radius is not 1) ITS NOT A UNIT CIRCLE
			//----------------------------------------------
            return false;
        }
        else{
			return true;
			//----------------------------------------------
			//UNLESS IT IS A UNIT CIRCLE 
			//----------------------------------------------
        }
	}
	//--------------------------------------------------------
	// toString - return a String representation of whats up with a specific circle
	//--------------------------------------------------------
	public String toString() {
		  return("Center: ("+ x +","+ y +")"+"\n"+"radius :"+radius);// will print 
		  //----------------------------------------------
		  //concatenate statement about the Circle
		  //----------------------------------------------
    }
    public static Boolean equals(Circle aCertainCircle,Circle anotherCertainCircle){//challenged to do this in one line
		return (aCertainCircle.getRadius()==anotherCertainCircle.getRadius()&&aCertainCircle.getCenter_x()==anotherCertainCircle.getCenter_y()&&aCertainCircle.getCenter_y()==anotherCertainCircle.getCenter_y());
		//checks all the saved values in every saved circle
		//----------------------------------------------
    }
    public static boolean isConcentric(Circle aCertainCircle,Circle anotherCertainCircle){
        return (aCertainCircle.getRadius()!=anotherCertainCircle.getRadius()&&aCertainCircle.getCenter_x()==anotherCertainCircle.getCenter_y()&&aCertainCircle.getCenter_y()==anotherCertainCircle.getCenter_y());
		// SAME AS ABOVE except 
		// notice the != instead of ==, the radius must not be the same in order for it to return true
		//----------------------------------------------
	}
	public static double distance(Circle aCertainCircle,Circle anotherCertainCircle){
		return Math.sqrt(Math.pow(anotherCertainCircle.getCenter_y()-aCertainCircle.getCenter_y(),2)+Math.pow(anotherCertainCircle.getCenter_x()-aCertainCircle.getCenter_x(),2));
		//----------------------------------------------
		//  Square root( (y2-y1)^2 + (x2-x1)^2 ) implementing this with variables
		//----------------------------------------------
	}
	public static boolean intersects(Circle aCertainCircle,Circle anotherCertainCircle){
	double timeSaved=distance(aCertainCircle,anotherCertainCircle); //saving time by callinga function that already exists
	return(  (aCertainCircle.getRadius() + anotherCertainCircle.getRadius())    >timeSaved); //comparing
	//----------------------------------------------
	//  implementing this with
	//----------------------------------------------
	}
}
