package mpjp.game.cuttings;

import java.util.Map;

import mpjp.game.PuzzleStructure;
import mpjp.shared.geom.PieceShape;

public interface Cutting {
	
	public Map<Integer, PieceShape> getShapes(PuzzleStructure structure);
}
