/* Created by Victor Ortiz & Diana Nguyen  CS142  11/14/19
 * Purpose: Demonstrate your ability to work in a team while creating an Object Oriented program in Java.
 */
import java.util.ArrayList;
import java.util.List;
// Stores data of all game items and items in player's inventory
public class Inventory extends Item {

	// Fields (member variables)
	private List<Item> inventory = new ArrayList<Item>(); // Player's inventory
	private List<Item> gameItems = new ArrayList<Item>(); // All the items in the game
	// Constructor
	public Inventory() {
		// Constants for each item: name, symbol, description & item info for user
		final Item chisel = new Item("Chisel", "ch", "\tA sharp tool for cutting or carving.", 
				"This CHISEL will be useful for breaking and cutting through objects.");
		final Item newspaper = new Item("Newspaper", "np", "A Local newspaper with recent events.", 
				"Skimming the NEWSPAPER you read the headline:\n\tANOTHER MISSING CIVILIAN. KILLER KIDNAPPER STRIKES AGAIN!");
		final Item hammer = new Item("Hammer", "hm", "\tA heavy ball-peen hammer.", 
				"This HAMMER will make easy work breaking through hard materials.");
		final Item flashlight = new Item("Flashlight", "fl", "A flashlight to help you see better.", 
				"Using the FLASHLIGHT you can now see in the dark areas of each room.");
		final Item redKey = new Item("Red Key", "rk", "\tA key for a matching RED LOCK.",
				"You use the RED KEY on the lock attached to your hands. You have been freed of the handcuffs!");
		final Item silverKey = new Item("Silver Key", "sk", "A key for a matching SILVER LOCK.", 
				"Use the SILVER KEY on the matching lock.");
		final Item blueKey = new Item("Blue Key", "bk", "A key for a matching BLUE LOCK.", 
				"Use the BLUE KEY on the matching lock.");
		final Item goldKey = new Item("Gold Key", "gk", "A key for a matching GOLD LOCK.", 
				"Use the GOLD KEY on the matching lock.");
		gameItems.add(chisel);
		gameItems.add(newspaper);
		gameItems.add(hammer);
		gameItems.add(flashlight);
		gameItems.add(redKey);
		gameItems.add(silverKey);
		gameItems.add(blueKey);
		gameItems.add(goldKey);
	}
	
	// Add an Item to player's inventory
	public void addItemToInventory(Item item) {
		inventory.add(item);
	}
	
	// Determine if a specific item is in the inventory List
	public boolean isItemInPlayerInventory(String itemSymbol) {
        for (int item = 0; item < inventory.size(); item++) {
        	// If item's symbol from inventory equals parameter string
            if (inventory.get(item).getSymbol().equals(itemSymbol)) {
                return true;
            }
        }
        return false;
    }
	
	// Determine if a mapString is an item in the gameItems List
	public boolean isItemInGameItems(String itemSymbol) {
        for (int item = 0; item < gameItems.size(); item++) {
            if (gameItems.get(item).getSymbol().equals(itemSymbol)) {
                return true;
            }
        }
        return false;
    }
	
	// Get a specific item from the gameItems List
	public Item getSpecificGameItem(String itemSymbol) {
		Item newItem = new Item();
		for (Item item: gameItems) {
			if (item.getSymbol().equals(itemSymbol)) {
				newItem = item;
			}
		}
		return newItem;
	}
	
	// Inventory get/set methods
	public List<Item> getInventory() {
		return inventory;
	}
	
	public void setInventory(List<Item> newInventory) {
		inventory = newInventory;
	}
	
	// Display all items in the player's inventory as formatted string
	public String displayAllItems() {
		String itemsInInventory = "\tItem:\t\tDescription:\n";
		for (Item item: inventory) {
			itemsInInventory += "\t" + item.getName() + "\t" + item.getDescription() + "\n";
		}
		return itemsInInventory;
	}
}