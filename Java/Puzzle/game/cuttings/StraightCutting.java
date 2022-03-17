package mpjp.game.cuttings;

import java.util.HashMap;
import java.util.Map;

import mpjp.game.PuzzleStructure;
import mpjp.shared.geom.LineTo;
import mpjp.shared.geom.PieceShape;
import mpjp.shared.geom.Point;

public class StraightCutting extends java.lang.Object
implements Cutting {

	private double pieceWidth;
	private double pieceHeight;
	
	public StraightCutting() {};
	
	@Override
	public Map<Integer, PieceShape> getShapes(PuzzleStructure structure) {
		pieceWidth=structure.getPieceWidth();
		pieceHeight=structure.getPieceHeight();
		
		Map<Integer, PieceShape> pieces = new HashMap<Integer, PieceShape>();
		
		for(int i = 0; i<structure.getPieceCount(); i++) {
			pieces.put(i, piece(new Point(-pieceWidth/2,-pieceHeight/2)));
		}
		return pieces;
	};
	
	public PieceShape piece(Point startPoint) {
		double x = startPoint.getX();
		double y = startPoint.getY();
		PieceShape piece = new PieceShape(startPoint)
				.addSegment(new LineTo(new Point(x+pieceWidth,y)))
				.addSegment(new LineTo(new Point(x+pieceWidth,y+pieceHeight)))
				.addSegment(new LineTo(new Point(x,y+pieceHeight)))
				.addSegment(new LineTo(startPoint));
		return piece;
	}
	
	
}
