/* Created by Victor Ortiz & Diana Nguyen  CS142  11/14/19
 * Purpose: Demonstrate your ability to work in a team while creating an Object Oriented program in Java.
 * Game description: ESCAPE ROOM GAME - 
 * Using the items you find along your journey, you need to figure out a way to freedom.
 */
import java.util.Scanner;

public class FinalProject {

	public static void main(String[] args) {
		// Create class instances
		Scanner keyboard = new Scanner(System.in);
		Player player = new Player();
		Inventory inventory = new Inventory();
		Map map = new Map();
		
		startGame(keyboard, player, map, inventory);
		// Main loop for game navigation
		boolean flag = true;
		while (flag) {
			int newPosition, positionToSet;
	    	System.out.println("\nPROMPT: What would you like to do now?");
	    	String inputFromUser = userNavigation(keyboard);
        	switch (inputFromUser) {
	        	case "north":
	        		newPosition = map.goNorth(player.getYPosition());
	        		positionToSet = moveYPosition(map, player, newPosition, keyboard, inventory);
	        		player.setYPosition(positionToSet);
	        		break;
	        	case "east":
	        		newPosition = map.goEast(player.getXPosition());
	        		positionToSet = moveXPosition(map, player, newPosition, keyboard, inventory);
	        		player.setXPosition(positionToSet);
	        		break;
	        	case "south":
	        		newPosition = map.goSouth(player.getYPosition());
	        		positionToSet = moveYPosition(map, player, newPosition, keyboard, inventory);
	        		player.setYPosition(positionToSet);
	        		break;
	        	case "west":
	        		newPosition = map.goWest(player.getXPosition());
	        		positionToSet = moveXPosition(map, player, newPosition, keyboard, inventory);
	        		player.setXPosition(positionToSet);
	        		break;
	        	case "info":
	        		System.out.println(getRoomDescription(map, player));
	        		break;
	        	case "inventory":
	        		itemInventory(inventory);
	        		break;
	        	case "help":
	        		System.out.println("\nINFO: Use the following commands to navigate within the game:\n\t" +  
	            			"use 'north', 'east', 'south', or 'west' to move around,\n\t" + 
	        				"'info' to display current room description\n\t" +
	            			"'inventory' to view current items in inventory,\n\t" +
	            			"'save' to save current game progress,\n\t" +
	            			"'restart' to reset to the beginning of the game,\n\t" +
	            			"or 'quit' to exit game & optionally save game progress.");
	        		break;
	        	case "restart":
	        		Player newPlayer = new Player();
	        		Inventory newInventory = new Inventory();
	        		player = newPlayer;
	        		inventory = newInventory;
	        		System.out.println("\nINFO: The game has been restarted.\n");
	        		startGame(keyboard, player, map, inventory);
	        		break;
	        	case "save":
	        		saveGame(player, map, inventory);
	        		break;
	        	case "quit":
	        		quitGame(keyboard, player, map, inventory);
	        		flag = false;
	        		break;
	        	default:
	        		System.out.println("\nERROR: '" + inputFromUser + "' is not a valid command.\n\tPlease try again.");
	        		break;
        	}
		}
    	keyboard.close();
	}
	
	// Introduce game, get user's name, or load game data
	private static void startGame(Scanner keyboard, Player player, Map map, Inventory inventory) {
		System.out.println("WELCOME TO THE ESCAPE ROOM GAME!\n\n" + 
				 "STORY: Using the items you find along your journey, you need to figure out a way to freedom.");
		boolean flag = true;
		while (flag) {
			System.out.println("\nPROMPT: Would you like to start a new game?\n\t" + 
					"If so, type 'new', or type 'load' to load a saved game:");
        	String inputFromUser = userNavigation(keyboard);
        	switch (inputFromUser) {
        		case "new":
        			newGame(keyboard, player);
	        		flag = false;
        			break;
        		case "load":
        			loadGame(player, map, inventory);
	        		flag = false;
        			break;
        		default:
	        		System.out.println("\nERROR: '" + inputFromUser + "' is not a valid command.\n\tPlease try again.");
        			break;
        	}
		}
	}
	
	// Start a new game (get user's name & introduce game story)
	private static void newGame(Scanner keyboard, Player player) {
		System.out.println("\nPROMPT: What is your name?");
	    player.setName(keyboard.nextLine());
		System.out.println("\nSTORY: You wake up in a locked cell, cold and confused. It's a bit hard to move your hands.\n\t" +
				"When you look down, you notice your hands are cuffed with an attached RED LOCK.\n\t" + 
				"You are not sure how you got here. The only thing you do know is that you need to find a way out!\n" +
				"\nROOM: In the cell you see the following -\n\t" + 
				"The bed you woke up at is in the northwest corner of the room.\n\t" +
				"A metal bar in the northeast corner of the room.\n\t" + 
				"To the south you see old, rusty cell bars that keep you confined in the cell.");
	}
	
	// Load game data from text file
	private static void loadGame(Player player, Map map, Inventory inventory) {
		TextFile textFile = new TextFile();
		textFile.Load(player, map, inventory);
		System.out.print(player.displayStats(inventory));
	}
	
	// Keyboard input for navigation
	private static String userNavigation(Scanner keyboard) {
    	String inputFromUser = keyboard.nextLine().trim().toLowerCase();
		return inputFromUser;
	}
	
	// Movement in Y-direction
	private static int moveYPosition(Map map, Player player, int newPosition, Scanner keyboard, Inventory inventory) {
		int currentPosition = player.getYPosition();
		// Determine if Y-direction has changed and interact with map
		String mapString = map.returnMapString(player.getXPosition(), newPosition);
		int positionToSet = mapInteraction(currentPosition, mapString, newPosition, keyboard, inventory, map);
		return positionToSet;
	}
	
	// Movement in X-direction
	private static int moveXPosition(Map map, Player player, int newPosition, Scanner keyboard, Inventory inventory) {
		int currentPosition = player.getXPosition();
		// Determine if X-direction has changed and interact with map
		String mapString = map.returnMapString(newPosition, player.getYPosition());
		int positionToSet = mapInteraction(currentPosition, mapString, newPosition, keyboard, inventory, map);
		return positionToSet;
	}
	
	// Player interaction with map: dialog, items, walls, doors, descriptions, etc.
	private static int mapInteraction(int currentPosition, String mapString, int newPosition, Scanner keyboard, Inventory inventory, Map map) {
		int positionToSet = 0;
		switch (mapString) {
			case "|":
				System.out.println("\nYou can’t move any further, there's a wall in the way.");
				// Don't change player's position (can't go through wall)
				positionToSet = currentPosition;
				break;
			case " ":
				System.out.println("\nYou moved one space.");
				positionToSet = newPosition;
				break;
			case "r":
				System.out.println("\nYou moved one space.");
				// Save room description (can be accessed when needed)
				map.setRoomString("r");
				positionToSet = newPosition;
				break;
			case "o":
				System.out.println("\nYou moved one space.");
				map.setRoomString("o");
				positionToSet = newPosition;
				break;
			case "y":
				System.out.println("\nYou moved one space.");
				map.setRoomString("y");
				positionToSet = newPosition;
				break;
			case "g":
				System.out.println("\nYou moved one space.");
				map.setRoomString("g");
				positionToSet = newPosition;
				break;
			case "b":
				System.out.println("\nYou moved one space.");
				map.setRoomString("b");
				positionToSet = newPosition;
				break;
			case "p":
				System.out.println("\nYou moved one space.");
				map.setRoomString("p");
				positionToSet = newPosition;
				break;
			case "cb":
				if (inventory.isItemInPlayerInventory("ch")) {
					System.out.println("\nSTORY: You sit down with your back against the cell bars.\n\t" + 
							"With limited hand mobility and hard work you successfully chisel the cell bars loose!\n\t" + 
							"You make your escape through the cell bars!");
					positionToSet = newPosition;
				}
				else {
					System.out.println("\nYou can't go any further! The cell bars are in the way!\n\t" + 
							"You need something to break out of the cell.");
					positionToSet = currentPosition;
				}
				break;
			case "%":
				System.out.println("\nYou get to a flight of stairs.\n\t" +
						"The basement is to the east and the main hallway is to the west.");
				positionToSet = newPosition;
				break;
			case "%b":
				System.out.println("\nYou reach the bottom of the flight of stairs.\n\t" +
						"The main hallway is to the west and the livingroom is to the east.");
				positionToSet = newPosition;
				break;
			case "co":
				System.out.println("\nYou come across a couch. You sit down and rest for a little bit.");
				positionToSet = newPosition;
				break;
			case "tv":
				System.out.println("\nYou see a TV. You try to turn it on but it doesn't work.");
				positionToSet = newPosition;
				break;
			case "bl":
				if (inventory.isItemInPlayerInventory("bk")){
					System.out.println("\nUsing the BLUE KEY you unlock the purple door!");
					positionToSet = newPosition;
				}
				else {
					System.out.println("\nYou don't have the correct key to unlock the purple door.");
					positionToSet = currentPosition;
				}
				break;
			case "bd":
				System.out.println("\nThe bed looks like it has been used recently.");
				positionToSet = newPosition;
				break;
			case "bt":
				System.out.println("\nYou look through the bedside table for anything useful.");
				pickUpItem(keyboard, inventory, "gk");
				positionToSet = newPosition;
				break;
			case "~y":
				System.out.println("\nYou pass through the yellow door.\n\t" + 
						"The office is to the west and the main hallway is to east.");
				positionToSet = newPosition;
				break;
			case "~g":
				System.out.println("\nYou pass through the green door.\n\t" + 
						"The storage closet is to the north and the office is to the south.");
				positionToSet = newPosition;
				break;
			case "ds":
				System.out.println("\nLooking across the desk you notice a newspaper.");
				pickUpItem(keyboard, inventory, "np");
				positionToSet = newPosition;
				break;
			case "ct":
				if (inventory.isItemInPlayerInventory("sk")) {
					System.out.println("\nUsing the SILVER KEY you unlock the chest!");
					pickUpItem(keyboard, inventory, "bk");
					positionToSet = newPosition;
				}
				else {
					System.out.println("\nThe chest is locked! You need a key that matches the SILVER LOCK.");
					positionToSet = currentPosition;
				}
				break;
			case "rk":
				System.out.println("\nYou come across a FLASHLIGHT and a RED KEY on a bookshelf in the northwest corner of the room.");
				pickUpItem(keyboard, inventory, "fl");
				pickUpItem(keyboard, inventory, "rk");
				positionToSet = newPosition;
				break;
			case "cw":
				if (inventory.isItemInPlayerInventory("hm")) {
					System.out.println("\nThe large crack in the wall looks suspicious.\n\t" +
							"Using your HAMMER you successfully break through the wall and find a SILVER KEY!");
					pickUpItem(keyboard, inventory, "sk");
					positionToSet = newPosition;
				}
				else {
					System.out.println("\nYou need something to break through the wall.");
					positionToSet = currentPosition;
				}
				break;
			case "gl":
				if (inventory.isItemInPlayerInventory("gk")) {
					System.out.println("\nSTORY: You insert the GOLD KEY into the matching lock.\n\t" +
							"The key turns and unlocks the black door!\n\t" +
							"Once through the black door, you find yourself outside.\n\t" + 
							"Looking around yourself, you notice you’re in the backyard to some house.\n\t" +
							"With your escape you rush out to get help.");
					positionToSet = newPosition;
				}
				else {
					System.out.println("\nYou can't go any further!\n\t" + 
							"You do not have the right key to open the black door!");
					positionToSet = currentPosition;
				}
				break;
			case "ed":
				System.out.println("\nYOU HAVE ESCAPED!!! CONGRATULATIONS!!!");
				positionToSet = newPosition;
				break;
			default:
				// no case matches, these are items to pick up
				pickUpItem(keyboard, inventory, mapString);
				positionToSet = newPosition;
				break;
		}
		return positionToSet;
	}
	
	// Gets the current roomString
	public static String getRoomDescription(Map map, Player player) {
		String RoomDescription = matchRoomString(map.getRoomString());
		return RoomDescription;
	}
	
	// Using the roomString gets the correct room description
	public static String matchRoomString(String mapString) {
		String roomDescription = "";
		switch (mapString) {
			case "r":
				roomDescription = "\nROOM: In the cell you see the following -\n\t" + 
						"In the nortwest corner is the bed you woke up in.\n\t" +
						"A metal bar in the northeast corner of the room.\n\t" + 
						"To the south you see old, rusty cell bars that keep you confined in the cell.";
				break;
			case "o":
				roomDescription = "\nROOM: In the main hallway, you notice the following -\n\t" + 
						"Stairs in the northeast corner of the room.\n\t" + 
						"A black door with a GOLD LOCK to the south of the room.\n\t" + 
						"An unlocked yellow door to the southwest of the room.";
				break;
			case "y":
				roomDescription = "\nROOM: You look around the office and notice the following -\n\t" + 
						"A messy desk a couple spaces west of you.\n\t" + 
						"A chest with a SILVER LOCK to the south of the room.\n\t" + 
						"An unlocked green door to the northwest corner of the room.";
				break;
			case "g":
				roomDescription = "\nROOM: Inspecting the room you see the following -\n\t" + 
						"A flashlight and a key on a shelf to the north of the room.\n\t" + 
						"A wall with a large crack in the northeast corner of the room.\n\t" + 
						"Seems like the wall was broken and resealed.";
				break;
			case "b":
				roomDescription = "\nROOM: You enter a livingroom and notice the following -\n\t" +
						"A large TV in the northeast corner of the room.\n\t" +
						"A fluffy, well-used couch in the southeast corner of the room.\n\t" +
						"There’s a purple door to the south of the room.";
				break;
			case "p":
				roomDescription = "\nROOM: Looking around the bedroom you see the following -\n\t" + 
						"A large luxurious bed to east of the room.\n\t" + 
						"A bedside table in the southeast corner of the room.";
				break;
		}
		return roomDescription;
	}
	
	// Pick up items (add to player's inventory)
	private static void pickUpItem(Scanner keyboard, Inventory inventory, String mapString) {
		if (inventory.isItemInGameItems(mapString)) {
			Item item = new Item();
			item = inventory.getSpecificGameItem(mapString);
			System.out.println("\nYou have found a " + item.getName() + "!\n\nPROMPT: Would you like add the " + 
					item.getName() + " to your inventory, 'yes' or 'no'?");
			String inputFromUser = userNavigation(keyboard);
			if (inputFromUser.equals("yes")) {
				inventory.addItemToInventory(item);
				System.out.println("\nINVENTORY: You have added the " + item.getName() + " to your inventory!\n\t" + 
						item.getItemInfo());
			}
			else {
				System.out.println("\nINVENTORY: You have decided to leave the " + item.getName() + 
						" behind. You might need it on your journey.");
			}
		}
		else {
			System.out.println("\nThere are no items to pick up here!");
		}
	}
	
	// View all items in inventory
	private static void itemInventory(Inventory inventory) {
		System.out.print("\nINVENTORY: You have the following items -\n" + inventory.displayAllItems());
	}
	
	// Save game data (to text file)
	private static void saveGame(Player player, Map map, Inventory inventory) {
		TextFile file = new TextFile();
		file.Save(player, map, inventory);
	}
	
	// Terminate the game (asks user to save game data)
	private static void quitGame(Scanner keyboard, Player player, Map map, Inventory inventory) {
		System.out.println("\nPROMPT: You will lose all current game progress.\n\t" + 
				"Would you like to save the current game progress, 'yes' or 'no'?");
		String answer = userNavigation(keyboard);
		if (!answer.equals("no")) {
			saveGame(player, map, inventory);
		}
		System.out.print("\nEXIT: The game has been terminated.");
	}
}