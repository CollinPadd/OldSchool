//*******************************************************
// CircleTester.java
//
//
//  A client to test the functionality of objects
//  of the class Circle
// 
//*******************************************************
public class CircleTester{
	public static void main(String[] args) {
		Circle circle1 = new Circle();
		Circle circle2 = new Circle();
		circle1.setCenter_x(0.0);
		circle1.setCenter_y(0.0);
		circle1.setRadius(2);
		circle2.setCenter_x(2.0);
		circle2.setCenter_y(1.0);
		circle2.setRadius(1);
		System.out.println("Circle1 = "+circle1);
		System.out.println("Circle2 = "+circle2);
		// If the method setRadius is implemented correctly,
		// a call to setRadius with a negative number
		// will not change the value of the circle's radius.
		//
		circle1.setRadius(-2.0); 
		//
		// Reset the center of circle1 (-3.0,4.0)
		//
		circle1.setCenter_x(-3.0);
		circle1.setCenter_y(4.0);
		// print circle1 characteristics (center and radius), use a statement similar 
		// to the previous println statements. Note that is not necessary to call
        //the method toString, why? --Because we can directly get the data;
        System.out.println("Circle1 = "+circle1);
		// set the circle2 radius to 5.3
		circle2.setRadius(5.3);
		// print circle2 characteristics (center and radius), use a statement similar to the first and
		// second println statements
		System.out.println("Circle2 = "+circle2);
		// print circle1 diameter, area and perimeter
		System.out.println("Additional Info - Circle 1");
		System.out.println("Diameter = "+(circle1.diameter()));
		System.out.println("area = "+(circle1.area()));
		System.out.println("perimeter = "+(circle1.perimeter()));
		// print circle2 diameter, area and perimeter
		System.out.println("Additional Info - Circle 2");
		System.out.println("Diameter = "+(circle2.diameter()));
		System.out.println("area = "+(circle2.area()));
		System.out.println("perimeter = "+(circle2.perimeter()));
		// display whether circle1 is a unit circle
		////////////////////////////////////////////////////////////////
		////////INDENTED FOR EASE OF ACCESS///
		//////////////////////////////////////////////////
			if (circle1.isUnitCircle()){//Checks if Circle1 is a unit circle?
				System.out.println("Circle 1 is a Unit Circle" );//it is
			}
			else{
				System.out.println("Circle 1 is not a Unit Circle" );//it isnt
			}
			// display whether circle2 is a unit circle
			if (circle2.isUnitCircle()){//checks if circle2 is a unit circle
				System.out.println("Circle 2 is a Unit Circle" );//circle 2 is a unit circle
			}
			else{
				System.out.println("Circle 2 is not a Unit Circle" );//circle 2
			}
			// your additional tests should be placed below here
		System.out.println("//////NEXT TEST//UNIT CIRCLE AND BIGGER CIRCLE//////////////////" );
		System.out.println("Circle1 = "+circle1);
		System.out.println("Circle2 = "+circle2);
		circle1.setCenter_x(0.0);
		circle1.setCenter_y(0.0);
		circle1.setRadius(1);
		circle2.setCenter_x(0.0);
		circle2.setCenter_y(0.0);
		circle2.setRadius(2);
		if (Circle.equals(circle1,circle2)){// checks if two circle inputs of type circle are the same
			System.out.println("Circles do match" );//they do match
		}
		else{
			System.out.println("Circles dont match" );//they dont match
		}
		if (Circle.isConcentric(circle1,circle2)){//checks if the circles have the same origin but not radius
			System.out.println("Circle 1 is Actually concentric with Circle 2" );// it is
		}
		else{
			System.out.println("Circle 2 is not Concentric withCircle 1" );// you know
		}	
		if (Circle.intersects(circle1,circle2)){// checks intersection point on a graph
			System.out.println("Circle 1 intersects Circle 2" );// they intersect
		}
		else{
			System.out.println("Circle 2 is not intersecting with Circle 1" );// they intersect
		}
		if (circle1.isUnitCircle()){// another unit circle check
			System.out.println("Circle 1 is a Unit Circle" );// it is 
		}
		else{
			System.out.println("Circle 1 is not a Unit Circle" );// it isnt
		}
		if (circle2.isUnitCircle()){// another one wow haha
			System.out.println("Circle 2 is a Unit Circle" );//might be
		}
		else{
			System.out.println("Circle 2 is not a Unit Circle" );// i could see this happening
		}
		System.out.println("The Distance between the circles is "+Circle.distance(circle1, circle2) );System.out.println("////////NEXT TEST////SAME CIRCLE ON OPPOSITE SIDES OF Y AXIS///////" );
		circle1.setCenter_x(-10.0);
		circle1.setCenter_y(0.0);
		circle1.setRadius(2);
		circle2.setCenter_x(10.0);
		circle2.setCenter_y(0.0);
		circle2.setRadius(2);
		System.out.println("Circle1 = "+circle1);
		System.out.println("Circle2 = "+circle2);
		if (Circle.equals(circle1,circle2)){// checks if two circle inputs of type circle are the same
			System.out.println("Circles do match" );//they do match
		}
		else{
			System.out.println("Circles dont match" );//they dont match
		}
		if (Circle.isConcentric(circle1,circle2)){//checks if the circles have the same origin but not radius
			System.out.println("Circle 1 is Actually concentric with Circle 2" );// it is
		}
		else{
			System.out.println("Circle 2 is not Concentric withCircle 1" );// you know
		}	
		if (Circle.intersects(circle1,circle2)){// checks intersection point on a graph
			System.out.println("Circle 1 intersects Circle 2" );// they intersect
		}
		else{
			System.out.println("Circle 2 is not intersecting with Circle 1" );// they intersect
		}
		if (circle1.isUnitCircle()){// another unit circle check
			System.out.println("Circle 1 is a Unit Circle" );// it is 
		}
		else{
			System.out.println("Circle 1 is not a Unit Circle" );// it isnt
		}
		if (circle2.isUnitCircle()){// another one wow haha
			System.out.println("Circle 2 is a Unit Circle" );//might be
		}
		else{
			System.out.println("Circle 2 is not a Unit Circle" );// i could see this happening
		}
		System.out.println("The Distance between the circles is "+Circle.distance(circle1, circle2) );System.out.println("////////FINAL TEST////CRAZY NUMBERS///////" );
		circle1.setCenter_x(-16323.0);
		circle1.setCenter_y(124.0);
		circle1.setRadius(69);
		circle2.setCenter_x(1231.21);
		circle2.setCenter_y(513.0);
		circle2.setRadius(2.2);
		System.out.println("Circle1 = "+circle1);
		System.out.println("Circle2 = "+circle2);
		if (Circle.equals(circle1,circle2)){// checks if two circle inputs of type circle are the same
			System.out.println("Circles do match" );//they do match
		}
		else{
			System.out.println("Circles dont match" );//they dont match
		}
		if (Circle.isConcentric(circle1,circle2)){//checks if the circles have the same origin but not radius
			System.out.println("Circle 1 is Actually concentric with Circle 2" );// it is
		}
		else{
			System.out.println("Circle 2 is not Concentric withCircle 1" );// you know
		}	
		if (Circle.intersects(circle1,circle2)){// checks intersection point on a graph
			System.out.println("Circle 1 intersects Circle 2" );// they intersect
		}
		else{
			System.out.println("Circle 2 is not intersecting with Circle 1" );// they intersect
		}
		if (circle1.isUnitCircle()){// another unit circle check
			System.out.println("Circle 1 is a Unit Circle" );// it is 
		}
		else{
			System.out.println("Circle 1 is not a Unit Circle" );// it isnt
		}
		if (circle2.isUnitCircle()){// another one wow haha
			System.out.println("Circle 2 is a Unit Circle" );//might be
		}
		else{
			System.out.println("Circle 2 is not a Unit Circle" );// i could see this happening
		}
		/////////////////////////CHECKS ENTIRE PROGRAM 3 TIMES
	}
	
}
