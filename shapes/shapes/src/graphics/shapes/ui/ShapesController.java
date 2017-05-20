package graphics.shapes.ui;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
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
		/*KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		kfm.addKeyEventDispatcher(new KeyEventDispatcher(){
		      @Override
		      public boolean dispatchKeyEvent(KeyEvent e) {
		        System.out.println("Got key event!");
		        return false;
		      }}
		      );*/
	}
	@Override
	public void mouseClicked(MouseEvent e){
		Shape shape = this.getTarget(e);
		if (this.isShiftDown){
			if (shape != null) {
				this.toggleSelection(shape);
			}
		}
		else{
			this.unselectAll();
			if (shape!= null){
				this.toggleSelection(shape);
			}
		}
		getView().updateUI();
	}
	@Override
	public void mousePressed(MouseEvent e){
		Shape shape = this.getTarget(e);
		if (shape != null && this.isSelected(shape)) {
			
		}
		if (shape == null || !this.isSelected(shape)){
			
		}
		getView().updateUI();
	}
	@Override
	public void mouseReleased(MouseEvent e){
		getView().updateUI();
	}
	@Override
	public void mouseDragged(MouseEvent e){
		this.translateSelected(e.getX(), e.getY());
		getView().updateUI();
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
	public void translate(int x,int y,Shape shape){
		shape.translate(x - shape.getLoc().x, y - shape.getLoc().y); // soustraction de la position actuelle de la shape pour ne pas partir vers l'infini
	}
	public void translateSelected(int x,int y){
		Iterator<Shape> iter = this.selectedShapes.iterator();
		SCollection scol = new SCollection();
		while(iter.hasNext()){
			scol.add(iter.next());	
		}
		this.translate(x, y, scol);
	}
	public void select(Shape shape){
		this.selectedShapes.add(shape);
		((SelectionAttributes) shape.getAttributes("Select")).select();
	}
	public void unselect(Shape shape){
		this.selectedShapes.remove(shape);
		((SelectionAttributes) shape.getAttributes("Select")).unselect();
	}
	public void unselectAll(){
		ArrayList<Shape> shapesToBeRemoved = new ArrayList<Shape>();
		this.selectedShapes.forEach(shapes->{shapesToBeRemoved.add(shapes);}); // on ne peut pas modifier une liste en meme temps qu'on la parcours
		shapesToBeRemoved.forEach(shapes->{this.unselect(shapes);});
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
	public void keyPressed(KeyEvent e){
		if(e.isShiftDown()){
			this.isShiftDown = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e){
		if(!e.isShiftDown()){
			this.isShiftDown = false;
		}
	}
	@Override
	public void keyTyped(KeyEvent e){
		
	}
	public boolean shiftDown(){
		return this.isShiftDown;
	} 
}
