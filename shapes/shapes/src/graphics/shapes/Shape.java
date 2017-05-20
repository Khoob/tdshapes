package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.TreeMap;

import graphics.shapes.attributes.Attributes;




public abstract class Shape {
	private TreeMap<String,Attributes> attributes;
	
	public Shape(){
		this.attributes = new TreeMap<String,Attributes>();
	}
	
	public void addAttributes(Attributes attri){
		attributes.put(attri.getId(), attri);
	}
	public Attributes getAttributes(String attri){
		return attributes.get(attri);
	}
	public abstract Point getLoc();
	public abstract void setLoc(Point point);
	public abstract void translate(int x,int y);
	public abstract Rectangle getBounds();
	public abstract void accept(ShapeVisitor sv);
}
