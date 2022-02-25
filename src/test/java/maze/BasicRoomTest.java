package maze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BasicRoomTest {

	@Test(timeout = 100)
	public void testDescriptions() {
		BasicRoom room = new BasicRoom("Sage's red room.", "Sage's room.", false);
		assertEquals("Sage's red room.", room.getFullDescription());
		assertEquals("Sage's room.", room.getShortDescription());
	}

	@Test(timeout = 100)
	public void testExitAndGetRooms() {
		BasicRoom room = new BasicRoom("Jake's blue room.", "Jake's room.", true);
		assertTrue(room.isExit());
		BasicRoom otherRoom = new BasicRoom("Gabe's green room.", "Gabe's room.", true);
		room.getRooms().insertAt(0, otherRoom);
		assertEquals(0, room.getRooms().contains(otherRoom));

	}

	// Exception
	@Test(timeout = 100, expected = NullPointerException.class)
	public void testNullDescriptions() {
		BasicRoom room = new BasicRoom(null, null, true);
	}
}