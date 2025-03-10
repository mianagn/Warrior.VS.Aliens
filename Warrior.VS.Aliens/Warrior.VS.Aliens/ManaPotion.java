public class ManaPotion extends Item {
    private final int manaAmount;
    public ManaPotion(String name, int manaAmount) {
        super(name);
        this.manaAmount = manaAmount;
    }

    @Override
    public void effect(Character character) {
        int currentMana = character.getStats().getMana();
        int maxMana = character.getMaxMana();
        int newMana = Math.min(currentMana + manaAmount, maxMana);
        character.getStats().setMana(newMana);
        System.out.println(character.getName() + " used a mana potion and restored " + manaAmount + " mana!");
    }

    @Override
    public String toString() {
        return getName() + " (Restores " + manaAmount + " mana)";
    }
}
