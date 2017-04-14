//Manu Kondapaneni
//This class is to compute the arcPanel
//import java.awt.*;

//import java.awt.event.*;
//import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;






public class ArcPanel 
// extends JPanel
{
	private Timer timer;
	private int [] x;
	private int [] y;
	private int [] angles;
	private int points;
	private int incAngle;
	private Point center = new Point(500,700);
	private int origAngle;
	private int hypotenuse;
	private int index = 0;
	private double cosin;
	private double sin;
	private double hoopPoint;
	private int[] hoopX;
	private int hoopY;
	private int compute;
	private int hoopIndex = 0;
	
	
	public static void main(String [] args)
	{
		ArcPanel ap = new ArcPanel();
		ap.findArc();
		ap.printPoints();	
	}

	
	public ArcPanel()
	{
		hoopIndex = 0;
		compute = 650;
		hoopPoint = 0;
		hoopY = 50;
		index = 0;
		cosin = 0;
		sin = 0;
		hypotenuse = 200;
		points = 36;
		incAngle = 5;
		origAngle = 0;
		
		x = new int [points];
		y = new int [points];
		angles = new int[points];
		hoopX = new int [points];
		
		for(int z = 0; z<x.length;z++)
		{
			hoopX [z] = 0;
			angles[z] = 0;
			x[z] = 0;
			y[z] = 0;
		}
	}
	
	public void computeHoopIndex()
	{
		int rval = (int)(Math.random()*5+1);
		
		if(rval ==1)
			hoopIndex = 25; 
		if (rval == 2)
			hoopIndex = 22; 
		if (rval == 3)
			hoopIndex = 19; // 18;
		if (rval == 4)
			hoopIndex = 15; 
		if (rval == 5)
			hoopIndex = 12;	
		
	}
	
	
	public void decrementIndexAndSet(int i)
	 {
		//System.out.println("decre");

		index -= i;
		
	}

	public void incrementIndexAndSet(int i)
	 {
		//System.out.println("incre");
		index += i;
	}

	public void findArc()//will find the coordinates for arc
	{
		//This is to find the arc
		for(int i =0; i < x.length; i++)
		{	
			cosin = Math.cos(Math.toRadians(origAngle));//I used Cosin to find the x value of the arcs hypotenuse by splitting it up into 36 different triangles
			cosin = cosin*hypotenuse;//using triangles and sine and cosine to find missing lengths
			
			x[i] = (int)(450-cosin);//500 is horizontal length
			
			hoopPoint = Math.tan(Math.toRadians(origAngle));//This is for the x value where the hoop should be
			hoopPoint = compute/hoopPoint;//compute = 650 the y length of my screen minus 100 
			hoopX[i] = (int)(500+hoopPoint);//Storing it in array
			
			
			 
			sin = Math.sin(Math.toRadians(origAngle));//This is to find the y value of the arc, same way as the X
			sin = sin*hypotenuse;
			y[i] = (int)(650-sin);
			angles[i] = origAngle;//So I will know which angle each index has
			
			//700 is vertical length
			origAngle += incAngle;//inc angle is 5 so I will have 36 different points around a 180 degree arc.
		}

	}

	public void printPoints() 
	{
		for (int i = 0; i < 20; i++) {
		   computeHoopIndex();
		   System.out.println("hoopindex"+hoopIndex);
		}
		
		for (int i=0; i < x.length; i++) 
		{
			//System.out.println("x:" + x[i] + " y:" + y[i]);
			System.out.println("hoopX "+ i + " " + hoopX[i]);
			
		}
	}
	
	public int[] getPointsX() 
	{
		return x;
	}
	
	public int[] getPointsY() 
	{
		return y;
	}

	public Timer getTimer()
	 {
		return timer;
	}

	public void setTimer(Timer timer) 
	{
		this.timer = timer;
	}

	public Point getCenter() 
	{
		return center;
	}

	public void setCenter(Point center)
	 {
		this.center = center;
	}

	public int getIndex() 
	{
		return index;
	}

	public void setIndex(int index)
	 {
		this.index = index;
	}

	public int[] getAngles() 
	{
		return angles;
	}

	public void setAngles(int[] angles) 
	{
		this.angles = angles;
	}

	public int getHypotenuse() 
	{
		return hypotenuse;
	}

	public void setHypotenuse(int hypotenuse) 
	{
		this.hypotenuse = hypotenuse;
	}
	
	public double getHoopPoint() {
		return hoopPoint;
	}

	public void setHoopPoint(double hoopPoint) {
		this.hoopPoint = hoopPoint;
	}

	public int[] getHoopX() {
		return hoopX;
	}

	public void setHoopX(int[] hoopX) {
		this.hoopX = hoopX;
	}

	public int getHoopY() {
		return hoopY;
	}

	public void setHoopY(int hoopY) {
		this.hoopY = hoopY;
	}

	public int getHoopIndex() {
		return hoopIndex;
	}

	public void setHoopIndex(int hoopIndex) {
		this.hoopIndex = hoopIndex;
	}

//	public void paintComponent(Graphics g)
//	{
//		super.paintComponent(g);
//		
//		g.drawOval(x[index],y[index],100,100);
//	}
	

}	


