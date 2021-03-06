package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class SCollection extends Shape {
	
	public ArrayList<Shape> shapes;
	
	public SCollection(){
		super();
		this.shapes = new ArrayList<Shape>();
	}
	public Iterator<Shape> iterator(){
		return this.shapes.iterator();
	}
	public void add(Shape shape){
		this.shapes.add(shape);
	}

	@Override
	public Point getLoc() {
		return this.getBounds().getLocation();
	}

	@Override
	public void setLoc(Point point) {
		this.getBounds().setLocation(point);
	}

	@Override
	public void translate(int x, int y) {
		this.iterator().forEachRemaining(shape -> { shape.translate(x, y); });
	}

	@Override
	public Rectangle getBounds() {
		Iterator<Shape> iter = this.iterator();
		Rectangle rect =  iter.next().getBounds();
		while (iter.hasNext()){
			rect = rect.union(iter.next().getBounds());
		}
		return rect;
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitCollection(this);
	}

}
