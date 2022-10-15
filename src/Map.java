/* Created by Victor Ortiz & Diana Nguyen  CS142  11/14/19
 * Purpose: Demonstrate your ability to work in a team while creating an Object Oriented program in Java.
 */
// Stores data of walls, items, and room descriptions 
public class Map {
	
	// Game map (14 columns (x-axis) & 10 rows (y-axis))
	private final String[][] gameMap = {
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
	/* Map symbols:
	 * gameMap[1][6] == [y-axis][x-axis]: starting position for new game
	 * "|" == wall boundaries, single letter == room description,
	 * double letter == item to pickup/item to interact with
	 * "%" == stairs, "~" == door, "ed" == end of game
	 */
	// Fields (member variables)
	private String roomString;
	
	// Constants to know dimensions of gameMap
	private final int xLength = gameMap[0].length;
	private final int yLength = gameMap.length;
	
	// Room string get/set methods
	public String getRoomString() {
		return roomString;
	}
	
	public void setRoomString(String newRoomString) {
		roomString = newRoomString;
	}
	
	// Map get method
	public String[][] getMap() {
		return gameMap;
	}
	
	// Get dimensions of map
	public int getXLength() {
		return xLength;
	}
	
	public int getYLength() {
		return yLength;
	}
	
	// Update player positions (must pre-increment/pre-decrement)
	public int goNorth(int newYPosition) {
		return --newYPosition;
	}
	
	public int goEast(int newXPosition) {
		return ++newXPosition;
	}
	
	public int goSouth(int newYPosition) {
		return ++newYPosition;
	}
	
	public int goWest(int newXPosition) {
		return --newXPosition;
	}
	
	// Return string from the map at specified (x, y) position
	public String returnMapString(int xPosition, int yPosition) {
		String item = gameMap[yPosition][xPosition]; // gameMap[row][column]
		return item;
	}
}