
public class Character {
    private final String name;
    private final Stats stats;
    private Inventory<Item> inventory;
    private Armor equippedArmor;
    private Weapon equippedWeapon;
    private final int maxHealth;
    private final int maxMana;

    public Character(String name, Stats stats) {
        this.name = name;
        this.stats = stats;
        this.inventory = new Inventory<>();
        this.equippedArmor = null;
        this.equippedWeapon = null;


        this.maxHealth = stats.getHealth() + stats.getDefense();
        this.maxMana = stats.getMana();

        this.addItemToInventory(new Weapon("Thunderfury, blessed blade of the windseeker", 60));
        this.addItemToInventory(new Armor("Dreadnaught's Battlegear", 100));

        if (this instanceof Human) {
            for (int i = 0; i < 3; i++) {
                this.addItemToInventory(new HealthPotion("Health Potion", 200));
            }
            for (int i = 0; i < 2; i++) {
                this.addItemToInventory(new ManaPotion("Mana Potion", 40));
            }
        }
    }
    public Stats getStats() {
        return stats;
    }

    public String getName() {
        return name;
    }

    public Inventory<Item> getInventory() {
        return inventory;
    }
    public int getMaxHealth() {
        return maxHealth;
    }


    public int getMaxMana() {
        return maxMana;
    }


    public void addItemToInventory(Item item) {
        this.inventory.addItem(item);
    }

    public void equipArmor(Armor armor) {
        equippedArmor = armor;


    }
    public Armor getEquippedArmor(){
        return equippedArmor;
    }

    public void equipWeapon(Weapon weapon) {
        equippedWeapon = weapon;


    }
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void attack(Character opponent) {
        int weaponBoost = (equippedWeapon != null) ? equippedWeapon.attackBoost() : 0;
        int damage = this.stats.getStrength() + weaponBoost;
        System.out.println(this.name + " attacks " + opponent.getName() + " for " + damage + " damage!");
        opponent.takeDamage(damage);
    }

    public void specialAttack(Character opponent) {
        int weaponBoost = (equippedWeapon != null) ? equippedWeapon.attackBoost(): 0;
        int damage = (this.stats.getStrength() * 2) + weaponBoost;
        System.out.println(this.name + " uses a special attack on " + opponent.getName() + " for " + damage + " damage!");
        opponent.takeDamage(damage);
    }

    public void takeDamage(int damage) {
        int currentHealth = stats.getHealth();
        currentHealth -= damage-(getStats().getDefense()/10);
        stats.setHealth(currentHealth);
    }

    public static void equipPresetItems(Character character) {
        Weapon sword = null;
        Armor armor = null;

        for (Item item : character.getInventory().getItems()) {
            if (item instanceof Weapon && item.getName().equals("Thunderfury, blessed blade of the windseeker")) {
                sword = (Weapon) item;
            }
            if (item instanceof Armor && item.getName().equals("Dreadnaught's Battlegear")) {
                armor = (Armor) item;
            }
        }

        if (sword != null) {
            character.equipWeapon(sword);
            sword.effect(character);
        }
        if (armor != null) {
            character.equipArmor(armor);
            armor.effect(character);
        }
    }
}
