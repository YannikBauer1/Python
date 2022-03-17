package mpjp.shared.geom;

public class LineTo extends java.lang.Object
implements Segment, java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point endPoint;
	
	public LineTo() {};
	
	public LineTo(Point endPoint) {
		this.endPoint = endPoint;
	};
	
	public Point getEndPoint() {
		return endPoint;
	};
	
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	};
}
