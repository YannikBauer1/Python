package mpjp.server;

import mpjp.client.ManagerService;
import mpjp.game.Images;
import mpjp.game.WorkspacePool;
import mpjp.game.cuttings.CuttingFactoryImplementation;
import mpjp.shared.MPJPException;
import mpjp.shared.PuzzleInfo;
import mpjp.shared.PuzzleLayout;
import mpjp.shared.PuzzleSelectInfo;
import mpjp.shared.PuzzleView;
import mpjp.shared.geom.Point;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
public class ManagerServiceImpl extends RemoteServiceServlet 
implements ManagerService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	WorkspacePool workspacePool = new WorkspacePool();
	private static ManagerServiceImpl instance = new ManagerServiceImpl();
	Images images = new Images();
	CuttingFactoryImplementation factory = new CuttingFactoryImplementation();

	public ManagerServiceImpl(){}
	
	@Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        File base = new File(context.getRealPath("/"));

        File imagesdir = new File(base,"WEB-INF/classes/mpjp/resources");
        File poolDir = new File(base,"WEB-INF/pool");

        if(!poolDir.exists())
            poolDir.mkdir();

        Images.setImageDirectory(imagesdir);
        WorkspacePool.setPoolDiretory(poolDir);
    }
	
	public void addClient(String name, String workspaceId) {
		try {
			workspacePool.addClient(name, workspaceId);
		} catch (ClassNotFoundException | MPJPException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getWorkspaceIdFromName(String gameName, String password) {
		Map<String, PuzzleSelectInfo> map = new HashMap<String,PuzzleSelectInfo>();
		try {
			map = workspacePool.getAvailableWorkspaces();
		} catch (ClassNotFoundException | IOException | MPJPException e) {
			e.printStackTrace();
		}
		for (String i:map.keySet()) {
			if(map.get(i).getGameName().equals(gameName) &&
					map.get(i).getPassword().equals(password)) {
				return i;
			}
		}
		return "";
	}
	
	public static ManagerServiceImpl getInstance() {
		return instance;
	};
	
	public void reset() {
		instance = new ManagerServiceImpl();
	};
	
	public java.util.Set<java.lang.String> getAvailableCuttings() {
		return factory.getAvaliableCuttings();
	};
	
	public java.util.Set<java.lang.String> getAvailableImages() 
			throws IOException {
		return images.getAvailableImages();
	};
	
	public java.util.List<Double> getImageWidhtHeight(String image) {
		BufferedImage bimg = null;
		try {
			bimg = ImageIO.read(new File(Images.getImageDirectory().getPath()+"\\"+image));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	double width = bimg.getWidth();
    	double height = bimg.getHeight();
    	java.util.List<Double> set = new ArrayList<Double>();
    	set.add(width);
    	set.add(height);
		return set;
	}
	
	public WorkspacePool getWorkspacePool() {
		return workspacePool;
	}
	
	public Map<String,PuzzleSelectInfo> getAvailableWorkspaces() 
			throws IOException, MPJPException{
		try {
			return workspacePool.getAvailableWorkspaces();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	};
	
	public java.lang.String createWorkspace(PuzzleInfo info, String gameName, String password) 
			throws MPJPException, IOException {
		return workspacePool.createWorkspace(info, gameName, password);
	};
	
	public java.lang.Integer selectPiece(java.lang.String workspaceId, 
			Point point) throws MPJPException, IOException {
		try {
			return workspacePool.getWorkspace(workspaceId).selectPiece(point);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	};
	
	public PuzzleLayout connect(java.lang.String workspaceId, int pieceId, 
			Point point) throws MPJPException, IOException {
		try {
			return workspacePool.getWorkspace(workspaceId).connect(pieceId, point);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	};
	
	public PuzzleView getPuzzleView(java.lang.String workspaceId) 
			throws MPJPException, IOException {
		try {
			return workspacePool.getWorkspace(workspaceId).getPuzzleView();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	};
	
	public PuzzleLayout getCurrentLayout(java.lang.String workspaceId) 
			throws MPJPException, IOException {
		try {
			return workspacePool.getWorkspace(workspaceId).getCurrentLayout();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
