package mpjp.shared;

import mpjp.shared.geom.Point;

public class PieceStatus extends java.lang.Object 
implements HasPoint, java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int block;
	private int id;
	private Point position;
	private double rotation;
	
	public PieceStatus() {};
	
	public PieceStatus (int id, Point point) {
		this.id = id;
		this.position = point;
	};
	
	public java.lang.Integer getId(){
		return id;
	}
	
	@Override
	public double getX() {
		return position.getX();
	}
	
	@Override
	public double getY() {
		return position.getY();
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}
	
	public int getBlock() {
		return block;
	}
	
	public void setBlock(int block) {
		this.block = block;
	}
	
	public double getRotation() {
		return rotation;
	}
	
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
}
