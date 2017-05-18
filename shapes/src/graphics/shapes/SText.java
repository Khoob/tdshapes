package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.FontAttributes;

public class SText extends Shape {
	
	private String text;
	private Point loc;
	
	public SText(Point loc, String text){
		this.loc = loc;
		this.text = text;
	}
	
	public String getText(){
		return this.text;
	}
	public void setText(String text){
		this.text = text;
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
		FontAttributes font = (FontAttributes) this.getAttributes("Font");
		return new Rectangle(this.loc.x,this.loc.y,30,10);
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitText(this);
		
	}

}
