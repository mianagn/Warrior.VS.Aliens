package main.java.com.WarriorVSAlien.model.items;

import main.java.com.WarriorVSAlien.model.characters.CharacterDomain;

public abstract class Item {
    private final String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public abstract void effect(CharacterDomain characterDomain);
    @Override
    public String toString() {
        return name;
    }

}
