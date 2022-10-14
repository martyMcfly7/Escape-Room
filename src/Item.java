/* Created by Victor Ortiz & Diana Nguyen  CS142  11/14/19
 * Purpose: Demonstrate your ability to work in a team while creating an Object Oriented program in Java.
 */
// Stores data of items a player can pick up throughout the game
public class Item {
	
	// Fields (member variables)
	private String name;
	private String symbol;
	private String description;
	private String itemInfo;
	// Constructor
	public Item() {
		name = "";
		symbol = "";
		description = "";
		itemInfo = "";
	}
	
	// Overloaded constructor with parameters for each field
	public Item(String newName, String newSymbol, String newDescription, String newItemInfo) {
		name = newName;
		symbol = newSymbol;
		description = newDescription;
		itemInfo = newItemInfo;
	}
	
	// Item name get/set methods
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	// item symbol get/set methods
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String newSymbol) {
		symbol = newSymbol;
	}
	
	// Item description get/set methods
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String newDescription) {
		description = newDescription;
	}
	
	// Item information get/set methods
	public String getItemInfo() {
		return itemInfo;
	}
	
	public void setItemInfo(String newItemInfo) {
		itemInfo = newItemInfo;
	}
	
	// display all item fields as formatted string
	public String displayItemInfo() {
		String item = name + ", " + symbol + ", " + description + ", " + itemInfo;
		return item;
	}
}