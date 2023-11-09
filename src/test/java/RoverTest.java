import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoverTest {
    @Test
    public void testRoverInitialization() {
        Rover rover = new Rover("0 0 N", "M");
        assertEquals(0, rover.getxCoordinate());
        assertEquals(0, rover.getyCoordinate());
        assertEquals('N', rover.getDirection().charValue());
        assertEquals("M", rover.getInstructions());
    }

    @Test
    public void testTurnLeft() {
        Rover rover = new Rover("0 0 N", "L");
        rover.turn("L");
        assertEquals('W', rover.getDirection().charValue());
    }

    @Test
    public void testTurnRight() {
        Rover rover = new Rover("0 0 N", "R");
        rover.turn("R");
        assertEquals('E', rover.getDirection().charValue());
    }

    @Test
    public void testMoveForwardNorth() {
        Rover rover = new Rover("0 0 N", "M");
        rover.moveForward(5, 5);
        assertEquals(1, rover.getyCoordinate());
    }

    @Test
    public void testMoveForwardSouth() {
        Rover rover = new Rover("0 1 S", "M");
        rover.moveForward(5, 5);
        assertEquals(0, rover.getyCoordinate());
    }

    @Test
    public void testMoveForwardWest() {
        Rover rover = new Rover("1 0 W", "M");
        rover.moveForward(5, 5);
        assertEquals(0, rover.getxCoordinate());
    }

    @Test
    public void testMoveForwardEast() {
        Rover rover = new Rover("0 0 E", "M");
        rover.moveForward(5, 5);
        assertEquals(1, rover.getxCoordinate());
    }

    @Test
    public void testFinalPositionWithinBounds() {
        Rover rover = new Rover("0 0 N", "MMRM");
        String finalPosition = rover.finalPosition(5, 5);
        assertEquals("1 2 E", finalPosition);
    }

    @Test
    public void testFinalPositionOffBoard() {
        Rover rover = new Rover("3 3 E", "MMM");
        String finalPosition = rover.finalPosition(4, 4);
        assertEquals("6 3 E  Robot went off board, mission failed", finalPosition);
    }
    @Test
    public void testInitialPositionOffBoard() {
        Rover rover = new Rover("3 3 E", "MMM");
        String finalPosition = rover.finalPosition(1, 1);
        assertEquals("3 3 E  Robot is already off board, mission failed", finalPosition);
    }
}
