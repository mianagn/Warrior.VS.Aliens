package test;

import main.java.com.WarriorVSAlien.model.*;
import main.java.com.WarriorVSAlien.model.characters.CharacterDomain;
import main.java.com.WarriorVSAlien.model.characters.Human;
import main.java.com.WarriorVSAlien.model.items.potions.HealthPotion;
import main.java.com.WarriorVSAlien.model.items.Item;
import main.java.com.WarriorVSAlien.model.items.potions.RagePotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestUseItem {

    @Test
    void testUseHealthPotion() {
        // Arrange
        Stats stats = new Stats(1000, 10, 10, 10, 10);
        CharacterDomain player = new Human("Player");
        player.getStats().setHealth(2);
        Item healthPotion = new HealthPotion("Health Potion", player.getMaxHealth());

        // Act
        healthPotion.effect(player);


        // Assert
        // Assuming the healing potion restores 50 health points
        Assertions.assertEquals(player.getMaxHealth(), player.getStats().getHealth());
    }
    @Test
    void testUseRagePotion() {
        // Arrange
        Stats stats = new Stats(100, 50, 10, 0, 10); // initial stats
        CharacterDomain warrior = new Human("Warrior");
        Item ragePotion = new RagePotion("Rage Potion",warrior.getMaxRage()); // Assuming main.java.com.WarriorVSAlien.model.items.potions.RagePotion increases rage

        // Act
        ragePotion.effect(warrior);

        // Assert
        // Assuming main.java.com.WarriorVSAlien.model.items.potions.RagePotion increases the warrior's rage by 20
        Assertions.assertEquals(warrior.getMaxRage(), warrior.getStats().getRage()); // Adjust depending on your logic
    }
}
