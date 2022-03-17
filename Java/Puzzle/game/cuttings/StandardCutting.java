package mpjp.game.cuttings;

import java.util.Map;

import mpjp.game.Direction;
import mpjp.game.PuzzleStructure;
import mpjp.shared.geom.PieceShape;
import mpjp.shared.geom.Point;

class StandardCutting extends java.lang.Object
implements Cutting {

	StandardCutting() {};
	
	public Map<Integer, PieceShape> getShapes(PuzzleStructure structure) {
		return null;
	};
	
	public Point getMiddlePoint(int id, Direction direction) {
		return null;
	};
	
	public Point getStartControlPoint1(int id, Direction direction) {
		return null;
	};
	
	public Point getStartControlPoint2(int id, Direction direction) {
		return null;
	};
	
	public Point getEndControlPoint1(int id, Direction direction) {
		return null;
	};
	
	public Point getEndControlPoint2(int id, Direction direction) {
		return null;
	};	
}
