package mpjp.client;

import java.io.IOException;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

import mpjp.shared.MPJPException;
import mpjp.shared.PuzzleInfo;
import mpjp.shared.PuzzleLayout;
import mpjp.shared.PuzzleSelectInfo;
import mpjp.shared.PuzzleView;
import mpjp.shared.geom.Point;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ManagerServiceAsync {

	void getWorkspaceIdFromName(String gameName, String password, 
			AsyncCallback<String> callback);
	
	void addClient(String client, String workspaceId, 
			AsyncCallback<Void> callback);
	
	void getAvailableCuttings(
			AsyncCallback<java.util.Set<java.lang.String>> callback);

	void getAvailableImages(
			AsyncCallback<java.util.Set<java.lang.String>> callback) 
					throws IOException;
	
	void getImageWidhtHeight(String image, 
			AsyncCallback<java.util.List<Double>> callback);

	void getAvailableWorkspaces(
			AsyncCallback<Map<String, PuzzleSelectInfo>> callback) 
					throws IOException, MPJPException;

	void createWorkspace( PuzzleInfo info, String gameName, 
			String password, AsyncCallback<java.lang.String> callback) 
					throws MPJPException, IOException;

	void selectPiece(java.lang.String workspaceId, 
			Point point, AsyncCallback<java.lang.Integer> callback)
					throws MPJPException, IOException;

	void connect(java.lang.String workspaceId, int pieceId, 
			Point point, AsyncCallback<PuzzleLayout> callback) 
					throws MPJPException, IOException;

	void getPuzzleView(
			java.lang.String workspaceId, AsyncCallback<PuzzleView> callback) 
					throws MPJPException, IOException;

	void getCurrentLayout(
			java.lang.String workspaceId, AsyncCallback<PuzzleLayout> callback)
					throws MPJPException, IOException;
}
