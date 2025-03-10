package Domain;

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
    public void effect(CharacterDomain characterDomain) {
        System.out.println(characterDomain + " equipped " + getName() + " which boosts attack by " + attackBoost);
        characterDomain.getStats().setStrength(characterDomain.getStats().getStrength() + attackBoost);
    }

    @Override
    public String toString() {
        return getName() + " (Attack Boost: " + attackBoost + ")";
    }
}
