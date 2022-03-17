package mpjp.client;

import java.io.IOException;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import mpjp.shared.MPJPException;
import mpjp.shared.PuzzleInfo;
import mpjp.shared.PuzzleLayout;
import mpjp.shared.PuzzleSelectInfo;
import mpjp.shared.PuzzleView;
import mpjp.shared.geom.Point;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("manager")
public interface ManagerService extends RemoteService {
	
	void addClient(String client, String workspaceId);
	
	String getWorkspaceIdFromName(String gameName, String password);
	
	java.util.Set<java.lang.String> getAvailableCuttings();

	java.util.Set<java.lang.String> getAvailableImages() throws IOException;
	
	java.util.List<Double> getImageWidhtHeight(String image);

	Map<String, PuzzleSelectInfo> getAvailableWorkspaces() 
			throws IOException, MPJPException;

	java.lang.String createWorkspace(PuzzleInfo info, String gameName, 
			String password) throws MPJPException, IOException;

	java.lang.Integer selectPiece(java.lang.String workspaceId, Point point)
			throws MPJPException, IOException;

	PuzzleLayout connect(java.lang.String workspaceId, int pieceId, Point point)
			throws MPJPException, IOException;

	PuzzleView getPuzzleView(java.lang.String workspaceId) 
			throws MPJPException, IOException;

	PuzzleLayout getCurrentLayout(java.lang.String workspaceId)
			throws MPJPException, IOException;
}
