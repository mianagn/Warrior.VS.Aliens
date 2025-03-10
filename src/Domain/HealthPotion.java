package Domain;

public class HealthPotion extends Item {
    private final int healingAmount;

    public HealthPotion(String name, int healingAmount) {
        super(name);
        this.healingAmount = healingAmount;
    }
    @Override
    public void effect(CharacterDomain characterDomain) {
        int currentHealth = characterDomain.getStats().getHealth();
        int maxHealth = characterDomain.getMaxHealth();
        int newHealth = Math.min(currentHealth + healingAmount, maxHealth);
        characterDomain.getStats().setHealth(newHealth);
        System.out.println(characterDomain.getName() + " used a health potion and restored " + healingAmount + " health!");
    }

}
