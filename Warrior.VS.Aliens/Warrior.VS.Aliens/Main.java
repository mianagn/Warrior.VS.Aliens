

public class Main {
    public static void main(String[] args){
        Character player= new Human("Player");
        Character.equipPresetItems(player);
        Game game= new Game(player);
        game.startGame();
    }
}
