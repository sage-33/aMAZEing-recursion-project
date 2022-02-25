package maze;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import structure.ListInterface;
import config.Configuration;

public class MazeSolutionTest {

	private MazeBuilder builder;

	// NOTICE: This test assumes your builder works. If you find errors, it is
	// possible that your MazeBuilder implementation is broken.

	@Before
	public void setup() {
		builder = Configuration.getMazeBuilder();
		if (builder == null)
			fail("You seem to have forgotten to set which builder to use in the Configuraiton.getMazeBuilder method.");
		if (builder == Configuration.getMazeBuilder())
			fail("You should return a new instance of MazeBuilder when Configuration.getMazeBuilder() is called.");
	}

	@Test(timeout = 500)
	public void testSimpleMaze() {
		Room r0 = builder.createExit("This is an exit", "very simple.");
		Maze maze = new Maze(r0);
		MazeSolution solution = Configuration.getMazeSolution(maze);
		assertNotNull(
				"You seem to have forgotten to set which MazeSolution to use in the Configuration.getMazeSolution method.",
				solution);

		assertEquals("The mazes should match.", maze, solution.getMaze());
		ListInterface<Room> rooms = solution.getSolution();
		assertFalse(rooms.isEmpty());
		assertEquals("The solution should contain 1 room", 1, rooms.size());
		assertEquals("The one room should be the exit.", r0, rooms.getFirst());
	}

	private void checkSolution(Room start, ListInterface<Room> rooms) {
		assertEquals(start, rooms.getFirst());

		while (!rooms.isEmpty()) {
			Room current = rooms.removeFirst();
			if (rooms.isEmpty()) {
				assertTrue("Last room in solution was not an exit.", current.isExit());
				return;
			}
			Room movingTo = rooms.getFirst();
			assertNotEquals("The room " + current.getFullDescription() + " did not contain a path to "
					+ movingTo.getFullDescription(), -1, current.getRooms().contains(movingTo));
		}
	}

	@Test(timeout = 500)
	public void testFourRoomMaze() {
		Room r0 = builder.createRoom("An orange room!", "Orange Hallway");
		Room r1 = builder.createRoom("A red room!", "Red Hallway");
		Room r2 = builder.createRoom("A blue room!", "Blue Hallway.");
		Room r3 = builder.createExit("A purple room!", "Purple Hallway.");
		builder.addOneWayPassage(r0, r1).addPassage(r1, r2).addOneWayPassage(r2, r0).addOneWayPassage(r2, r3)
				.addOneWayPassage(r3, r1);
		Maze maze = new Maze(r0);
		MazeSolution solution = Configuration.getMazeSolution(maze);
		assertNotNull(
				"You seem to have forgotten to set which MazeSolution to use in the Configuration.getMazeSolution method.",
				solution);

		assertEquals("The mazes should match.", maze, solution.getMaze());
		ListInterface<Room> rooms = solution.getSolution();
		checkSolution(r0, rooms);
	}

	@Test(timeout = 500, expected = UnsolvableMazeException.class)
	public void testFourRoomMazeNoExit() {
		Room r0 = builder.createRoom("An orange room!", "Orange Hallway");
		Room r1 = builder.createRoom("A red room!", "Red Hallway");
		Room r2 = builder.createRoom("A blue room!", "Blue Hallway.");
		Room r3 = builder.createRoom("A purple room!", "Purple Hallway.");
		builder.addOneWayPassage(r0, r1).addPassage(r1, r2).addOneWayPassage(r2, r0).addOneWayPassage(r2, r3)
				.addOneWayPassage(r3, r1);
		Maze maze = new Maze(r0);
		MazeSolution solution = Configuration.getMazeSolution(maze);
		solution.getSolution();
	}

	@Test(timeout = 500)
	public void testDeadEndMaze() {
		Room r0 = builder.createRoom("An orange room!", "Orange Hallway");
		Room r1 = builder.createRoom("A red room!", "Red Hallway");
		Room r2 = builder.createRoom("A blue room!", "Blue Hallway.");
		Room r3 = builder.createExit("A purple room!", "Purple Hallway.");
		builder.addOneWayPassage(r0, r1).addPassage(r0, r2).addPassage(r2, r3);
		Maze maze = new Maze(r0);
		MazeSolution solution = Configuration.getMazeSolution(maze);
		assertNotNull(
				"You seem to have forgotten to set which MazeSolution to use in the Configuration.getMazeSolution method.",
				solution);

		assertEquals("The mazes should match.", maze, solution.getMaze());
		ListInterface<Room> rooms = solution.getSolution();
		checkSolution(r0, rooms);
	}

	// logic test 1
	@Test(timeout = 500)
	public void testThreeRoomMaze() {
		Room r0 = builder.createRoom("Sage's dirty room!", "Dirty Hallway");
		Room r1 = builder.createRoom("Gabe's clean room!", "Clean Hallway");
		Room r2 = builder.createExit("Jake's amazing room!", "Amazing Hallway.");
		builder.addOneWayPassage(r0, r1).addPassage(r1, r2).addOneWayPassage(r2, r0).addOneWayPassage(r2, r0);
		Maze maze = new Maze(r0);
		MazeSolution solution = Configuration.getMazeSolution(maze);
		assertNotNull(
				"You seem to have forgotten to set which MazeSolution to use in the Configuration.getMazeSolution method.",
				solution);

		assertEquals("The mazes should match.", maze, solution.getMaze());
		ListInterface<Room> rooms = solution.getSolution();
		checkSolution(r0, rooms);
	}

	// logic test 2
	@Test(timeout = 500)
	public void testFourRoomNotStraight() {
		Room r0 = builder.createRoom("Sage's dirty room!", "Dirty Hallway");
		Room r1 = builder.createRoom("Gabe's clean room!", "Clean Hallway");
		Room r2 = builder.createRoom("Jake's amazing room!", "Amazing Hallway.");
		Room r3 = builder.createExit("Sammy's weird room!", "Weird Hallway");
		builder.addOneWayPassage(r0, r2).addOneWayPassage(r2, r1).addOneWayPassage(r1, r3).addOneWayPassage(r3, r0);
		Maze maze = new Maze(r0);
		MazeSolution solution = Configuration.getMazeSolution(maze);
		assertNotNull(
				"You seem to have forgotten to set which MazeSolution to use in the Configuration.getMazeSolution method.",
				solution);

		assertEquals("The mazes should match.", maze, solution.getMaze());
		ListInterface<Room> rooms = solution.getSolution();
		checkSolution(r0, rooms);
	}

	// exception test
	@Test(timeout = 500, expected = UnsolvableMazeException.class)
	public void testNoPathToExit() {
		Room r0 = builder.createRoom("Sage's cool room!", "Cool Hallway.");
		Room r1 = builder.createRoom("Jake's dirty room!", "Dirty Hallway.");
		Room r2 = builder.createRoom("Gabe's weird room!", "Weird Hallway.");
		builder.addOneWayPassage(r0, r1).addPassage(r1, r2).addOneWayPassage(r2, r0).addOneWayPassage(r2, r0);
		Maze maze = new Maze(r0);
		MazeSolution solution = Configuration.getMazeSolution(maze);
		solution.getSolution();
	}

}
