package mpjp.shared;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PuzzleLayout extends java.lang.Object
implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer,List<Integer>> blocks = new HashMap<Integer,List<Integer>>();
	private int percentageSolved;
	private Map<Integer,PieceStatus> pieces = new HashMap<Integer,PieceStatus>();
	
	public PuzzleLayout() {};
	
	public PuzzleLayout(Map<Integer,PieceStatus> pieces,
			Map<Integer,List<Integer>> blocks) {
		this.pieces = pieces;
		this.blocks = blocks;
		int p = getPieces().size();
		int b = getBlocks().size();
		this.percentageSolved = 100 * (p - b) / (p - 1);
	};
	
	public Map<Integer,PieceStatus> getPieces() {
		return pieces;
	}
	
	public Map<Integer,List<Integer>> getBlocks() {
		return blocks;
	}
	
	public int getPercentageSolved() {
		return percentageSolved;
	}
	
	public boolean isSolved() {
		if (blocks.size() > 1) {
			return false;
		} else {
			return true;
		}
	}
	
	public int getBlock(int id) {
		for (Integer key : getBlocks().keySet()) {
			if (getBlocks().get(key).contains(id)) {
				return id;
			}
		}
		return (Integer) null;
	}
	public PieceStatus getPieceStatus(int id) {
		for (Integer key : getPieces().keySet()) {
			if (key==id) {
				return getPieces().get(key);
			}
		}
		return null;
	}
}
