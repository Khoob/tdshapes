package graphics.shapes.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

public class ShapesController extends Controller {
	
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	private boolean isShiftDown;

	public ShapesController(Object newModel) {
		super(newModel);
		Iterator<Shape> iter = ((SCollection) newModel).iterator();
		while(iter.hasNext()){
			Shape shape = iter.next();
			if (this.isSelected(shape)) {
				this.selectedShapes.add(shape);
				System.out.println(this.selectedShapes.toString());
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e){
		Shape shape = this.getTarget(e);
		if (shape != null) {
			if (this.selectedShapes.isEmpty()){	
				this.select(shape);
			}
			else{
				if (this.shiftDown()){
					this.select(shape);
				}
				else {
					this.unselectAll();
					this.select(shape);
				}
			}
		}
		else{
			this.unselectAll();
		}
		getView().updateUI();
	}
	/*
	@Override
	public void mousePressed(MouseEvent e){
		Shape shape = this.getTarget(e);
		if (((SelectionAttributes) shape.getAttributes("Select")).isSelected()) {
			
		}
	}*/
	
	/*
	@Override
	public void mouseReleased(MouseEvent e){
		getView().updateUI();
	}*/
	@Override
	public void mouseDragged(MouseEvent e){
		this.translateSelected(e.getX(), e.getY());
		getView().updateUI();
	}
	public void translateSelected(int x,int y){
		Iterator<Shape> iter = this.selectedShapes.iterator();
		while(iter.hasNext()){
			Shape shape = iter.next();
			shape.translate(x - shape.getLoc().x, y - shape.getLoc().y); // soustraction de la position actuelle de la shape pour ne pas partir vers l'infini
		}
	}
	public Shape getTarget(MouseEvent e){
		SCollection newModel = (SCollection) super.getModel();
		Shape shape;
		Iterator<Shape> iter=newModel.iterator();
		while(iter.hasNext()) {
			shape=iter.next();
			if (shape.getBounds().contains(e.getPoint())) {
				return shape;		
			}
		}
		return null;
	}
	public void unselectAll(){
		ArrayList<Shape> shapesToBeRemoved = new ArrayList<Shape>();
		this.selectedShapes.forEach(shapes->{shapesToBeRemoved.add(shapes);}); // on ne peut pas modifier une liste en meme temps qu'on la parcours
		shapesToBeRemoved.forEach(shapes->{this.unselect(shapes);});
	}
	/*public void translate(int x,int y){
		SCollection newModel = (SCollection) super.getModel();
		Shape shape;
		Iterator<Shape> iter=newModel.iterator();
	}*/
	public void select(Shape shape){
		this.selectedShapes.add(shape);
		((SelectionAttributes) shape.getAttributes("Select")).select();
	}
	public void unselect(Shape shape){
		this.selectedShapes.remove(shape);
		((SelectionAttributes) shape.getAttributes("Select")).unselect();
	}
	public void toggleSelection(Shape shape){
		if (this.isSelected(shape)){
			this.unselect(shape);
		}
		else {
			this.select(shape);
		}
	}
	public boolean isSelected(Shape shape){
		return ((SelectionAttributes) shape.getAttributes("Select")).isSelected();
	}
	@Override
	public void keypressed(KeyEvent e){
		System.out.println("merde");
		 if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
             System.out.println("Right");
         }

         if (e.getKeyCode() == KeyEvent.VK_LEFT) {
             System.out.println("Left");
         }
	}
	@Override
	public void keyreleased(KeyEvent e){
		this.isShiftDown = e.isShiftDown();
	}
	public boolean shiftDown(){
		return this.isShiftDown;
	} 
}
