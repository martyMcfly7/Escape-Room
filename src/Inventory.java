// created by Victor Ortiz & Diana Nguyen  CS142  11/14/19
// purpose: demonstrate your ability to work in a team while creating an Object Oriented program in Java.

// required to use an ArrayList
import java.util.ArrayList;
import java.util.List;

// stores data about items in player's inventory and all possible items
public class Inventory extends Item {

	// fields (member variables)
	private List<Item> inventory = new ArrayList<Item>();
	private List<Item> gameItems = new ArrayList<Item>();
	
	// constructors for the Inventory class
	public Inventory() {
		// constants for each item: name, symbol, description & item info for user
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
		// add all items to gameItems list
		gameItems.add(chisel);
		gameItems.add(newspaper);
		gameItems.add(hammer);
		gameItems.add(flashlight);
		gameItems.add(redKey);
		gameItems.add(silverKey);
		gameItems.add(blueKey);
		gameItems.add(goldKey);
	}
	
	// method to add an Item to player's inventory
	public void addItemToInventory(Item item) {
		inventory.add(item);
	}
	
	// method to determine if a specific item is in the inventory List
	public boolean itemInPlayerInventory(String itemSymbol) {
        for (int item = 0; item < inventory.size(); item++) {
        	// if item's symbol from inventory equals parameter string
            if (inventory.get(item).getSymbol().equals(itemSymbol)) {
                return true;
            }
        }
        return false;
    }
	
	// method to determine if a specific item is in the gameItems List
	public boolean itemInGameItems(String itemSymbol) {
        for (int item = 0; item < gameItems.size(); item++) {
         	// if item's symbol from gameItems equals parameter string
            if (gameItems.get(item).getSymbol().equals(itemSymbol)) {
                return true;
            }
        }
        return false;
    }
	
	// method to get a specific item from the gameItems List
	public Item getSpecificGameItem(String itemSymbol) {
		Item newItem = new Item();
		for (Item item: gameItems) {
			// if item's symbol equals parameter string
			if (item.getSymbol().equals(itemSymbol)) {
				newItem = item;
			}
		}
		return newItem;
	}
	
	// inventory get/set methods
	public List<Item> getInventory() {
		return inventory;
	}
	
	public void setInventory(List<Item> newInventory) {
		inventory = newInventory;
	}
	
	// method to display all items in the inventory
	public String displayAllItems() {
		String itemsInInventory = "\tItem:\t\tDescription:\n";
		// for every item in inventory, get the following...
		for (Item item: inventory) {
			itemsInInventory += "\t" + item.getName() + "\t" + item.getDescription() + "\n";
		}
		return itemsInInventory;
	}
	
}