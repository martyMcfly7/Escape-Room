/* Created by Victor Ortiz & Diana Nguyen  CS142  11/14/19
 * Purpose: Demonstrate your ability to work in a team while creating an Object Oriented program in Java.
 */
// Stores data for the current player of the game
public class Player {
	
	// Fields (member variables)
	private String playerName;
	// Player's position in gameMap[row][column]
	private int xPosition;
	private int yPosition;
	
	// Constructors
	public Player() {
		playerName = "";
		// Starting position for new game
		xPosition = 6;
		yPosition = 1;
	}
	
	// Overloaded constructor with parameters for each field
	public Player(String newPlayerName, int newXPosition, int newYPosition) {
		playerName = newPlayerName;
		xPosition = newXPosition;
		yPosition = newYPosition;
	}
	
	// Player name get/set methods
	public String getName() {
		return playerName;
	}
	
	public void setName(String newPlayerName) {
		playerName = newPlayerName;
	}
	
	// Player position (x, y) get/set methods
	public int getXPosition() {
		return xPosition;
	}
	
	public void setXPosition(int newXPosition) {
		xPosition = newXPosition;
	}
	
	public int getYPosition() {
		return yPosition;
	}
	
	public void setYPosition(int newYPosition) {
		yPosition = newYPosition;
	}
	
	// Display returning player stats as formatted string
	public String displayStats(Inventory inventory) {
		return "\nWELCOME BACK!\n\nPlayer Stats -\n\tName: " + getName() +
				"\nItems in Inventory -\n" + inventory.displayAllItems();
	}
}