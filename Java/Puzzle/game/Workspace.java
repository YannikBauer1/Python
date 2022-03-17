package mpjp.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import mpjp.game.cuttings.Cutting;
import mpjp.game.cuttings.CuttingFactoryImplementation;
import mpjp.shared.MPJPException;
import mpjp.shared.PieceStatus;
import mpjp.shared.PuzzleInfo;
import mpjp.shared.PuzzleLayout;
import mpjp.shared.PuzzleSelectInfo;
import mpjp.shared.PuzzleView;
import mpjp.shared.geom.PieceShape;
import mpjp.shared.geom.Point;

public class Workspace extends java.lang.Object
implements java.io.Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Direction NORTH = Direction.NORTH;
	private static Direction WEST = Direction.WEST;
	private static Direction SOUTH = Direction.SOUTH;
	private static Direction EAST = Direction.EAST;
	PuzzleInfo info;
	private PuzzleView view;
	private PuzzleStructure structure;
	private PuzzleLayout currentLayout = new PuzzleLayout();
	static double heightFactor=1.5;
	static double widthFactor=1.5;
	static double radius;
	
	private List<String> clients = new ArrayList<String>();
	private String gameName;
	private String password;
	
	Workspace() {};
	
	public Workspace(PuzzleInfo info,String gameName, String password) 
			throws MPJPException {
		this.gameName=gameName;
		this.password=password;
		this.info = info;
		this.structure = new PuzzleStructure(info);
		CuttingFactoryImplementation factory = new CuttingFactoryImplementation();
		String s = info.getCuttingName();
		Cutting c = factory.createCutting(s);
		java.util.Map<java.lang.Integer, PieceShape> shapes = c.getShapes(structure);
		//widthFactor=690/info.getWidth();
		//heightFactor=400/info.getHeight();
		this.view = new PuzzleView(new Date(System.currentTimeMillis()), 
				info.getWidth()*widthFactor, info.getHeight()*heightFactor,
				info.getWidth(), info.getHeight(), structure.getPieceWidth(),
				structure.getPieceHeight(), info.getImageName(), shapes, 
				structure.getStandardLocations());
		scatter();
	}
	public List<String> getClients (){
		return clients;
	}
	public void addClient(String name) {
		clients.add(name);
	}
	
	public static double getWidthFactor() {
		return widthFactor;
	}
	
	public static void setWidthFactor(double widthFactor) {
		Workspace.widthFactor = widthFactor;
	}
	
	public static double getHeightFactor() {
		return Workspace.heightFactor;
	}
	
	public static void setHeightFactor(double heightFactor) {
		Workspace.heightFactor = heightFactor;
	};
	
	public static double getRadius() {
		return Workspace.radius;
	};
	
	public static void setRadius(double radius) {
		Workspace.radius = radius;
	};
	
	public java.lang.String getId() {
		String img = info.getImageName().toString().split("\\.")[0];
		String time = view.getStart().toString().replaceAll("\\s+","").replaceAll(":", "");
		return img+time;
	};
	
	public double getSelectRadius() {
		return Math.max(structure.getPieceHeight(),structure.getPieceWidth());
	};
	
	public PuzzleSelectInfo getPuzzleSelectInfo() {
		return new PuzzleSelectInfo(info, view.getStart(), 
				this.getPercentageSolved(),gameName,password);
	};
	
	public int getPercentageSolved() {
		return currentLayout.getPercentageSolved();
	};
	
	public PuzzleView getPuzzleView() throws MPJPException {
		return view;
	};
	
	public PuzzleLayout getCurrentLayout() {
		return currentLayout;
	};
	
	public void scatter() {
		Map<Integer,PieceStatus> pieces = new HashMap<Integer,PieceStatus>();
		Map<Integer,List<Integer>> blocks = new HashMap<Integer,List<Integer>>();
		double marginX = view.getPieceWidth()/2;
		double marginY = view.getPieceHeight()/2;
		for (int i=0; i<structure.getPieceCount();i++) {
			Random rX = new Random();
			double randomX = marginX + (view.getWorkspaceWidth()-marginX - marginX) * rX.nextDouble();
			Random rY = new Random();
			double randomY = marginY + (view.getWorkspaceHeight()-marginY - marginY) * rY.nextDouble();
			PieceStatus pieceStatus = new PieceStatus(i,new Point(randomX,randomY));
			pieceStatus.setBlock(i);
			pieces.put(i, pieceStatus);
			List<Integer> list = new ArrayList<Integer>();
			list.add(i);
			blocks.put(i, list);
		}
		currentLayout= new PuzzleLayout(pieces,blocks);
	};
	
	public void restore() {
		Collections.reverseOrder();
	};
	
	public java.lang.Integer selectPiece(Point point) {
		List<Integer> list = new ArrayList<Integer>();
		for (Integer key : currentLayout.getPieces().keySet()) {
			Point pos = currentLayout.getPieces().get(key).getPosition();
			if (Math.abs(point.getX()-pos.getX())<=getSelectRadius() &&
					Math.abs(point.getY()-pos.getY())<=getSelectRadius()) {
				list.add(currentLayout.getPieces().get(key).getBlock());
			}
		}
		if (list.size()==0) {
			return null;
		}
		return Collections.max(list,null);
	};
		
	public Map<Integer,List<Integer>> mergePieces(int id1, int id2, 
			Map<Integer,List<Integer>> blocks) {
		int blockId1 = currentLayout.getBlock(id1);
		int blockId2 = currentLayout.getBlock(id2);
		Map<Integer,List<Integer>> b = blocks;
		if (blockId1>blockId2) {
			b.get(blockId1).addAll(b.get(blockId2));
			b.remove(blockId2);
		} else {
			b.get(blockId2).addAll(b.get(blockId1));
			b.remove(blockId1);
		}
		return b;
	}
	
	public boolean outOfRange(Point point) {
		double marginToBo = structure.getPieceHeight()/2;
		double marginLeRi = structure.getPieceWidth()/2;
		double workspaceWidht = view.getWorkspaceWidth();
		double workspaceHeight = view.getWorkspaceHeight();
		if (point.getX()<marginLeRi || point.getY()<marginToBo 
				|| point.getX()>workspaceWidht-marginLeRi 
				|| point.getY()>workspaceHeight-marginToBo) {
			return true;
		}
		return false;
	}
	
	public PuzzleLayout connect(int movedId, Point point) throws MPJPException {
		if (outOfRange(point) || movedId<0 || movedId>structure.getPieceCount()-1) {
			System.out.print("out of range");
			throw new MPJPException();
		}
		double radius=getSelectRadius();
		List<Direction> dir = new ArrayList<Direction>();
		dir.add(NORTH);
		dir.add(WEST);
		dir.add(SOUTH);
		dir.add(EAST);
		for(Direction d: dir) {
			if(structure.getPieceFacing(d, movedId)!=null) {
				int facingId = structure.getPieceFacing(d, movedId);
				Point facingP = currentLayout.getPieceStatus(facingId).getPosition();
				double distance = Math.sqrt(Math.pow(facingP.getX() - point.getX(), 2) + Math.pow(facingP.getY() - point.getY(), 2));
				if(distance<radius) {
					Point movingPoint = new Point();
					if(d==NORTH) {
						movingPoint = structure.getPieceCenterFacing(SOUTH, facingP);
					} else if (d==WEST) {
						movingPoint = structure.getPieceCenterFacing(EAST, facingP);
					} else if (d==SOUTH) {
						movingPoint = structure.getPieceCenterFacing(NORTH, facingP);
					} else {
						movingPoint = structure.getPieceCenterFacing(WEST, facingP);
					}
					double moveX=movingPoint.getX()-currentLayout.getPieceStatus(movedId).getX();
					double moveY=movingPoint.getY()-currentLayout.getPieceStatus(movedId).getY();
					
					//move block
					Map<Integer,PieceStatus> pieces = currentLayout.getPieces();
					Map<Integer,List<Integer>> blocks = currentLayout.getBlocks();
					for(int i:blocks.get(currentLayout.getBlock(movedId))){
						Point oPosition = pieces.get(i).getPosition();
						Point nPosition = new Point(oPosition.getX()+moveX,oPosition.getY()+moveY);
						if(outOfRange(nPosition)) {
							throw new MPJPException();
						}
						pieces.get(i).setPosition(nPosition);
					}
					
					//change block indices
					blocks = mergePieces(movedId,facingId,blocks);
					currentLayout = new PuzzleLayout(pieces,blocks);
					return currentLayout;
				}
			}
		}
		double moveX=point.getX()-currentLayout.getPieceStatus(movedId).getX();
		double moveY=point.getY()-currentLayout.getPieceStatus(movedId).getY();
		
		//move block
		Map<Integer,PieceStatus> pieces = currentLayout.getPieces();
		Map<Integer,List<Integer>> blocks = currentLayout.getBlocks();
		for(int i:blocks.get(currentLayout.getBlock(movedId))){
			Point oPosition = pieces.get(i).getPosition();
			Point nPosition = new Point(oPosition.getX()+moveX,oPosition.getY()+moveY);
			if(outOfRange(nPosition)) {
				throw new MPJPException();
			}
			pieces.get(i).setPosition(nPosition);
		}
		currentLayout = new PuzzleLayout(pieces,blocks);
		return currentLayout;
	};
	
	public boolean nextTo (int id1,int id2) {
		if (Math.abs(id1-id2)==1 || Math.abs(id1-id2)==info.getColumns()) {
			return true;
		}
		return false;
	};
	
	public PuzzleStructure getPuzzleStructure() {
		return structure;
	};
	
}
