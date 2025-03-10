public class HealthPotion extends Item {
    private final int healingAmount;

    public HealthPotion(String name, int healingAmount) {
        super(name);
        this.healingAmount = healingAmount;
    }
    @Override
    public void effect(Character character) {
        int currentHealth = character.getStats().getHealth();
        int maxHealth = character.getMaxHealth();
        int newHealth = Math.min(currentHealth + healingAmount, maxHealth);
        character.getStats().setHealth(newHealth);
        System.out.println(character.getName() + " used a health potion and restored " + healingAmount + " health!");
    }

}
