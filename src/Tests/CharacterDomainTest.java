package Tests;

import Domain.CharacterDomain;
import Domain.Stats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CharacterDomainTest {
    @Test
    void testCharacterHealth() {
        CharacterDomain character = new CharacterDomain("TestHero", new Stats(100, 10, 5, 3, 2));
        Assertions.assertEquals(100, character.getStats().getHealth());
    }
}