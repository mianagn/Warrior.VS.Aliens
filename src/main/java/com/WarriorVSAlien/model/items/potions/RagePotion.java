package main.java.com.WarriorVSAlien.model.items.potions;

import main.java.com.WarriorVSAlien.model.characters.CharacterDomain;
import main.java.com.WarriorVSAlien.model.items.Item;

public class RagePotion extends Item {
    private final int rageAmount;
    public RagePotion(String name, int rageAmount) {
        super(name);
        this.rageAmount = rageAmount;
    }

    @Override
    public void effect(CharacterDomain characterDomain) {
        int currentRage = characterDomain.getStats().getRage();
        int maxRage = characterDomain.getMaxRage();
        int newRage = Math.min(currentRage + rageAmount, maxRage);
        characterDomain.getStats().setRage(newRage);
        System.out.println(characterDomain.getName() + " used a rage potion and restored " + rageAmount + " rage!");
    }

    @Override
    public String toString() {
        return getName() + " (Restores " + rageAmount + " mana)";
    }
}
