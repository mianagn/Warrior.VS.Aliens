package Tests;

import Domain.Alien;
import Domain.CharacterDomain;
import Domain.Human;
import Domain.Stats;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AttackTest {

    @Test
    void attackTest() {
        // Arrange
        Stats stats = new Stats(1000, 50, 10, 30, 10); // 1000 health, 50 attack power
        CharacterDomain character = new Human("TestHero");
        CharacterDomain character2 = new Alien("TestVillain");
        CharacterDomain.equipPresetItems(character);

        int initialHealth = character2.getStats().getHealth();

        // Act
        character.attack(character2);
        int remainingHealth = character2.getStats().getHealth();
        // Possible damage values
        int damage=(stats.getStrength()*2+character.getEquippedWeapon().attackBoost()-stats.getDefense()/10);
        int normalDamage =damage;
        int critDamage = (int) (damage* 1.5); // 75
        int doubleHitDamage = damage* 2; // 100

        int expectedHealth1 = initialHealth - normalDamage;
        int expectedHealth2 = initialHealth - critDamage;
        int expectedHealth3 = initialHealth - doubleHitDamage;

        // Assert that the health is one of the expected values
        assertTrue(
                remainingHealth == expectedHealth1 ||
                        remainingHealth == expectedHealth2 ||
                        remainingHealth == expectedHealth3,
                "Health did not match any expected value."
        );
    }
}