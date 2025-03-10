package main.java.com.WarriorVSAlien.controller;

import main.java.com.WarriorVSAlien.model.characters.Alien;
import main.java.com.WarriorVSAlien.model.characters.CharacterDomain;
import main.java.com.WarriorVSAlien.model.characters.Human;

import java.util.ArrayList;
import java.util.List;

public class GameControllerImpl implements GameController{
    private GameState state ;

    public GameControllerImpl() {


        this.state.enemies = new ArrayList<>();
        this.enemies.add(new Alien("Zorath the Conqueror"));
        this.enemies.add(new Alien("Xyloxis the Voidbringer"));
        this.enemies.add(new Alien("Kaldrix the Devourer"));
        this.currentEnemyIndex = 0;
        this.gameOver = false;

    }

    public List<CharacterDomain> getEnemies() {
        return state.getEnemies();
    }

    public CharacterDomain getCurrentEnemy() {
        return enemies.get(currentEnemyIndex);
    }

    public CharacterDomain getPlayer() {
        return player;
    }

    public void setPlayer(CharacterDomain player) {
        this.player = player;
    }



    public void setEnemies(List<CharacterDomain> enemies) {
        this.enemies = enemies;
    }

    public void nextEnemy() {
        if (currentEnemyIndex < enemies.size() - 1) {
            currentEnemyIndex++;
        } else {
            gameOver = true;  // No more enemies left, game ends
        }
    }

    public boolean isLastEnemy() {
        return currentEnemyIndex >= enemies.size() - 1;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void handlePlayerChoice(int choice, CharacterDomain opponent) {
        switch (choice) {
            case 1:
                player.attack(opponent);
                break;
            case 2:
                if (player.getStats().getRage() >= 10) {
                    player.specialAttack(opponent);
                } else {
                    System.out.println("Not enough Rage!");
                }
                break;
            case 3:
             //   .useItem();
                break;
            case 4:
                System.out.println("You ran away!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void opponentTurn(CharacterDomain opponent) {
        int randomAction = (int) (Math.random() * 3) + 1;

        if (randomAction == 3) {
            opponent.specialAttack(player);
        } else {
            opponent.attack(player);
        }
    }

    public boolean getValidBattleChoice(int choice) {
        return switch (choice) {
            case 1, 2, 3 -> true;
            default -> {
                System.out.println("Invalid action. Choose 'attack','Special attack' or 'use item'.");
                yield false;
            }
        };
    }

//    public boolean getStarted() {
//        return started;
//    }
//
//    private void startBattle(CharacterDomain opponent) {
//        while (player.getStats().getHealth() > 0 && opponent.getStats().getHealth() > 0) {
//            game.printBattleStatus(opponent);
//            Game.printMenu();
//            int choice = Game.getPlayerChoice();
//            handlePlayerChoice(choice, opponent);
//
//            if (opponent.getStats().getHealth() > 0) {
//                opponentTurn(opponent);
//            }
//        }
//
//
//        if (player.getStats().getHealth() <= 0) {
//            System.out.println(player.getName() + " has been defeated.");
//            System.out.println("main.java.com.WarriorVSAlien.view.Game Over! You lost.");
//            System.exit(0);
//        } else {
//            System.out.println(opponent.getName() + " has been defeated.");
//        }
//    }
//
//    public boolean hasStarted() {
//        return true;
//    }
//
//    public boolean isValidItem(int choice) {
//        boolean valid = false;
//
//        try {
//            if (choice < 1 || choice > inventory.getItems().size()) {
//                return "Invalid choice, please try again." ;
//            } else {
//                Item selectedItem = inventory.getItems().get(choice - 1);
//                if (selectedItem instanceof Weapon || selectedItem instanceof Armor) {
//                    System.out.println("You have already equipped " + selectedItem.getName() + ". Please choose another item.");
//                } else {
//                    selectedItem.effect(player);
//                    inventory.removeItem(selectedItem);
//                    validChoice = true;
 //                       return ""
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Invalid input, please try again.");
//            scanner.nextLine();
//        }
//    }
}
