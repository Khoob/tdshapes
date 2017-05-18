package graphics.shapes;

public interface ShapeVisitor {
	public abstract void visitRectangle(SRectangle srect); 
	public abstract void visitCircle(SCircle scir);
	public abstract void visitText(SText stext);
	public abstract void visitCollection(SCollection scol);
}
