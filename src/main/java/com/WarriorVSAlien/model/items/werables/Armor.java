package main.java.com.WarriorVSAlien.model.items.werables;

import main.java.com.WarriorVSAlien.model.characters.CharacterDomain;
import main.java.com.WarriorVSAlien.model.items.Item;

public class Armor extends Item {
    private final int defenseBoost;

    public Armor(String name, int defenseBoost) {
        super(name);
        this.defenseBoost = defenseBoost;
    }

    @Override
    public void effect(CharacterDomain characterDomain) {
        System.out.println(characterDomain + " equipped " + getName() + " which boosts defense by " + defenseBoost);
        characterDomain.getStats().setDefense(characterDomain.getStats().getDefense() + defenseBoost);
    }

    @Override
    public String toString() {
        return getName() + " (Defense Boost: " + defenseBoost + ")";
    }
}
