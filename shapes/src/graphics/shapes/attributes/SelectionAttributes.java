package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes{
	
	public static final String id = "Select";
	private boolean selected;
	
	public SelectionAttributes(){
		this.selected=false;
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	public boolean isSelected(){
		return this.selected;
	}
	public void select(){
		this.selected=true;
	}
	public void unselect(){
		this.selected=false;
	}
	public void toggleSelection(){
		if (this.selected==true){
			this.unselect();
		}
		if (this.selected==false){
			this.select();
		}
	}
}
