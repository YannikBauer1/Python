package mpjp.shared;

import java.util.Map;

import mpjp.shared.geom.PieceShape;
import mpjp.shared.geom.Point;

public class PuzzleView extends java.lang.Object
implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.String image;
	private Map<Integer, Point> locations;
	private double pieceHeight;
	private double pieceWidth;
	private double puzzleHeight;
	private double puzzleWidth;
	private java.util.Map<java.lang.Integer, PieceShape> shapes;
	private java.util.Date start;
	private double workspaceHeight;
	private double workspaceWidth;
	
	public PuzzleView() {}

	public PuzzleView(java.util.Date start, double workspaceWidth, double workspaceHeight, 
			double puzzleWidth, double puzzleHeight, double pieceWidth, double pieceHeight, 
			java.lang.String image, java.util.Map<java.lang.Integer, PieceShape> shapes, 
			Map<Integer,Point> locations) {
		this.image = image;
		this.locations = locations;
		this.pieceHeight = pieceHeight;
		this.pieceWidth = pieceWidth;
		this.puzzleHeight = puzzleHeight;
		this.puzzleWidth = puzzleWidth;
		this.shapes = shapes;
		this.start = start;
		this.workspaceHeight = workspaceHeight;
		this.workspaceWidth = workspaceWidth;
	};
	
	public java.util.Date getStart() {
		return start;
	};
	
	public double getWorkspaceHeight() {
		return workspaceHeight;
	};
	
	public double getWorkspaceWidth() {
		return workspaceWidth;
	};
	
	public double getPuzzleHeight() {
		return puzzleHeight;
	};
	
	public double getPuzzleWidth() {
		return puzzleWidth;
	};
	
	public double getPieceHeight() {
		return pieceHeight;
	};
	
	public double getPieceWidth() {
		return pieceWidth;
	};
	
	public java.lang.String getImage() {
		return image;
	};
	
	public PieceShape getPieceShape(int id) {
		return shapes.get(id);
	};
	
	public Point getStandardPieceLocation(int id) {
		return locations.get(id);
	};
}

	
