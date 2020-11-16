// created by Victor Ortiz & Diana Nguyen  CS142  11/14/19
// purpose: demonstrate your ability to work in a team while creating an Object Oriented program in Java.

//required to use Scanner for keyboard input
import java.util.Scanner;

/* game description: ESCAPE ROOM GAME - 
 * Using the items you find along your journey, you need to figure out a way to freedom. */
public class FinalProject {

	public static void main(String[] args) {
		// create instances to use throughout FinalProject class
		Scanner keyboard = new Scanner(System.in);
		Player player = new Player();
		Inventory inventory = new Inventory();
		Map map = new Map();
		
		// introduce game info/start new game or load game
		startGame(keyboard, player, map, inventory);
		
		boolean flag = true;
		// loop to show menu of options
		while (flag) {
			// variables to track changes in player position
			int newPosition, positionToSet;
			
			// prompt user for input (return input)
	    	System.out.println("\nPROMPT: What would you like to do now?");
	    	String inputFromUser = userNavigation(keyboard);
	    	
        	// match user input to case
        	switch (inputFromUser) {
	        	case "north":
	        		// get new position & get map string (if a wall or locked door don't change position)
	        		newPosition = map.goNorth(player.getYPosition());
	        		positionToSet = moveYPosition(map, player, newPosition, keyboard, inventory);
	        		player.setYPosition(positionToSet);
	        		break;
	        	case "east":
	        		// get new position & get map string (if a wall or locked door don't change position)
	        		newPosition = map.goEast(player.getXPosition());
	        		positionToSet = moveXPosition(map, player, newPosition, keyboard, inventory);
	        		player.setXPosition(positionToSet);
	        		break;
	        	case "south":
	        		// get new position & get map string (if a wall or locked door don't change position)
	        		newPosition = map.goSouth(player.getYPosition());
	        		positionToSet = moveYPosition(map, player, newPosition, keyboard, inventory);
	        		player.setYPosition(positionToSet);
	        		break;
	        	case "west":
	        		// get new position & get map string (if a wall or locked door don't change position)
	        		newPosition = map.goWest(player.getXPosition());
	        		positionToSet = moveXPosition(map, player, newPosition, keyboard, inventory);
	        		player.setXPosition(positionToSet);
	        		break;
	        	case "info":
	        		// print the current room description
	        		System.out.println(map.getRoomDescription());
	        		break;
	        	case "inventory":
	        		// display all items in player's inventory
	        		itemInventory(inventory);
	        		break;
	        	case "help":
	        		// display all commands user can use to navigate within the game
	        		System.out.println("\nINFO: Use the following commands to navigate within the game:\n\t" +  
	            			"use 'north', 'east', 'south', or 'west' to move around,\n\t" + 
	        				"'info' to display current room description\n\t" +
	            			"'inventory' to view current items in inventory,\n\t" +
	            			"'save' to save current game progress,\n\t" +
	            			"'restart' to reset to the beginning of the game,\n\t" +
	            			"or 'quit' to exit game & optionally save game progress.");
	        		break;
	        	case "restart":
	        		// create new instances of Player & Inventory classes
	        		Player newPlayer = new Player();
	        		Inventory newInventory = new Inventory();
	        		// assign the new instances to class-level variables
	        		player = newPlayer;
	        		inventory = newInventory;
	        		System.out.println("\nINFO: The game has been restarted.\n");
	        		// call StartGame() to restart the game with new instances
	        		startGame(keyboard, player, map, inventory);
	        		break;
	        	case "save":
	        		// save player info, current inventory & room description to text file
	        		saveGame(player, map, inventory);
	        		break;
	        	case "quit":
	        		// terminate the game (user asked to save game)
	        		quitGame(keyboard, player, map, inventory);
	        		// break out of while loop
	        		flag = false;
	        		break;
	        	default:
	        		// show error message for incorrect user input
	        		System.out.println("\nERROR: '" + inputFromUser + "' is not a valid command.\n\tPlease try again.");
	        		break;
        	}
		}
    	keyboard.close();
	}
	
	// method to introduce game, get user's name, or load game data
	private static void startGame(Scanner keyboard, Player player, Map map, Inventory inventory) {
		System.out.println("WELCOME TO THE ESCAPE ROOM GAME!\n\n" + 
				 "STORY: Using the items you find along your journey, you need to figure out a way to freedom.");
		
		boolean flag = true;
		// show menu of options
		while (flag) {
			// ask user to start a new game or load a saved game (return input)
			System.out.println("\nPROMPT: Would you like to start a new game?\n\t" + 
					"If so, type 'new', or type 'load' to load a saved game:");
        	String inputFromUser = userNavigation(keyboard);
        	
        	// match user input to case
        	switch (inputFromUser) {
        		case "new":
        			// start a new game
        			newGame(keyboard, player);
	        		flag = false;
        			break;
        		case "load":
        			// load a saved game
        			loadGame(player, map, inventory);
	        		flag = false;
        			break;
        		default:
        			// show error message for incorrect user input
	        		System.out.println("\nERROR: '" + inputFromUser + "' is not a valid command.\n\tPlease try again.");
        			break;
        	}
		}
	}
	
	// start a brand new game (get user's name & introduce plot of game)
	private static void newGame(Scanner keyboard, Player player) {
		// get users name to use throughout game
		System.out.println("\nPROMPT: What is your name?");
	    player.setName(keyboard.nextLine());
	    // describe game plot & describe initial room to the user
		System.out.println("\nSTORY: You wake up in a locked cell, cold and confused. It's a bit hard to move your hands.\n\t" +
				"When you look down, you notice your hands are cuffed with an attached RED LOCK.\n\t" + 
				"You are not sure how you got here. The only thing you do know is that you need to find a way out!\n" +
				"\nROOM: In the cell you see the following -\n\t" + 
				"The bed you woke up at is in the northwest corner of the room.\n\t" +
				"A metal bar in the northeast corner of the room.\n\t" + 
				"To the south you see old, rusty cell bars that keep you confined in the cell.");
	}
	
	// load game data from text file
	private static void loadGame(Player player, Map map, Inventory inventory) {
		TextFile textFile = new TextFile();
		// load player info, room description & inventory
		textFile.Load(player, map, inventory);
		// recap game stats for returning player
		System.out.print(player.displayStats(inventory));
	}
	
	// user input for navigation using Scanner
	private static String userNavigation(Scanner keyboard) {
    	// use trim() & toLowerCase() to match a case in a switch loop
    	String inputFromUser = keyboard.nextLine().trim().toLowerCase();
		return inputFromUser;
	}
	
	// display info/items when position changes in Y direction
	private static int moveYPosition(Map map, Player player, int newPosition, Scanner keyboard, Inventory inventory) {
		int currentPosition = player.getYPosition();
		// get string from map from new player position
		String mapString = map.returnMapString(player.getXPosition(), newPosition);
		// determine if position changed (get new item, unlock door/chest, etc), else hit wall (don't change position)
		int positionToSet = mapInteraction(currentPosition, mapString, newPosition, keyboard, inventory, map);
		return positionToSet;
	}
	
	// display info/items when position changes in X direction
	private static int moveXPosition(Map map, Player player, int newPosition, Scanner keyboard, Inventory inventory) {
		int currentPosition = player.getXPosition();
		// get string from map from new player position
		String mapString = map.returnMapString(newPosition, player.getYPosition());
		// determine if position changed (get new item, unlock door/chest, etc), else hit wall (don't change position)
		int positionToSet = mapInteraction(currentPosition, mapString, newPosition, keyboard, inventory, map);
		return positionToSet;
	}
	
	// player interaction with map: new items, hit walls, locked/unlock doors, room descriptions, etc.
	private static int mapInteraction(int currentPosition, String mapString, int newPosition, Scanner keyboard, Inventory inventory, Map map) {
		int positionToSet = 0;
		switch (mapString) {
			case "|":
				System.out.println("\nYou can’t move any further, there's a wall in the way.");
				// don't change player's position (can't go through wall)
				positionToSet = currentPosition;
				break;
			case " ":
				System.out.println("\nYou moved one space.");
				positionToSet = newPosition;
				break;
			case "r":
				System.out.println("\nYou moved one space.");
				// save room description (can be accessed when needed)
				setRoomString(mapString, map);
				positionToSet = newPosition;
				break;
			case "o":
				System.out.println("\nYou moved one space.");
				setRoomString(mapString, map);
				positionToSet = newPosition;
				break;
			case "y":
				System.out.println("\nYou moved one space.");
				setRoomString(mapString, map);
				positionToSet = newPosition;
				break;
			case "g":
				System.out.println("\nYou moved one space.");
				setRoomString(mapString, map);
				positionToSet = newPosition;
				break;
			case "b":
				System.out.println("\nYou moved one space.");
				setRoomString(mapString, map);
				positionToSet = newPosition;
				break;
			case "p":
				System.out.println("\nYou moved one space.");
				setRoomString(mapString, map);
				positionToSet = newPosition;
				break;
			case "cb":
				if (inventory.itemInPlayerInventory("ch")) {
					System.out.println("\nSTORY: You sit down with your back against the cell bars.\n\t" + 
							"With limited hand mobility and hard work you successfully chisel the cell bars loose!\n\t" + 
							"You make your escape through the cell bars!");
					positionToSet = newPosition;
				}
				else {
					System.out.println("\nYou can't go any further! The cell bars are in the way!\n\t" + 
							"You need something to break out of the cell.");
					// don't let player advance position
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
				if (inventory.itemInPlayerInventory("bk")){
					System.out.println("\nUsing the BLUE KEY you unlock the purple door!");
					positionToSet = newPosition;
				}
				else {
					System.out.println("\nYou don't have the correct key to unlock the purple door.");
					// don't let player advance position
					positionToSet = currentPosition;
				}
				break;
			case "bd":
				System.out.println("\nThe bed looks like it has been used recently.");
				positionToSet = newPosition;
				break;
			case "bt":
				System.out.println("\nYou look through the bedside table for anything useful.");
				// ask user to add newspaper to inventory
				pickUp(keyboard, inventory, "gk");
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
				// ask user to add newspaper to inventory
				pickUp(keyboard, inventory, "np");
				positionToSet = newPosition;
				break;
			case "ct":
				if (inventory.itemInPlayerInventory("sk")) {
					System.out.println("\nUsing the SILVER KEY you unlock the chest!");
					// ask user to add blue key to inventory
					pickUp(keyboard, inventory, "bk");
					positionToSet = newPosition;
				}
				else {
					System.out.println("\nThe chest is locked! You need a key that matches the SILVER LOCK.");
					// don't let player advance position
					positionToSet = currentPosition;
				}
				break;
			case "rk":
				System.out.println("\nYou come across a FLASHLIGHT and a RED KEY on a bookshelf in the northwest corner of the room.");
				// ask user to add the flashlight and red key to inventory
				pickUp(keyboard, inventory, "fl");
				pickUp(keyboard, inventory, "rk");
				positionToSet = newPosition;
				break;
			case "cw":
				if (inventory.itemInPlayerInventory("hm")) {
					System.out.println("\nThe large crack in the wall looks suspicious.\n\t" +
							"Using your HAMMER you successfully break through the wall and find a SILVER KEY!");
					// ask user to add the silver key to inventory
					pickUp(keyboard, inventory, "sk");
					positionToSet = newPosition;
				}
				else {
					System.out.println("\nYou need something to break through the wall.");
					positionToSet = currentPosition;
				}
				break;
			case "gl":
				if (inventory.itemInPlayerInventory("gk")) {
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
				// if there is no case (items you can pick up)
				pickUp(keyboard, inventory, mapString);
				positionToSet = newPosition;
				break;
		}
		return positionToSet;
	}
	
	// set the roomString (to access the room description when needed)
	public static void setRoomString(String mapString, Map map)
	{
		// save roomString to variable (always know which room player is in)
		map.setRoomString(mapString);
		// call method using mapString to set the correct roomDescription
		setRoomDescription(mapString, map);
	}
	
	// set the room description String
	public static void setRoomDescription(String mapString, Map map) {
		switch (mapString) {
			case "r":
				map.setRoomDescription("\nROOM: In the cell you see the following -\n\t" + 
						"In the nortwest corner is the bed you woke up in.\n\t" +
						"A metal bar in the northeast corner of the room.\n\t" + 
						"To the south you see old, rusty cell bars that keep you confined in the cell.");
				break;
			case "o":
				map.setRoomDescription("\nROOM: In the main hallway, you notice the following -\n\t" + 
						"Stairs in the northeast corner of the room.\n\t" + 
						"A black door with a GOLD LOCK to the south of the room.\n\t" + 
						"An unlocked yellow door to the southwest of the room.");
				break;
			case "y":
				map.setRoomDescription("\nROOM: You look around the office and notice the following -\n\t" + 
						"A messy desk a couple spaces west of you.\n\t" + 
						"A chest with a SILVER LOCK to the south of the room.\n\t" + 
						"An unlocked green door to the northwest corner of the room.");
				break;
			case "g":
				map.setRoomDescription("\nROOM: Inspecting the room you see the following -\n\t" + 
						"A flashlight and a key on a shelf to the north of the room.\n\t" + 
						"A wall with a large crack in the northeast corner of the room.\n\t" + 
						"Seems like the wall was broken and resealed.");
				break;
			case "b":
				map.setRoomDescription("\nROOM: You enter a livingroom and notice the following -\n\t" +
						"A large TV in the northeast corner of the room.\n\t" +
						"A fluffy, well-used couch in the southeast corner of the room.\n\t" +
						"There’s a purple door to the south of the room.");
				break;
			case "p":
				map.setRoomDescription("\nROOM: Looking around the bedroom you see the following -\n\t" + 
						"A large luxurious bed to east of the room.\n\t" + 
						"A bedside table in the southeast corner of the room.");
				break;
		}
	}
	
	// pick up items (add to player's inventory)
	private static void pickUp(Scanner keyboard, Inventory inventory, String mapString) {
		// check if mapString matches any items in gameItems inventory
		if (inventory.itemInGameItems(mapString)) {
			Item item = new Item();
			// if a match is found return specified item
			item = inventory.getSpecificGameItem(mapString);
			System.out.println("\nYou have found a " + item.getName() + "!\n\nPROMPT: Would you like add the " + 
					item.getName() + " to your inventory, 'yes' or 'no'?");
			String inputFromUser = userNavigation(keyboard);
			// if user answers yes, add item to inventory
			if (inputFromUser.equals("yes")) {
				inventory.addItemToInventory(item);
				System.out.println("\nINVENTORY: You have added the " + item.getName() + " to your inventory!\n\t" + 
						item.getItemInfo());
			}
			else {  // leave item behind
				System.out.println("\nINVENTORY: You have decided to leave the " + item.getName() + 
						" behind. You might need it on your journey.");
			}
		}
		else {  // edge case
			System.out.println("\nThere are no items to pick up here!");
		}
	}
	
	// view all items in inventory
	private static void itemInventory(Inventory inventory) {
		// returns string by calling displayAllItems() in Inventory class
		System.out.print("\nINVENTORY: You have the following items -\n" + inventory.displayAllItems());
	}
	
	// save game data (to text file)
	private static void saveGame(Player player, Map map, Inventory inventory) {
		TextFile file = new TextFile();
		// call the Save() through instance, send in data to save
		file.Save(player, map, inventory);
	}
	
	// terminate the game (asks user to save game data)
	private static void quitGame(Scanner keyboard, Player player, Map map, Inventory inventory) {
		// ask the user to save game state to file
		System.out.println("\nPROMPT: You will lose all current game progress.\n\t" + 
				"Would you like to save the current game progress, 'yes' or 'no'?");
		String answer = userNavigation(keyboard);
		// if user answers anything but no, call the SaveGame()
		if (!answer.equals("no")) {
			saveGame(player, map, inventory);
		}
		System.out.print("\nINFO: The game has been terminated.");
	}
	
}