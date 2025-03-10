public class Alien extends Character {
    private static final Stats[] ALIEN_STATS = {
            new Stats(600, 35, 8, 30, 15),  // main.java.com.WarriorVSAlien.model.characters.Alien 1
            new Stats(750, 40, 12, 40, 18), // main.java.com.WarriorVSAlien.model.characters.Alien 2
            new Stats(900, 50, 15, 50, 20)  // main.java.com.WarriorVSAlien.model.characters.Alien 3
    };
    private static int alienCount = 0;
    public Alien(String name) {
        super(name, getNextStats());
    }
    private static Stats getNextStats() {
        if (alienCount >= ALIEN_STATS.length) {
            alienCount = 0;
        }
        return ALIEN_STATS[alienCount++];
    }

    @Override
    public void attack(Character opponent) {
        super.attack(opponent);
    }

    @Override
    public void specialAttack(Character opponent) {
        System.out.println(this.getName() + " uses a special attack on " + opponent.getName());
        int currentMana = getStats().getMana();
        this.getStats().setMana(currentMana - 10);
        System.out.println("Current mana: " + this.getStats().getMana());
        opponent.takeDamage(this.getStats().getStrength() * 2);
    }
}
