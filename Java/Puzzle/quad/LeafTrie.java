package mpjp.quad;

import java.util.ArrayList;

import mpjp.shared.HasPoint;

public class LeafTrie<T extends HasPoint> extends Trie<T> {

	private java.util.Collection<T> points;

	public LeafTrie(double topLeftX, double topLeftY, double bottomRightX, double bottomRightY) {
		super(topLeftX, topLeftY, bottomRightX, bottomRightY);
		points = new ArrayList<T>();
	};
	
	public java.util.Collection<T> getPoints() {
		return points;
	}
	
	@Override
	T find(T point) {
		for (T p : points) {
			if (p.getX()==point.getX() && p.getY()==point.getY()){
				return point;
			}
		}
		return null;
	};
	
	@Override
	Trie<T> insert(T point) {
		if (pointOutOfBound(point)) {
			throw new PointOutOfBoundException();
		} else {
			if (this.getCapacity()>points.size()) {
				points.add(point);
				return this;
			} else {
				NodeTrie<T> nodeTrie = new NodeTrie<T>(topLeftX, topLeftY, bottomRightX, bottomRightY);
				for (T p : points) {
					nodeTrie.insert(p);
				}
				nodeTrie.insert(point);
				return nodeTrie;
			}
		}
	}
	
	@Override
	Trie<T> insertReplace(T point) {
		delete(find(point)); //nao consigui apagar todos os pontos com as coord. de point
		points.add(point);
		return this;
	}
	
	@Override
	void collectNear(double x, double y, double radius, java.util.Set<T> nodes) {
		for(T p : points) {
			if(getDistance(p.getX(), p.getY(), x, y)<=radius) {
				nodes.add(p);
			}
		}
	}
	
	@Override
	void collectAll(java.util.Set<T> nodes) {
		for(T p : points) {
			nodes.add(p);
		}
	}
	
	@Override
	void delete(T point) {
		points.remove(point);
	}

	@Override
	public String toString() {
		return "LeafTrie [bottomRightX=" + bottomRightX + ", bottomRightY=" + bottomRightY + ", topLeftX=" + topLeftX
				+ ", topLeftY=" + topLeftY + "]";
	};
	
	public void accept(Visitor<T> visitor) {
		visitor.visit(this);
	};
	

}
