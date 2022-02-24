package maze;

import structure.ListInterface;
import structure.RecursiveLinkedList;

/**
 * Represents a basic room that provides a full and short description of said
 * room and determines whether or not a room is an exit
 *
 * @author sagesilberman
 *
 */
public class BasicRoom implements Room {
	private String fullDesc; // a full description of the room
	private String shortDesc; // a short description of the room
	private boolean exit; // whether or not a room is an exit or not
	protected ListInterface<Room> adjacRooms; // a list of rooms adjacent to the current room

	/**
	 * Constructs a basic room with the full description of the room, short
	 * description of the room, and whether or not a room is an exit
	 * 
	 * @param f the full description of the room
	 * @param s the short description of the room
	 * @param e whether or not a room is an exit s
	 */
	public BasicRoom(String f, String s, boolean e) {
		fullDesc = f;
		shortDesc = s;
		exit = e;
		adjacRooms = new RecursiveLinkedList<Room>();
	}

	@Override
	public String getFullDescription() {
		return fullDesc;
	}

	@Override
	public String getShortDescription() {
		return shortDesc;
	}

	@Override
	public boolean isExit() {
		if (exit) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ListInterface<Room> getRooms() {
		return adjacRooms;
	}

}
