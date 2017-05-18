package graphics.shapes.attributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;



public class FontAttributes extends Attributes {

	public Font font;
	public Color fontColor;
	
	public Rectangle getBounds(String text){
		return new Rectangle();
	}
	
	@Override
	public String getId() {
		return "Font";
	}
	
	

}
