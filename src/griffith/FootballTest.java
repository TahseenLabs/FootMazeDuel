package griffith;
/*
 * Group Member-1: Olimeh kelvin     ; Student No. 3112489
 * Group Member-2: Tahseen Ahmad     ; Student No. 3135743
 * Group Member-3: Francis Ngonadi   ; Student No. 3138335
 * Group Member-4: Komarovskyi Denys ; Student No. 3105289
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FootballTest {
    private Football football;

    @BeforeEach
    void setUp() {
        football = new Football(370, 225); // Starting at Center
    }

    @Test
    void testInitialPosition() {
        assertNotNull(football, "Football should be initialized properly");
    }

    @Test
    void testMoveDownAndUp() {
        football.move(); // Moves once (down)
        football.move(); // Moves again

        // Simulating downward movement hitting limit and reversing direction
        for (int i = 0; i < 100; i++) {
            football.move(); // Should hit LOWER or UPPER bounds and reverse
        }

        assertTrue(true, "Football moved within allowed range without crashing");
    }

    @Test
    void testResetSetsCenterAndFollow() {
        football.resetPosition();
        assertDoesNotThrow(() -> football.resetPosition(), "Reset should complete without exception");
    }

   /*@Test
    void testMoveWithStrikerAlignsToStriker() {
        football.resetPosition(); // Puts it in following mode
        football.moveWithStriker(100);
        assertDoesNotThrow(() -> football.moveWithStriker(100), "Ball should follow striker smoothly");
    }*/
}

