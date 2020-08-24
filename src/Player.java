// created by Victor Ortiz & Diana Nguyen  CS142  11/14/19
// purpose: demonstrate your ability to work in a team while creating an Object Oriented program in Java.

// stores data for the current player of the game
public class Player {
	
	// fields (member variables)
	private String playerName;
	// this is the player position in roomMap array (x,y)
	private int xPosition;
	private int yPosition;
	
	// constructors for the Player class
	public Player() {
		playerName = "";
		// starting position within jail cell
		xPosition = 6;
		yPosition = 1;
	}
	
	// overloaded constructor with parameters for each field
	public Player(String newPlayerName, int newXPosition, int newYPosition) {
		playerName = newPlayerName;
		xPosition = newXPosition;
		yPosition = newYPosition;
	}
	
	// player name get/set methods
	public String getName() {
		return playerName;
	}
	
	public void setName(String newPlayerName) {
		playerName = newPlayerName;
	}
	
	// x coordinate position get/set methods
	public int getXPosition() {
		return xPosition;
	}
	
	public void setXPosition(int newXPosition) {
		xPosition = newXPosition;
	}
	
	// y coordinate position get/set methods
	public int getYPosition() {
		return yPosition;
	}
	
	public void setYPosition(int newYPosition) {
		yPosition = newYPosition;
	}
	
	// display basic information for returning player
	public String displayStats(Inventory inventory) {
		return "\nWELCOME BACK!\n\nPlayer Stats -\n\tName: " + getName() +
				"\nItems in Inventory -\n" + inventory.displayAllItems();
	}
	
}