package Domain;

public class Human extends CharacterDomain {
    public Human(String name ) {
        super(name,new Stats(800, 40, 10, 50, 20));
    }

    @Override
    public void attack(CharacterDomain opponent) {
        int weaponBoost = (getEquippedWeapon()!= null) ? getEquippedWeapon().attackBoost() : 0;
        int damage = getStats().getStrength() + weaponBoost;
        int totalDamage = damage;

        int randomAttack = (int) (Math.random() * 4) + 1;

        System.out.println(this.getName() + " attacks " + opponent.getName() + " for " + damage + " damage!");
        opponent.takeDamage(damage);

        if (randomAttack == 4) {
            System.out.println(this.getName() + " got an extra attack from Sword Specialazation.");
            opponent.takeDamage(damage);
            totalDamage += damage;
            System.out.println("Total damage dealt to " + opponent.getName() + ": " + totalDamage);
        }
    }
    @Override
    public void specialAttack(CharacterDomain opponent) {
        int weaponBoost = (getEquippedWeapon() != null) ? getEquippedWeapon().attackBoost() : 0;
        int damage = (this.getStats().getStrength() * 2) + weaponBoost;
        System.out.println(this.getName() + " uses Heroic strike on " + opponent.getName() + " for " + damage+ " damage!");
        int currentRage = getStats().getRage();
        this.getStats().setRage(currentRage - 10);
        opponent.takeDamage(this.getStats().getStrength() * 2);
    }
}
