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
import javax.swing.*;

class GamePanelTest {
    private GamePanel panel;

    @BeforeEach
    void setUp() {
        panel = new GamePanel();
    }

    @Test
    void testScoreboardExists() {
        JLabel scoreLabel = findComponent(panel, JLabel.class, "Striker: 0 | Defender: 0");
        assertNotNull(scoreLabel, "Scoreboard label should exist in GamePanel.");
    }

    @Test
    void testResetButtonExists() {
        JButton resetButton = findComponent(panel, JButton.class, "Reset Game");
        assertNotNull(resetButton, "Reset button should exist in GamePanel.");
    }

    // Utility method to find a component by type and text
    private <T extends JComponent> T findComponent(JPanel panel, Class<T> type, String text) {
        for (var component : panel.getComponents()) {
            if (type.isInstance(component) && ((JComponent) component).toString().contains(text)) {
                return type.cast(component);
            }
        }
        return null;
    }
}
