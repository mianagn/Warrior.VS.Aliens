package main.java.com.WarriorVSAlien.model.characters;

import main.java.com.WarriorVSAlien.model.Stats;
import main.java.com.WarriorVSAlien.model.items.weapons.Weapon;

public class Alien extends CharacterDomain {
    private static final Stats[] ALIEN_STATS = {
            new Stats(800, 60, 8, 30, 15),  // main.java.com.WarriorVSAlien.model.characters.Alien 1
            new Stats(1000, 80, 12, 40, 18), // main.java.com.WarriorVSAlien.model.characters.Alien 2
            new Stats(1200, 100, 15, 50, 20)  // main.java.com.WarriorVSAlien.model.characters.Alien 3
    };
    private static int alienCount = 0;
    public Alien(String name) {
        super(name, getNextStats());
    }
    private static Stats getNextStats() {
        if (alienCount >= ALIEN_STATS.length) {
            alienCount = 0;
        }
        return ALIEN_STATS[alienCount++];
    }
    Weapon sword = null;

    @Override
    public void attack(CharacterDomain opponent) {
        int weaponBoost = (sword != null) ? sword.attackBoost() : 0;
        int damage = getStats().getStrength() + weaponBoost;
        int totalDamage = damage;

        int randomAttack = (int) (Math.random() * 4) + 1;

        System.out.println(this.getName() + " attacks " + opponent.getName() + " for " + damage + " damage!");
        opponent.takeDamage(damage);

        if (randomAttack == 4) {
            System.out.println(this.getName() + " uses Thrash and attacks again!");
            opponent.takeDamage(damage);
            totalDamage += damage;
            System.out.println("Total damage dealt to " + opponent.getName() + ": " + totalDamage);
        }
    }

    @Override
    public void specialAttack(CharacterDomain opponent) {
        int damage = this.getStats().getStrength() * 2;
        System.out.println(this.getName() + " uses Void Beam on " + opponent.getName() + " for " + damage + " damage!");
        opponent.takeDamage(damage);
    }
}
