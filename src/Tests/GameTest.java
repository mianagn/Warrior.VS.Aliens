package Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
            game = new Game();

    }

    @Test
    void testStartGame() {
        // Assert that the player has been correctly initialized with the input name
        Assertions.assertEquals("TestPlayer", game.getPlayer().getName());
    }

    @Test
    void testPlayerCreation() {
        // Assert that the player was created correctly
        Assertions.assertTrue(game.getStarted());
    }
}