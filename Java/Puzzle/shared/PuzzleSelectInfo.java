package mpjp.shared;

public class PuzzleSelectInfo extends PuzzleInfo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int percentageSolved;
	private java.util.Date start;
	private String gameName;
	private String password;
	
	public PuzzleSelectInfo() {};
	
	public PuzzleSelectInfo(PuzzleInfo info, java.util.Date start, int percentageSolved, String gameName, String password) {
		this.percentageSolved = percentageSolved;
		this.start = start;
		this.gameName=gameName;
		this.password=password;
	}
	public String getGameName() {
		return gameName;
	}
	public String getPassword() {
		return password;
	}
	public int getPercentageSolved() {
		return percentageSolved;
	}
	
	public java.util.Date getStart() {
		return start;
	}
}
