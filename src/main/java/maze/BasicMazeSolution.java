package maze;

import structure.ListInterface;
import structure.RecursiveLinkedList;

/**
 * Represents the solution path to solve the current maze by determining if a
 * room is an exit or leads to an exit and compiling the path of rooms to escape
 * 
 * @author sagesilberman
 *
 */
public class BasicMazeSolution implements MazeSolution {
	private int currRoomNum = -1; // the current room number we are at in the maze
	private ListInterface<Room> visitedRooms; // a list of all the rooms we have visited in the maze
	private Maze currMaze; // current maze we are trying to solve

	/**
	 * Constructs a BasicMazeSolution
	 * 
	 * @param currMaze the current we are in
	 */
	public BasicMazeSolution(Maze currMaze) {
		this.currMaze = currMaze;
		visitedRooms = new RecursiveLinkedList<Room>();
	}

	/**
	 * Returns <code>true</code> if the maze can be solved.
	 * 
	 * @param r the current room we are in
	 * @return <code>true</code> if the maze can be solved; <code>false</code>
	 *         otherwise
	 */
	public boolean canBeSolved(Room r) {
		currRoomNum++;
		// if r is an exit then yes the maze can be solved
		if (r.isExit()) {
			return true;
			// else if the current room number is less than r's size, it can't be solved
		} else if (currRoomNum == r.getRooms().size() - 1) {
			return false;

		} else {
			visitedRooms.insertLast(r.getRooms().get(currRoomNum));
			if (visitedRooms.contains(r.getRooms().get(currRoomNum)) > 0) {
				currRoomNum++;
				return canBeSolved(r.getRooms().get(currRoomNum));
			} else {
				return canBeSolved(r.getRooms().get(currRoomNum));
			}
		}
	}

	@Override
	public Maze getMaze() {
		return currMaze;
	}

	@Override
	public ListInterface<Room> getSolution() {
		ListInterface<Room> solutionPath = getSolutionFromStart(currMaze.getStart());
		if (solutionPath == null) {
			throw new UnsolvableMazeException("This maze cannot be solved.");
		}
		return solutionPath;
	}

	/**
	 * Returns the solution path starting from the starting room.
	 * 
	 * @param startR the starting room of the maze
	 * @return the solution path
	 */
	public ListInterface<Room> getSolutionFromStart(Room startR) {
		// if the starting room is an exit then add the first room to the solution path
		// list and just return that
		if (startR.isExit()) {
			ListInterface<Room> solutionPath = new RecursiveLinkedList<Room>();
			solutionPath.insertFirst(startR);
			return solutionPath;
			// else go to the next room and try that
		} else {
			return getNextSolution(startR, 0);
		}
	}

	/**
	 * Returns the solution path starting from the starting room and the index
	 * starting starting index
	 * 
	 * @param startR the starting room of the maze
	 * @param sIndex the starting index of the starting room
	 * @return the solution path
	 */
	private ListInterface<Room> getNextSolution(Room startR, int sIndex) {
		// if the starting rooms index is less than the starting index then it doesn't
		// exist (a.k.a. not a room)
		if (startR.getRooms().size() <= sIndex) {
			return null;
		}
		Room nextRoom = startR.getRooms().get(sIndex);

		// if we've already been to this room then ignore and move on to the next room
		// and
		// increase the index
		if (visitedRooms.contains(nextRoom) != -1) {
			return getNextSolution(startR, sIndex + 1);
		}

		// the last room we visited is marked as a next room
		visitedRooms.insertLast(nextRoom);
		ListInterface<Room> solutionPath = getSolutionFromStart(nextRoom);

		// if the path isn't null add the room to the solution path list of rooms
		if (solutionPath != null) {
			solutionPath.insertFirst(startR);
			return solutionPath;
		}

		// recurse through next room and increase index
		return getNextSolution(startR, sIndex + 1);
	}
}
