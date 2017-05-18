package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SCircle extends Shape {
	private int radius;
	private Point loc;
	
	public SCircle(Point loc ,int radius){
		this.loc = loc;
		this.radius = radius;
	}
	
	public int getRadius(){
		return radius;
	}
	public void setRadius(int radius){
		this.radius = radius;
	}
	
	@Override
	public Point getLoc() {
		return this.loc;
	}
	@Override
	public void setLoc(Point point) {
		this.loc = point;
	}
	@Override
	public void translate(int x, int y) {
		this.loc.translate(x, y);
		
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle(loc.x,loc.y,radius,radius);
	}
	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitCircle(this);
	}
	
}
