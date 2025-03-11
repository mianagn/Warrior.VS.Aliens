package test;


import main.java.com.WarriorVSAlien.view.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
class GameTest {
    private Game game;
    @BeforeEach
    void setUp() {
        // Simulate user input for the player name
        String simulatedInput = "TestPlayer\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);
        game = new Game();
        game.initializeGame();
    }
    @Test
    void testStartGame() {
        // Assert that the player's name is set to the simulated input "TestPlayer"
        Assertions.assertEquals("TestPlayer", game.getState().getPlayer().getName());
    }
}
