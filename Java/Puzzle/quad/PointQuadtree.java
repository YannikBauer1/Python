package mpjp.quad;

import java.util.HashSet;

import mpjp.shared.HasPoint;

public class PointQuadtree<T extends HasPoint> extends java.lang.Object
implements java.lang.Iterable<T> {
	
	public class PointIterator extends java.lang.Object
	implements java.util.Iterator<T>, java.lang.Runnable, Visitor<T>{
		
		private T nextPoint;
		private boolean terminated;
		private java.lang.Thread thread;
		
		public PointIterator() {
			thread = new Thread(this,"Point iterator");
			thread.start();
		};
		
		@Override
		public void visit(LeafTrie<T> leaf) {
			for(T point: leaf.getPoints()) {
				synchronized (this) {
					if(nextPoint != null)
						handshake();
					nextPoint = point;
					handshake();
				}
				
			}
		}

		@Override
		public void run() {
			terminated=false;
			if(top.getClass().getName()=="mpjp.quad.NodeTrie") {
				visit((NodeTrie<T>) top);
			}else {
				visit((LeafTrie<T>) top);
			}
			synchronized (this) {
				terminated=true;
				handshake();
			}
		}

		@Override
		public boolean hasNext() {
			synchronized (this) {
				if(! terminated) {
					handshake();
				}
				return nextPoint != null;
			}
		}

		@Override
		public T next() {
			T point = nextPoint;
			synchronized(this) {
				nextPoint = null;
			}
			return point;
		}

		@Override
		public void visit(NodeTrie<T> node) {
			java.util.Collection<Trie<T>> tries = node.getTries();
			for (Trie<T> trie: tries) {
				if(trie.getClass().getName()=="mpjp.quad.NodeTrie") {
					visit((NodeTrie<T>) trie);
				}else {
					visit((LeafTrie<T>) trie);
				}
			}
		}
		
		private void handshake() {
			notify();
			try {
				wait();
			} catch (InterruptedException cause) {
				throw new RuntimeException("Unexpected interruption while waiting",cause);
			}
		}
	}

	private Trie<T> top;
	
	public PointQuadtree(double topLeftX, double topLeftY, double bottomRightX, double bottomRightY) {
		top = new LeafTrie<T>(topLeftX, topLeftY, bottomRightX, bottomRightY);
	};
	
	public PointQuadtree(double width, double height) {
		top = new LeafTrie<T>(0, width, height, 0);
	};
	
	public PointQuadtree(double width, double height, double margin) {
		top = new LeafTrie<T>(-margin, height+margin, width+margin, -margin);
	};
	
	public T find(T point) {
		return top.find(point);
	};
	
	public void insert(T point) {
		top=top.insert(point);
	};
	
	public void insertReplace(T point){
		top=top.insertReplace(point);
	};
	
	public java.util.Set<T> findNear(double x, double y, double radius) {
		java.util.Set<T> nodes = new HashSet<T>();
		top.collectNear(x, y, radius, nodes);
		return nodes;
	};
	
	public void delete(T point) {
		top.delete(point);																					
	};
	
	public java.util.Set<T> getAll() {
		java.util.Set<T> nodes = new HashSet<T>();
		top.collectAll(nodes);
		return nodes;
	};

	public java.util.Iterator<T> iterator() {
		return new PointIterator();
	};
	
	
	
}
