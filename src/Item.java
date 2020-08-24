// created by Victor Ortiz & Diana Nguyen  CS142  11/14/19
// purpose: demonstrate your ability to work in a team while creating an Object Oriented program in Java.

// stores data items player can pick up throughout the game
public class Item {
	
	// fields (member variables)
	private String name;
	private String symbol;
	private String description;
	private String itemInfo;
	
	// constructors for the Item class
	public Item() {
		name = "";
		symbol = "";
		description = "";
		itemInfo = "";
	}
	
	// overloaded constructor with parameters for each field
	public Item(String newName, String newSymbol, String newDescription, String newItemInfo) {
		name = newName;
		symbol = newSymbol;
		description = newDescription;
		itemInfo = newItemInfo;
	}
	
	// item name get/set methods
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	// symbol for item get/set methods
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String newSymbol) {
		symbol = newSymbol;
	}
	
	// symbol for item get/set methods
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String newDescription) {
		description = newDescription;
	}
	
	// symbol for item get/set methods
	public String getItemInfo() {
		return itemInfo;
	}
	
	public void setItemInfo(String newItemInfo) {
		itemInfo = newItemInfo;
	}
	
	// display all fields of item as formatted string
	public String displayItemInfo() {
		String item = name + ", " + symbol + ", " + description + ", " + itemInfo;
		return item;
	}
	
}