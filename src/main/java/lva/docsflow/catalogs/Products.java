package lva.docsflow.catalogs;

public class Products extends Catalog {
	
	private int Package_qty;
    private float Volume;
    private float UnitWeight;	
    
	public Products(int id, String code, String name, int qty, float volume, float weight) {
		super(id, code, name);
		this.Package_qty = qty;
		this.Volume = volume;
		this.UnitWeight = weight;
	}
	public int getQty() {
		return Package_qty;
	}
	
	public float getVoleme() {
		return Volume;
	}
	
	public float getWeight() {
		return UnitWeight;
	}
	
	public void setQty(int qty) {
		this.Package_qty = qty;
	}

	public void setVolume(float volume) {
		this.Volume = volume;
	}
	
	public void setWeight(float weight) {
		this.UnitWeight = weight;
	}	
	public int create() {
		return 1;
	};


}
