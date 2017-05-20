package graphics.shapes.ui;


import java.awt.Graphics;

import graphics.shapes.SCollection;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesView extends View{
	
	private SCollection sc;

	public ShapesView(SCollection sc) {
		super(sc);
		this.sc = sc;
        this.setFocusable(true);
	}
	
	@Override
	public Controller defaultController(Object model){
		return new ShapesController((SCollection) model);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    ShapeDraftman sd = new ShapeDraftman(g);
	    sc.iterator().forEachRemaining(shape -> { shape.accept(sd); });
	    
	}
}
