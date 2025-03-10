package main.java.com.WarriorVSAlien.model;

public class Stats {
    private int health;
    private int strength;
    private final int agility;
    private int rage;
    private int defense;
    public Stats(int health, int strength, int agility,int rage,int defense) {
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.defense=defense;
        this.rage=rage;

    }
    public int getAgility() { return agility; }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    public int getStrength() { return strength; }

    public void setStrength(int strength) { this.strength = strength; }

    public int getRage() { return rage; }
    public void setRage(int rage) { this.rage = rage; }
    public int getDefense() { return defense; }
    public void setDefense(int defense) { this.defense = defense; }

    @Override
    public String toString() {
        return "main.java.com.WarriorVSAlien.model.Stats{" +
                "health=" + health +
                ", strength=" + strength +
                ", agility=" + agility +
                ", defense=" + defense +
                ", Rage=" + rage +
                '}';
    }
}
