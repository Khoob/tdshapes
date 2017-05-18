package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Graphics;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;

public class ShapeDraftman implements ShapeVisitor {
	
	public final ColorAttributes DEFAULTCOLORATTRIBUTES = new ColorAttributes(true, true, Color.BLACK, Color.BLACK);;
	private Graphics g;
	
	public ShapeDraftman(Graphics g){
		this.g = g;
	}

	@Override
	public void visitRectangle(SRectangle srect) {
		ColorAttributes color = (ColorAttributes) srect.getAttributes("Color");
		if (color.stroked){
			g.setColor(color.strokedColor);
			g.drawRect(srect.getLoc().x,srect.getLoc().y, srect.getRect().width, srect.getRect().height);
		}
		if (color.filled){	
			g.setColor(color.filledColor);
			g.fillRect(srect.getLoc().x,srect.getLoc().y, srect.getRect().width, srect.getRect().height);
		}
	}

	@Override
	public void visitCircle(SCircle scir) {
		ColorAttributes color = (ColorAttributes) scir.getAttributes("Color");
		if (color.stroked){
			g.setColor(color.strokedColor);
			g.drawOval(scir.getLoc().x, scir.getLoc().y, scir.getRadius(), scir.getRadius());
		}
		if (color.filled){
			g.setColor(color.filledColor);
			g.fillOval(scir.getLoc().x, scir.getLoc().y, scir.getRadius(), scir.getRadius());
		}
	}

	@Override
	public void visitText(SText stext) {
		FontAttributes font = (FontAttributes) stext.getAttributes("Font");
		ColorAttributes color = (ColorAttributes) stext.getAttributes("Color");
		g.setFont(font.font);
		if (color.filled){
			g.setColor(color.filledColor);
			g.fillRect(stext.getLoc().x, stext.getLoc().y - 10,stext.getBounds().width,stext.getBounds().height);
		}
		if (color.stroked){
			g.setColor(color.strokedColor);
			g.drawString(stext.getText(),stext.getLoc().x, stext.getLoc().y);
		}
	}

	@Override
	public void visitCollection(SCollection scol) {
		scol.iterator().forEachRemaining(shape -> { shape.accept(this); });
	}
	
	
}
