package maze;

import structure.ListInterface;

//one thing to comment here

/**
 * COMMENT
 * 
 * @author sagesilberman
 *
 */
public class BasicMazeBuilder implements MazeBuilder {

	@Override
	public Room createRoom(String longDescription, String shortDescription) {
		if (longDescription == null || shortDescription == null) {
			throw new NullPointerException();
		}
		BasicRoom newRoom = new BasicRoom(longDescription, shortDescription, false);
		return newRoom;
	}

	@Override
	public Room createExit(String longDescription, String shortDescription) {
		if (longDescription == null || shortDescription == null) {
			throw new NullPointerException();
		}
		BasicRoom newExitRoom = new BasicRoom(longDescription, shortDescription, true);
		return newExitRoom;
	}

	@Override
	public MazeBuilder addPassage(Room room0, Room room1) {
		addOneWayPassage(room0, room1);
		addOneWayPassage(room1, room0);
		return this;
	}

	@Override
	public MazeBuilder addOneWayPassage(Room fromRoom, Room toRoom) {
		if (fromRoom == null || toRoom == null) {
			throw new NullPointerException();
		}
		ListInterface<Room> fromRoomAdjacRooms = fromRoom.getRooms();
		if (fromRoomAdjacRooms.contains(toRoom) == -1) {
			fromRoomAdjacRooms.insertFirst(toRoom);
		}
		return this;
	}

}
