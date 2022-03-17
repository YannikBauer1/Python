package mpjp.shared.geom;

import java.util.ArrayList;
import java.util.List;

public class PieceShape extends java.lang.Object
implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.util.List<Segment> segments = new ArrayList<Segment>();
	private Point startPoint;
	
	public PieceShape() {};
	
	public PieceShape(Point startPoint) {
		this.startPoint = startPoint;
	};
	
	public PieceShape (Point startPoint, java.util.List<Segment> segments) {
		this.startPoint = startPoint;
		this.segments = segments;
	};
	
	public java.util.List<Segment> getSegments() {
		return segments;
	};
	
	public void setSegments(java.util.List<Segment> segments) {
		this.segments = segments;
	};
	
	public Point getStartPoint() {
		return startPoint;
	};
	
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	};
	
	public PieceShape addSegment(Segment segment) {
		segments.add(segment);
		return this;
	};
}
