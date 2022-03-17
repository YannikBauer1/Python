package mpjp.game;

import mpjp.shared.MPJPException;
import mpjp.shared.PuzzleInfo;
import mpjp.shared.geom.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class PuzzleStructure extends java.lang.Object
implements java.lang.Iterable<java.lang.Integer> ,java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int columns;
	private int rows;
	private double width;
	private double height;

	public PuzzleStructure(int rows, int columns, double width, double height){
		this.rows = rows;
		this.columns = columns;
		this.width = width;
		this.height = height;
	};
	
	PuzzleStructure(PuzzleInfo info) {
		columns=info.getColumns();
		rows=info.getRows();
		width=info.getWidth();
		height=info.getHeight();
	};
	
	public PuzzleStructure() {}
	
	void init(int rows, int columns, double width, double heigth) {
		
	};
	
	public int getPieceCount() {
		int pieceCount = rows * columns;
		return pieceCount;
	};
	
	public int getRows() {
		return rows;
	};
	
	public void setRows(int rows) {
		this.rows = rows;
	};
	
	public int getColumns() {
		return columns;
	};
	
	public void setColumns(int columns) {
		this.columns = columns;
	};
	
	public double getWidth() {
		return width;
	};
	
	public void setWidth(double width) {
		this.width = width;
	};
	
	public double getHeight() {
		return height;
	};
	
	public void setHeight(double height) {
		this.height = height;
	};
	
	public double getPieceWidth() {
		double pieceWidth = width / columns;
		return pieceWidth;
	};
	
	public double getPieceHeight() {
		double pieceHeight = height / rows;
		return pieceHeight;
	};
	
	public java.lang.Integer getPieceFacing(Direction direction, java.lang.Integer id) {
		int quotiente = id/columns;
		int resto = id%columns;
		if (direction.toString()=="EAST") {
			if (resto==columns-1) {return null;}
			return id+1;
		} else if (direction.toString()=="SOUTH") {
			if (quotiente==rows-1) {return null;}
			return id+columns;
		} else if (direction.toString()=="WEST") {
			if (resto==0) {return null;}
			return id-1;
		} else if (direction.toString()=="NORTH") {
			if (quotiente==0) {return null;}
			return id-columns;
		} else { return null; }
	};
	
	public Point getPieceCenterFacing(Direction direction, Point point) {
		if (direction.toString()=="EAST") {
			double y = point.getY();
			double x = point.getX()+getPieceWidth();
			return new Point(x,y);
		} else if (direction.toString()=="SOUTH") {
			double y = point.getY()+getPieceHeight();
			double x = point.getX();
			return new Point(x,y);
		} else if (direction.toString()=="WEST") {
			double y = point.getY();
			double x = point.getX()-getPieceWidth();
			return new Point(x,y);
		} else if (direction.toString()=="NORTH") {
			double y = point.getY()-getPieceHeight();
			double x = point.getX();
			return new Point(x,y);
		} else {return null;}
	};
	
	public int getPieceRow(int id) throws MPJPException {
		if (id>=getPieceCount() || id<0) {throw new MPJPException();}
		int quotiente = id/columns;
		return quotiente;
	};
	
	public int getPieceColumn(int id) throws MPJPException {
		if (id>=getPieceCount() || id<0) {throw new MPJPException();}
		int resto = id%columns;
		return resto;
	};
	
	public Map<Integer, Point> getStandardLocations() throws MPJPException {
		Map<Integer, Point> locations = new HashMap<Integer, Point>();
		for (int i=0; i<getPieceCount();i++) {
			locations.put(i, getPieceStandardCenter(i));
		}
		return locations;
	};
	
	public Point getPieceStandardCenter(int id) throws MPJPException {
		if (id>=getPieceCount() || id<0) {throw new MPJPException();}
		double x = getPieceColumn(id)*getPieceWidth()+(getPieceWidth()/2);
		double y = getPieceRow(id)*getPieceHeight()+(getPieceHeight()/2);
		return new Point(x,y);
	};
	
	public java.util.Set<java.lang.Integer> getPossiblePiecesInStandarFor(Point point) {
		double x = point.getX();
		double y = point.getY();
		int col = (int) (x/getPieceWidth());
		int row = (int) (y/getPieceHeight());
		int id = row*columns+col;
		Set<Integer> set1 = new HashSet<Integer>();
		Set<Integer> set2 = new HashSet<Integer>();
		if (getPieceFacing(Direction.WEST,id)!=null) {
			set1.add(getPieceFacing(Direction.WEST,id));
		}
		set1.add(id);
		if (getPieceFacing(Direction.EAST,id)!=null) {
			set1.add(getPieceFacing(Direction.EAST,id));
		}
		for (Integer i: set1) {
			if (getPieceFacing(Direction.NORTH,i)!=null) {
				set2.add(getPieceFacing(Direction.NORTH,i));
			}
		}
		set2.addAll(set1);
		for (Integer i: set1) {
			if (getPieceFacing(Direction.SOUTH,i)!=null) {
				set2.add(getPieceFacing(Direction.SOUTH,i));
			}
		}
		return set2;
	};
	
	public Point getRandomPointInStandardPuzzle() {
		Random r1 = new Random();
		double x = width * r1.nextDouble();
		Random r2 = new Random();
		double y = height * r2.nextDouble();
		return new Point(x,y);	
	};
	
	public java.util.Iterator<java.lang.Integer> iterator() {
		List<Integer> idList = new ArrayList<Integer>();
		for (int i=0; i<getPieceCount();i++) {
			idList.add(i);
		}
		Iterator<Integer> iterator = idList.iterator();
		return iterator;
	};
}
