package graphics.shapes.attributes;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;



public class FontAttributes extends Attributes {
	
	public Font font = new Font("Helvetica",Font.PLAIN,12);
	public Color fontColor = Color.black;
	
	public Rectangle getBounds(String text){
		Canvas c = new Canvas();
		FontMetrics metrics = c.getFontMetrics(font);
		int hgt = metrics.getHeight();
		int adv = metrics.stringWidth(text);
		return new Rectangle(adv +2, hgt +1);
	}
	
	@Override
	public String getId() {
		return "Font";
	}
	
	

}
