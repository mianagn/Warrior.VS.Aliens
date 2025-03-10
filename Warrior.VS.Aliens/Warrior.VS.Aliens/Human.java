public class Human extends Character {
    public Human(String name ) {
        super(name, new Stats(800, 40, 10, 50, 20));
    }

    @Override
    public void attack(Character opponent) {
        super.attack(opponent);
    }
    @Override
    public void specialAttack(Character opponent) {
        int weaponBoost = (getEquippedWeapon() != null) ? getEquippedWeapon().attackBoost() : 0;
        int damage = (this.getStats().getStrength() * 2) + weaponBoost;
        System.out.println(this.getName() + " uses a special attack on " + opponent.getName() + " for " + damage+ " damage!");
        int currentMana = getStats().getMana();
        this.getStats().setMana(currentMana - 10);
        opponent.takeDamage(this.getStats().getStrength() * 2);
    }
}
