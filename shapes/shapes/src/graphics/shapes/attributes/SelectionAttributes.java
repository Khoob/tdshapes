package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes{
	
	private boolean selected;
	
	public SelectionAttributes(){
		this.selected=false;
	}
	public SelectionAttributes(boolean bool){
		this.selected=bool;
	}
	
	@Override
	public String getId() {
		return "Select";
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
