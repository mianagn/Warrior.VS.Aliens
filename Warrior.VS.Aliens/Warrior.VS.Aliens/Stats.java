public class Stats {
    private int health;
    private int strength;
    private final int agility;
    private int mana;
    private int defense;
    public Stats(int health, int strength, int agility,int mana,int defense) {
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.defense=defense;
        this.mana=mana;

    }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    public int getStrength() { return strength; }
    public void setStrength(int strength) { this.strength = strength; }

    public int getMana() { return mana; }
    public void setMana(int mana) { this.mana = mana; }
    public int getDefense() { return defense; }
    public void setDefense(int defense) { this.defense = defense; }

    @Override
    public String toString() {
        return "main.java.com.WarriorVSAlien.model.Stats{" +
                "health=" + health +
                ", strength=" + strength +
                ", agility=" + agility +
                ", defense=" + defense +
                ", mana=" + mana +
                '}';
    }
}
