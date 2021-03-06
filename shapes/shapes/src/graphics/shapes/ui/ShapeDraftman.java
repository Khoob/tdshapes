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
import graphics.shapes.attributes.SelectionAttributes;

public class ShapeDraftman implements ShapeVisitor {
	
	public final ColorAttributes DEFAULTCOLORATTRIBUTE = new ColorAttributes(true, true, Color.BLACK, Color.BLACK);
	public final SelectionAttributes DEFAULTSELECTIONATTRIBUTE = new SelectionAttributes();
	public final FontAttributes DEFAULTFONTATTRIBUTE = new FontAttributes();
	private Graphics g;
	
	public ShapeDraftman(Graphics g){
		this.g = g;
	}

	@Override
	public void visitRectangle(SRectangle srect) {
		SelectionAttributes select = (SelectionAttributes) srect.getAttributes("Select");
		ColorAttributes color = (ColorAttributes) srect.getAttributes("Color");
		if (select == null){
			select = this.DEFAULTSELECTIONATTRIBUTE;
		}
		if (color == null){
			color = this.DEFAULTCOLORATTRIBUTE;
		}
		if (color.filled){	
			g.setColor(color.filledColor);
			g.fillRect(srect.getLoc().x,srect.getLoc().y, srect.getRect().width, srect.getRect().height);
		}
		if (color.stroked){
			g.setColor(color.strokedColor);
			g.drawRect(srect.getLoc().x,srect.getLoc().y, srect.getRect().width, srect.getRect().height);
		}
		if (select.isSelected()){
			g.setColor(Color.black);
			g.drawRect(srect.getLoc().x - 2,srect.getLoc().y - 2, 4, 4);
			g.drawRect(srect.getLoc().x + srect.getRect().width - 3,srect.getLoc().y + srect.getRect().height - 3, 4, 4);
		}
	}

	@Override
	public void visitCircle(SCircle scir) {
		SelectionAttributes select = (SelectionAttributes) scir.getAttributes("Select");
		ColorAttributes color = (ColorAttributes) scir.getAttributes("Color");
		if (select == null){
			select = this.DEFAULTSELECTIONATTRIBUTE;
		}
		if (color == null){
			color = this.DEFAULTCOLORATTRIBUTE;
		}
		if (color.filled){
			g.setColor(color.filledColor);
			g.fillOval(scir.getLoc().x, scir.getLoc().y, scir.getRadius(), scir.getRadius());
		}
		if (color.stroked){
			g.setColor(color.strokedColor);
			g.drawOval(scir.getLoc().x, scir.getLoc().y, scir.getRadius(), scir.getRadius());
		}
		if (select.isSelected()){
			g.setColor(Color.black);
			g.drawRect(scir.getLoc().x - 2,scir.getLoc().y - 2, 4, 4);
			g.drawRect(scir.getLoc().x + scir.getRadius() - 3,scir.getLoc().y + scir.getRadius() - 3, 4, 4);
		}
	}

	@Override
	public void visitText(SText stext) {
		SelectionAttributes select = (SelectionAttributes) stext.getAttributes("Select");
		FontAttributes font = (FontAttributes) stext.getAttributes("Font");
		ColorAttributes color = (ColorAttributes) stext.getAttributes("Color");
		if (select == null){
			select = this.DEFAULTSELECTIONATTRIBUTE;
		}
		if (font == null){
			font = this.DEFAULTFONTATTRIBUTE;
		}
		if (color == null){
			color = this.DEFAULTCOLORATTRIBUTE;
		}
		if (color.filled){
			g.setColor(color.filledColor);
			g.fillRect(stext.getBounds().x, stext.getBounds().y, stext.getBounds().width, stext.getBounds().height);
		}
		if (color.stroked){
			g.setColor(color.strokedColor);
			g.drawRect(stext.getBounds().x, stext.getBounds().y, stext.getBounds().width, stext.getBounds().height);
		}
		g.setFont(font.font);
		g.setColor(font.fontColor);
		g.drawString(stext.getText(), stext.getLoc().x, stext.getLoc().y);
		if (select.isSelected()){
			g.setColor(Color.black);
			g.drawRect(stext.getBounds().x - 2,stext.getBounds().y - 2, 4, 4);
			g.drawRect(stext.getBounds().x + stext.getBounds().width - 3,stext.getBounds().y + stext.getBounds().height - 3, 4, 4);
		}
		//g.drawRect(stext.getBounds().x, stext.getBounds().y, stext.getBounds().width, stext.getBounds().height);
	}

	@Override
	public void visitCollection(SCollection scol) {
		SelectionAttributes select = (SelectionAttributes) scol.getAttributes("Select");
		scol.iterator().forEachRemaining(shape -> { shape.accept(this); });
		if (select.isSelected()){
			g.setColor(Color.black);
			g.drawRect(scol.getBounds().x - 2,scol.getBounds().y - 2, 4, 4);
			g.drawRect(scol.getBounds().x + scol.getBounds().width - 3,scol.getBounds().y + scol.getBounds().height - 3, 4, 4);
		}
	}
	
	
}
