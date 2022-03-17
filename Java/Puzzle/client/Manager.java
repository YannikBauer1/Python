package mpjp.client;

import java.io.IOException;
import java.util.Map;

import mpjp.game.WorkspacePool;
import mpjp.shared.MPJPException;
import mpjp.shared.PuzzleInfo;
import mpjp.shared.PuzzleLayout;
import mpjp.shared.PuzzleSelectInfo;
import mpjp.shared.PuzzleView;
import mpjp.shared.geom.Point;

public interface Manager {

	void reset();

	java.util.Set<java.lang.String> getAvailableCuttings();

	java.util.Set<java.lang.String> getAvailableImages() throws IOException;

	WorkspacePool getWorkspacePool();

	Map<String, PuzzleSelectInfo> getAvailableWorkspaces() 
			throws IOException, MPJPException;

	java.lang.String createWorkspace(PuzzleInfo info) 
			throws MPJPException, IOException;

	java.lang.Integer selectPiece(java.lang.String workspaceId, Point point)
			throws MPJPException, IOException;

	PuzzleLayout connect(java.lang.String workspaceId, int pieceId, Point point)
			throws MPJPException, IOException;

	PuzzleView getPuzzleView(java.lang.String workspaceId) 
			throws MPJPException, IOException;

	PuzzleLayout getCurrentLayout(java.lang.String workspaceId)
			throws MPJPException, IOException;

}