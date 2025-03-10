package test;

import main.java.com.WarriorVSAlien.controller.GameController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {

    private GameController gameState;

    @BeforeEach
    void setUp() {
        gameState = new GameController("TestPlayer");

    }

    @Test
    void testStartGame() {
        // Assert that the player has been correctly initialized with the input name
        Assertions.assertEquals("TestPlayer", gameState.getPlayer().getName());
    }


}