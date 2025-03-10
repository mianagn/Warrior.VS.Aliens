package test;

import main.java.com.WarriorVSAlien.model.characters.CharacterDomain;
import main.java.com.WarriorVSAlien.model.Stats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharacterDomainTest {
    @Test
    void testCharacterHealth() {
        CharacterDomain character = new CharacterDomain("TestHero", new Stats(100, 10, 5, 3, 2));
        Assertions.assertEquals(100, character.getStats().getHealth());
    }
}