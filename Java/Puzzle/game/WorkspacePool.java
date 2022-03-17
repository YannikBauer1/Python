package mpjp.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.FileOutputStream;


import mpjp.shared.MPJPException;
import mpjp.shared.PuzzleSelectInfo;
import mpjp.shared.PuzzleInfo;

public class WorkspacePool extends java.lang.Object {

	static java.lang.String SERIALIAZTION_SUFFIX = ".ser";
	private static java.io.File poolDirectory;
	
	public WorkspacePool() {};
	
	public void addClient(String name, String workspaceId) 
			throws ClassNotFoundException, MPJPException, IOException {
		Workspace work = recover(workspaceId);
		work.addClient(name);
		backup(workspaceId,work);
	}
	
	public static java.io.File getPoolDirectory() {
		return poolDirectory;
	};
	
	public static void setPoolDiretory(java.io.File poolDirectory1) {
		poolDirectory = poolDirectory1;
	};
	
	public static void setPoolDiretory(java.lang.String pathname) {
		poolDirectory = new File(pathname);
	};
	
	public java.lang.String createWorkspace(PuzzleInfo info, String gameName, 
			String password) throws MPJPException, IOException {
		Workspace work = new Workspace(info, gameName, password);
		backup(work.getId(),work);
		return work.getId();
	};
	
	public Workspace getWorkspace(java.lang.String id) 
			throws MPJPException, IOException, ClassNotFoundException {
		return recover(id);
	};
	
	public Map<String,PuzzleSelectInfo> getAvailableWorkspaces() 
			throws IOException, ClassNotFoundException, MPJPException {
		String[] pathnames = poolDirectory.list();
		Map<String,PuzzleSelectInfo> availableWorkspaces = new HashMap<String,PuzzleSelectInfo>();
		String string="";
		for(String s:pathnames) {
			string = s.split("\\.")[0];
			Workspace work = getWorkspace(string);
			if(work.getPuzzleSelectInfo().getPercentageSolved()!=100) {
				availableWorkspaces.put(work.getId(), work.getPuzzleSelectInfo());
			}
		}
		return availableWorkspaces;
	};
	
	public java.io.File getFile(java.lang.String workspaceId) {
		File[] pathnames = poolDirectory.listFiles();
		String s = workspaceId+SERIALIAZTION_SUFFIX;
        for (File f : pathnames) {
            if (f.getName().equals(s)) {
            	return f;
            }
        }
        File f = new File(poolDirectory,s);
        return f;
	};
	
	public void backup(java.lang.String workspaceId, Workspace workspace) 
			throws MPJPException, IOException {
		String filename = workspaceId+SERIALIAZTION_SUFFIX;
		FileOutputStream f = new FileOutputStream(poolDirectory.getPath()+"\\"+filename);
        ObjectOutputStream out = new ObjectOutputStream(f);
        out.writeObject(workspace);
        out.close();
        f.close();
	};
	
	public Workspace recover(java.lang.String workspaceId) 
			throws MPJPException, IOException, ClassNotFoundException {
		File f = getFile(workspaceId);
		FileInputStream file = new FileInputStream(f);
        ObjectInputStream in = new ObjectInputStream(file);
        Workspace work = (Workspace)in.readObject();
        in.close();
        file.close();
		return work;
	};
	
	void reset() {
		
	};
	
}
