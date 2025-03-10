public class Armor extends Item {
    private final int defenseBoost;

    public Armor(String name, int defenseBoost) {
        super(name);
        this.defenseBoost = defenseBoost;
    }

    @Override
    public void effect(Character character) {
        System.out.println(character + " equipped " + getName() + " which boosts defense by " + defenseBoost);
        character.getStats().setDefense(character.getStats().getDefense() + defenseBoost);
    }

    @Override
    public String toString() {
        return getName() + " (Defense Boost: " + defenseBoost + ")";
    }
}
