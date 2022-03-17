package mpjp.quad;

import mpjp.shared.HasPoint;

public abstract class Trie<T extends HasPoint> extends java.lang.Object implements Element<T> {

	static enum Quadrant {
		NW, NE, SE, SW;

		/*
		public static Trie.Quadrant valueOf(java.lang.String name) {
			Trie.Quadrant v = null;
			switch (name) {
			case "NW":
				v = NW;
				break;
			case "NE":
				v = NE;
				break;
			case "SE":
				v = SE;
				break;
			case "SW":
				v = SW;
				break;
			}
			return v;
			// falta throw errors
		};*/
	}

	protected double bottomRightX;
	protected double bottomRightY;
	private static int capacity;
	protected double topLeftX;
	protected double topLeftY;

	public Trie(double topLeftX, double topLeftY, double bottomRightX, double bottomRightY) {
		this.bottomRightX = bottomRightX;
		this.bottomRightY = bottomRightY;
		this.topLeftX = topLeftX;
		this.topLeftY = topLeftY;
	}

	public int getCapacity() {
		return capacity;
	};

	public static void setCapacity(int capacity) {
		Trie.capacity = capacity;
	};
	
	abstract T find(T point);
	
	abstract Trie<T> insert(T point);
	
	abstract Trie<T> insertReplace(T point);
	
	abstract void collectNear(double x, double y, double radius, java.util.Set<T> points);
	
	abstract void collectAll(java.util.Set<T> points);
	
	abstract void delete(T point);

	boolean overlaps(double x, double y, double radius) {
		if(x>=topLeftX && x<=bottomRightX &&
				y>=bottomRightY && y<=topLeftY) {
			return true;
		}else if (x<topLeftX && y>=bottomRightY && y<=topLeftY &&
				x+radius>=topLeftX) {
			return true;
		} else if (x>bottomRightX && y>=bottomRightY && y<=topLeftY &&
				x-radius<=bottomRightX) {
			return true;
		} else if (x>=topLeftX && x<=bottomRightX && y>topLeftY &&
				y-radius<=topLeftY) {
			return true;
		} else if (x>=topLeftX && x<=bottomRightX && y<bottomRightY &&
				y+radius>=bottomRightY) {
			return true;
		} else if (x<topLeftX && y>topLeftY &&
				radius>=getDistance(x,y,topLeftX,topLeftY)) {
			return true;
		} else if (x>bottomRightX && y>topLeftY &&
				radius>=getDistance(x,y,bottomRightX,topLeftY)) {
			return true;
		} else if (x>bottomRightX && y<bottomRightY &&
				radius>=getDistance(x,y,bottomRightX,bottomRightY)) {
			return true;
		} else if (x<topLeftX && y<=bottomRightY &&
				radius>=getDistance(x,y,topLeftX,bottomRightY)) {
			return true;
		} else {
			return false;
		}
	};

	@Override
	public java.lang.String toString() {
		return "Trie [bottomRightX=" + bottomRightX + ", bottomRightY=" + bottomRightY + ", topLeftX=" + topLeftX
				+ ", topLeftY=" + topLeftY + "]";
	};

	public static double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	public boolean pointOutOfBound(T point) {
		double x = point.getX();
		double y = point.getY();
		if (x<topLeftX || x>bottomRightX ||
				y<bottomRightY || y>topLeftY) {
			return true;
		}
		return false;
	}
}
