package unit;

import org.autocarsimulator.model.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {

    @Test
    void testTurnLeft() {
        assertEquals(Direction.W, Direction.N.turnLeft());
        assertEquals(Direction.E, Direction.S.turnLeft());
        assertEquals(Direction.N, Direction.E.turnLeft());
        assertEquals(Direction.S, Direction.W.turnLeft());
    }

    @Test
    void testTurnRight() {
        assertEquals(Direction.E, Direction.N.turnRight());
        assertEquals(Direction.W, Direction.S.turnRight());
        assertEquals(Direction.S, Direction.E.turnRight());
        assertEquals(Direction.N, Direction.W.turnRight());
    }


}
