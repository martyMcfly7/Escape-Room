// created by Victor Ortiz & Diana Nguyen  CS142  11/14/19
// purpose: demonstrate your ability to work in a team while creating an Object Oriented program in Java.

// stores data about locations, items & rooms within the game map
public class Map {
	
	// game map with rooms, items, doors, start & end locations
	private final String[][] roomMap = { // 14 columns (x) & 10 rows (y)
		{"|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|"},
		{"|", "|", "|", "|", "|", "|", "r", " ","ch", "|", "|", "|", "|", "|"},
		{"|", "rk"," ","cw","sk", "|", "r", "r", "r", "|", "|", " ","tv", "|"},
		{"|", " ", " ", "|", "|", "|","cb","cb","cb", "|", "|", " ", " ", "|"},
		{"|", "g", " ", "|", "|", "|", "o", "o", "o", "%","%b", "b","co", "|"},
		{"|","~g", "|", "|", "|", "|", " ","hm", " ", "|", "|","bl", "|", "|"},
		{"|", "y", " ","ds", "y","~y", "o", "o", " ", "|", "|", "p", " ", "|"},
		{"|", " ", " ", " ", " ", "|", "|","gl", "|", "|", "|", " ","bd", "|"},
		{"|", " ","ct", " ", " ", "|", "|","ed", "|", "|", "|", " ","bt", "|"},
		{"|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|"}
	};
	//  map symbols:
	//  |: wall boundaries, st: start point, ch: chisel, cb: cell bars, ed: end point,
	//  #: random items, ~: unlocked doors, bd: bed, ds: desk, cw: chip wall, %: stairs

	// fields (member variables)
	private String roomDescription;
	private String roomString;
	
	// constants to know dimensions of the roomMap
	private final int xLength = roomMap[0].length;
	private final int yLength = roomMap.length;
	
	// roomDescription get/set methods
	public String getRoomDescription() {
		return roomDescription;
	}
	
	public void setRoomDescription(String newRoomDescription) {
		roomDescription = newRoomDescription;
	}
	
	// roomString get/set methods
	public String getRoomString() {
		return roomString;
	}
	
	public void setRoomString(String newRoomString) {
		roomString = newRoomString;
	}
	
	// map get method
	public String[][] getMap() {
		return roomMap;
	}
	
	// get dimensions of map
	public int getXLength() {
		return xLength;
	}
	
	public int getYLength() {
		return yLength;
	}
	
	// methods to update player position
	public int goNorth(int newYPosition) {
		// pre-decrement position
		return --newYPosition;
	}
	
	public int goEast(int newXPosition) {
		// pre-increment position
		return ++newXPosition;
	}
	
	public int goSouth(int newYPosition) {
		// pre-increment position
		return ++newYPosition;
	}
	
	public int goWest(int newXPosition) {
		// pre-decrement position
		return --newXPosition;
	}
	
	// method to return string from the map at a specified (x, y) position
	public String returnMapString(int xPosition, int yPosition) {
		String item = roomMap[yPosition][xPosition]; // roomMap[row][column]
		return item;
	}
	
}