package mpjp.shared.geom;

public class QuadTo extends java.lang.Object
implements Segment, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point controlPoint;
	private Point endPoint;
	
	public QuadTo() {};
	
	public QuadTo(Point controlPoint, Point endPoint) {
		this.controlPoint = controlPoint;
		this.endPoint = endPoint;
	};
	
	public Point getControlPoint() {
		return controlPoint;
	};
	public void setControlPoint(Point controlPoint) {
		this.controlPoint = controlPoint;
	};
	public Point getEndPoint() {
		return endPoint;
	};
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	};
}
