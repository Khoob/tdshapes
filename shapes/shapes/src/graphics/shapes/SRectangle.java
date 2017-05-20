package graphics.shapes;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class SRectangle extends Shape {
	
	private Rectangle rect;
	
	public SRectangle(Point point,int width,int height){
		super();
		this.rect = new Rectangle(point,new Dimension(width,height));
	}
	
	public Rectangle getRect(){
		return rect.getBounds();
	}
	
	@Override
	public Point getLoc() {
		return rect.getLocation();
	}

	@Override
	public void setLoc(Point point) {
		rect.setLocation(point);
	}

	@Override
	public void translate(int x, int y) {
		rect.translate(x, y);
	}

	@Override
	public Rectangle getBounds() {
		return rect.getBounds();
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitRectangle(this);
	}

}
