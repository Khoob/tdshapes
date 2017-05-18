package graphics.shapes.ui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

public class ShapesController extends Controller {

	public ShapesController(Object newModel) {
		super(newModel);
	}
	
	public void mouseClicked(MouseEvent e){
		Shape shape = this.getTarget(e);
		if (shape != null) {
			if (shape.countainsAttributes(SelectionAttributes.id)) {
				SelectionAttributes sAttri = (SelectionAttributes) shape.getAttributes(SelectionAttributes.id);
				sAttri.select();
			}
		}
		getView().updateUI();
	}
	
	public void mousePressed(MouseEvent e){
		/*Shape shape = this.getTarget(e);
		if (shape.countainsAttributes(SelectionAttributes.id)) {
			SelectionAttributes sAttri = (SelectionAttributes) shape.getAttributes(SelectionAttributes.id);
			sAttri.select();
		}*/
	}
	
	public void mouseReleased(MouseEvent e){
		getView().updateUI();
	}
	public void mouseDragged(MouseEvent e){
		this.translateSelected(e.getX(), e.getY());
		getView().updateUI();
	}
	
	public void translateSelected(int x,int y){
		SCollection newModel = (SCollection) super.getModel();
		Shape shape;
		Iterator<Shape> iter=newModel.iterator();
		while(iter.hasNext()) {
			shape=iter.next();
			if (shape.countainsAttributes(SelectionAttributes.id)) {
				SelectionAttributes sAttri = (SelectionAttributes) shape.getAttributes(SelectionAttributes.id);
				if (sAttri.isSelected()) {
					shape.translate(x, y);
				}
			}
		}
	}
	public Shape getTarget(MouseEvent e){
		SCollection newModel = (SCollection) super.getModel();
		Shape shape;
		Iterator<Shape> iter=newModel.iterator();
		while(iter.hasNext()) {
			shape=iter.next();
			if ((e.getX() >= shape.getBounds().x) && (e.getX() <= shape.getBounds().x + shape.getBounds().height) && (e.getY() >= shape.getBounds().y) && (e.getY() <= shape.getBounds().y +  shape.getBounds().width)) {
				return shape;		
			}
		}
		return null;
	}
	public void unselectAll(){
		SCollection newModel = (SCollection) super.getModel();
		Shape shape;
		Iterator<Shape> iter=newModel.iterator();
		while(iter.hasNext()) {
			shape=iter.next();
			if (shape.countainsAttributes(SelectionAttributes.id)) {
				SelectionAttributes sAttri = (SelectionAttributes) shape.getAttributes(SelectionAttributes.id);
				sAttri.unselect();
			}
		}
	}
	
	public void translate(int x,int y){
		SCollection newModel = (SCollection) super.getModel();
		Shape shape;
		Iterator<Shape> iter=newModel.iterator();
	}
	public void select(){
		SCollection newModel = (SCollection) super.getModel();
		Shape shape;
		Iterator<Shape> iter=newModel.iterator();
	}
	public void unselect(){
		SCollection newModel = (SCollection) super.getModel();
		Shape shape;
		Iterator<Shape> iter=newModel.iterator();
	}
	
	public void toggleSelection(){
		
	}
	
	/*public boolean shiftDown(){
		this.keyPressed(new KeyEvent(view, 0, 0, 0, 0));;
	} 
	public boolean isSelected(){
		
	}*/

}

