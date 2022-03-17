package mpjp.quad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import mpjp.shared.HasPoint;

public class NodeTrie<T extends HasPoint> extends Trie<T> {

	private Map<Trie.Quadrant, Trie<T>> tries;
	private double middleX;
	private double middleY;
	
	public NodeTrie(double topLeftX, double topLeftY, double bottomRightX, double bottomRightY) {
		super(topLeftX, topLeftY, bottomRightX, bottomRightY);
		tries = new HashMap<Trie.Quadrant,Trie<T>>();
		double width = (bottomRightX-topLeftX)/2;
		double height = (topLeftY-bottomRightY)/2;
		middleX = topLeftX+width;
		middleY = bottomRightY+height;
		tries.put(Trie.Quadrant.NW, new LeafTrie<>(topLeftX, topLeftY, middleX, middleY));
		tries.put(Trie.Quadrant.NE, new LeafTrie<>(middleX, middleY+height, middleX+width, middleY));
		tries.put(Trie.Quadrant.SE, new LeafTrie<>(middleX, middleY, bottomRightX, bottomRightY));
		tries.put(Trie.Quadrant.SW, new LeafTrie<>(middleX-width, middleY, middleX, middleY-height));
	};
	
	@Override
	T find(T point) {
		Trie<T> trie = tries.get(quadrantOf(point));
		return trie.find(point);
	};
	
	public Trie.Quadrant quadrantOf(T point){
		if (point.getX()>=middleX && point.getY()>=middleY) {
			return Trie.Quadrant.NE;
		} else if (point.getX()>=middleX && point.getY()<=middleY) {
			return Trie.Quadrant.SE;
		} else if (point.getX()<=middleX && point.getY()>=middleY) {
			return Trie.Quadrant.NW;
		} else {
			return Trie.Quadrant.SW;
		}
	};
	
	@Override
	Trie<T> insert(T point) {
		if (pointOutOfBound(point)) {
			throw new PointOutOfBoundException();
		} else {
			Trie.Quadrant quadrant = quadrantOf(point);
			Trie<T> trie = tries.get(quadrant);
			tries.put(quadrant,trie.insert(point));
			return this;
		}
	}
	
	@Override
	Trie<T> insertReplace(T point) {
		Trie.Quadrant quadrant = quadrantOf(point);
		Trie<T> trie = tries.get(quadrant);
		tries.put(quadrant,trie.insert(point));
		return this;
	}
	
	@Override
	void collectNear(double x, double y, double radius, java.util.Set<T> nodes) {
		for(Trie.Quadrant key: tries.keySet()) {
			if(tries.get(key).overlaps(x, y, radius)) {
				java.util.Set<T> no = new HashSet<T>();
				tries.get(key).collectNear(x, y, radius, no);
				nodes.addAll(no);
			}
		}
	}
	
	@Override
	void collectAll(java.util.Set<T> nodes) {
		for(Trie.Quadrant key: tries.keySet()) {
			java.util.Set<T> no = new HashSet<T>();
			tries.get(key).collectAll(no);
			nodes.addAll(no);
		}
	}
	
	@Override
	void delete(T point) {
		Trie<T> trie = tries.get(quadrantOf(point));
		trie.delete(point);
	}

	@Override
	public String toString() {
		return "LeafTrie [bottomRightX=" + bottomRightX + ", bottomRightY=" + bottomRightY + ", topLeftX=" + topLeftX
				+ ", topLeftY=" + topLeftY + "]";
	};
	
	public void accept(Visitor<T> visitor) {
		visitor.visit(this);
	};
	
	public java.util.Collection<Trie<T>> getTries() {
		java.util.Collection<Trie<T>> collection=new ArrayList<Trie<T>>();
		for(Trie.Quadrant key: tries.keySet()) {
			collection.add(tries.get(key));
		}
		return collection;
		
	};
}
