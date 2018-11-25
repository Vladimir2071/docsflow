package lva.docsflow.catalogs;

public abstract class Catalog {

	protected int id;
	protected String code;
	protected String name;
	
	public Catalog(int id, String code, String name) {
		super();
		this.id	= id;
		this.code = code;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
