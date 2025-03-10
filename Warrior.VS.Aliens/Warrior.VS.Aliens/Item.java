public abstract class Item {
    private final String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public abstract void effect(Character character);
    @Override
    public String toString() {
        return name;
    }

}
