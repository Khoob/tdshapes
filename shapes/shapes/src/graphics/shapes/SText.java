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
		Rectangle rect = font.getBounds(this.text);
		rect.translate((int) this.loc.getX() - 1, (int) this.loc.getY() - rect.height + 2);
		return rect.getBounds();
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitText(this);
		
	}

}
