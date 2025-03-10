public class Weapon extends Item {
    private final int attackBoost;

    public Weapon(String name, int attackBoost) {
        super(name);
        this.attackBoost = attackBoost;
    }

    public int attackBoost() {
        return this.attackBoost;
    }

    @Override
    public void effect(Character character) {
        System.out.println(character + " equipped " + getName() + " which boosts attack by " + attackBoost);
        character.getStats().setStrength(character.getStats().getStrength() + attackBoost);
    }

    @Override
    public String toString() {
        return getName() + " (Attack Boost: " + attackBoost + ")";
    }
}
