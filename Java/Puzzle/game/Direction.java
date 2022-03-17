package mpjp.game;

public enum Direction implements java.io.Serializable{
	EAST, NORTH, SOUTH, WEST;
	class EnumDesc<E extends Enum<E>>{
		
	}
	/*
	public static Direction valueOf​(java.lang.String name) {
		Direction dir = null;
		switch (name) {
		case "EAST":
			dir=EAST;
			break;
		case "SOUTH":
			dir=NORTH;
			break;
		case "WEST":
			dir=SOUTH;
			break;
		case "NORTH":
			dir=WEST;
			break;
		}
		return dir;
		
	}*/
	
	int getSignalX() {
		int x = 0;
		switch (this.toString()) {
		case "EAST":
			x=1;
			break;
		case "SOUTH":
			x=0;
			break;
		case "WEST":
			x=-1;
			break;
		case "NORTH":
			x=0;
			break;
		}
		return x;
	};
	
	int getSignalY() {
		int x = 0;
		switch (this.toString()) {
		case "EAST":
			x=0;
			break;
		case "SOUTH":
			x=1;
			break;
		case "WEST":
			x=0;
			break;
		case "NORTH":
			x=-1;
			break;
		}
		return x;
	};
}
