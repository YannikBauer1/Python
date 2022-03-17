package mpjp.game;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Map;
import java.util.Set;

import mpjp.shared.MPJPException;
import mpjp.shared.PuzzleInfo;
import mpjp.shared.PuzzleLayout;
import mpjp.shared.PuzzleSelectInfo;
import mpjp.shared.PuzzleView;
import mpjp.shared.geom.Point;
import mpjp.game.cuttings.CuttingFactoryImplementation;

public class Manager extends java.lang.Object implements java.io.Serializable {
	
	WorkspacePool workspacePool = new WorkspacePool();
	private static Manager instance = new Manager();
	Images images = new Images();
	CuttingFactoryImplementation factory = new CuttingFactoryImplementation();
	
	private Manager(){}
	
	public static Manager getInstance() {
		return instance;
	};
	
	public void reset() {
		instance = new Manager();
	};
	
	public java.util.Set<java.lang.String> getAvailableCuttings() {
		return factory.getAvaliableCuttings();
	};
	
	public java.util.Set<java.lang.String> getAvailableImages() throws IOException {
		return images.getAvailableImages();
	};
	
	public WorkspacePool getWorkspacePool() {
		return workspacePool;
	}
	
	public Map<String,PuzzleSelectInfo> getAvailableWorkspaces() throws ClassNotFoundException, IOException, MPJPException{
		return workspacePool.getAvailableWorkspaces();
	};
	
	public java.lang.String createWorkspace​(PuzzleInfo info) throws MPJPException, IOException {
		return workspacePool.createWorkspace​(info);
	};
	
	public java.lang.Integer selectPiece​(java.lang.String workspaceId, Point point) throws MPJPException, ClassNotFoundException, IOException {
		return workspacePool.getWorkspace​(workspaceId).selectPiece​(point);
	};
	
	public PuzzleLayout connect​(java.lang.String workspaceId, int pieceId, Point point) throws MPJPException, ClassNotFoundException, IOException {
		return workspacePool.getWorkspace​(workspaceId).connect​(pieceId, point);
	};
	
	public PuzzleView getPuzzleView​(java.lang.String workspaceId) throws MPJPException, ClassNotFoundException, IOException {
		return workspacePool.getWorkspace​(workspaceId).getPuzzleView();
	};
	
	public PuzzleLayout getCurrentLayout​(java.lang.String workspaceId) throws MPJPException, ClassNotFoundException, IOException {
		return workspacePool.getWorkspace​(workspaceId).getCurrentLayout();
	};
}
