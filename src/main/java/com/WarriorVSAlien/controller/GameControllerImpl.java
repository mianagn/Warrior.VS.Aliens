package main.java.com.WarriorVSAlien.controller;
import main.java.com.WarriorVSAlien.model.Inventory;
import main.java.com.WarriorVSAlien.model.characters.CharacterDomain;
import main.java.com.WarriorVSAlien.model.items.Item;
import main.java.com.WarriorVSAlien.model.items.weapons.Weapon;
import main.java.com.WarriorVSAlien.model.items.werables.Armor;
import main.java.com.WarriorVSAlien.view.Game;
public class GameControllerImpl implements GameController{
    private final Game game;
    private GameState state ;
    public GameControllerImpl(Game game){
        this.game=game;
        this.state=new GameState("Player1");
    }
public void setState(GameState state) {
  this.state = state;
}
    public boolean isValidPlayerName(String name){
        return !name.isEmpty()&&name.matches("^[A-Za-z]+$");
    }
    private void opponentTurn(CharacterDomain opponent) {
        int randomAction = (int) (Math.random() * 3) + 1;

        if (randomAction == 3) {
            opponent.specialAttack(state.getPlayer());
        } else {
            opponent.attack(state.getPlayer());
        }
    }
    public void startBattle(CharacterDomain opponent) {
        while (state.getPlayer().getStats().getHealth() > 0 && opponent.getStats().getHealth() > 0) {
            game.printBattleStatus(opponent);
            game.printMenu();
            int choice = game.getPlayerChoice();
            handlePlayerChoice(choice, opponent);
            if (opponent.getStats().getHealth() > 0) {
                opponentTurn(opponent);
            }
        }
        // Simultaneous death
        if (state.getPlayer().getStats().getHealth() <= 0 && opponent.getStats().getHealth() <= 0) {
            System.out.println("Both you and the opponent have fallen in battle...");
            state.setGameOver(true);  // End the game
            if(state.isGameOver()){System.exit(0);}
            return;  // Exit the battle
        }
        // If the player loses the battle
        if (state.getPlayer().getStats().getHealth() <= 0) {
            System.out.println("You lost the battle...");
            state.setGameOver(true);  // End the game
            if(state.isGameOver()){System.exit(0);}
            return;  // Exit the battle
        }
        // If the player wins the battle
        System.out.println("You defeated " + opponent.getName());
        // Check if the current enemy is the last one
        if (state.getCurrentEnemyIndex() >= state.getEnemies().size() - 1) {
            System.out.println("Congratulations! You've defeated all opponents!");
            state.setGameOver(true);  // End the game
            if(state.isGameOver()){System.exit(1);}
            return;
        }
        // If there are more enemies
        boolean continueGame = game.askIfContinue(state.getCurrentEnemyIndex(), state.getEnemies().size());
        // If the player decides to quit
        if (!continueGame) {
            System.out.println("You decided to quit the game.");
            state.setGameOver(true);  // End the game
            if(state.isGameOver()){System.exit(1);}
            return;
        }
        proceedToNextEnemy();
    }
    public void startNewGame() {
        // Equip preset weapon and armor
        CharacterDomain.equipPresetItems(this.state.getPlayer());
        int currentEnemyIndex = state.getCurrentEnemyIndex();
        while (currentEnemyIndex < state.getEnemies().size()) {
            CharacterDomain currentEnemy = state.getEnemies().get(currentEnemyIndex);
            System.out.println("A new opponent appeared: " + currentEnemy.getName());
            //Start battle
            startBattle(currentEnemy);
            // Ask if the player wants to continue
            boolean continueGame =game.askIfContinue(currentEnemyIndex, state.getEnemies().size());
            if (!continueGame) {
                break; // Stop if the player doesn't want to continue
            }
         currentEnemyIndex++;
            state.setCurrentEnemyIndex(currentEnemyIndex);
        }
    }
    public void handlePlayerChoice(int choice, CharacterDomain opponent) {
        switch (choice) {
            case 1:
                state.getPlayer().attack(opponent);
                break;
            case 2:
                if (state.getPlayer().getStats().getRage() >= 10) {
                    state.getPlayer().specialAttack(opponent);
                } else {
                    System.out.println("Not enough Rage!");
                }
                break;
            case 3:
                game.useItem();
                break;
            case 4:
                System.out.println("You ran away!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    private void proceedToNextEnemy() {
        int currentEnemyIndex = state.getCurrentEnemyIndex();
        currentEnemyIndex++;
        state.setCurrentEnemyIndex(currentEnemyIndex);
        startBattle(state.getCurrentEnemy());
    }
    public void useItemLogic(int itemChoice) {
        Inventory<Item> inventory = state.getPlayer().getInventory();
        Item selectedItem = inventory.getItems().get(itemChoice - 1); // Adjust for 1-based indexing
        //within the range of the inventory
        if (itemChoice < 1 || itemChoice > inventory.getItems().size()) {
            System.out.println("Invalid choice. Please choose a valid item.");
            return;
        }
        // Check if the item is already equipped
        if (selectedItem instanceof Weapon || selectedItem instanceof Armor) {
            System.out.println("You have already equipped " + selectedItem.getName() + ". Please choose another item.");
            itemChoice = game.getItemChoiceForInventory(inventory.getItems().size());
            useItemLogic(itemChoice);
            return;
        }
        //If the item is usable, apply the effect
        selectedItem.effect(state.getPlayer());
        //Remove the item from the inventory after use
        inventory.removeItem(selectedItem);
    }
}
