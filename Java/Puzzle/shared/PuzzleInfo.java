package mpjp.shared;

public class PuzzleInfo extends java.lang.Object
implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int columns;
	private java.lang.String cuttingName;
	private double height;
	private java.lang.String imageName;
	private int rows;
	private double width;
	
	public PuzzleInfo() {};
	
	public PuzzleInfo(java.lang.String imageName, java.lang.String cuttingName, int rows, int columns, double width, double height) {
		this.columns = columns;
		this.cuttingName = cuttingName;
		this.height = height;
		this.imageName = imageName;
		this.rows = rows;
		this.width = width;
	}
	
	public java.lang.String getImageName() {
		return imageName;
	}
	
	public void setImageName(java.lang.String imageName) {
		this.imageName = imageName;
	}
	
	public java.lang.String getCuttingName() {
		return cuttingName;
	}
	
	public void setCuttingName(java.lang.String cuttingName) {
		this.cuttingName = cuttingName;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public int getRows() {
		return rows;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public void setColumns(int columns) {
		this.columns = columns;
	}
}
