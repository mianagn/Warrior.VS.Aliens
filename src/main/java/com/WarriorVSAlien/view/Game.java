package main.java.com.WarriorVSAlien.view;

import main.java.com.WarriorVSAlien.controller.GameController;
import main.java.com.WarriorVSAlien.controller.GameControllerImpl;
import main.java.com.WarriorVSAlien.model.*;
import main.java.com.WarriorVSAlien.model.characters.CharacterDomain;
import main.java.com.WarriorVSAlien.model.items.werables.Armor;
import main.java.com.WarriorVSAlien.model.items.Item;
import main.java.com.WarriorVSAlien.model.items.weapons.Weapon;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Game {

    private GameControllerImpl gameController;
    private final Scanner scanner = new Scanner(System.in);
    private boolean started = false;

    public void initializeGame() {
        printAsciiArt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("In a war-torn galaxy, warriors rise to claim their glory...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("You are the last hope of humanity...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.print("What is your name, warrior? ");
        String playerName = getValidPlayerName();
        gameController = new GameControllerImpl();
    }

    public Game() {
    }


    private String getValidPlayerName() {
        String name;
        while (true) {
            name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please enter a valid name.");
            } else if (!name.matches("^[A-Za-z]+$")) {
                System.out.println("Invalid name. Use only letters (no numbers or special characters).");
            } else {
                break;
            }
        }
        return name;
    }

    public void startGame() {
        //player = new main.java.com.WarriorVSAlien.model.characters.Human("TestPlayer");
        this.initializeGame();

        started = hasStarted();
        CharacterDomain.equipPresetItems(state.getPlayer());
        int currentEnemyIndex = 0;


        while (currentEnemyIndex < gameController.getEnemies().size()) {
            CharacterDomain currentEnemy = state.getEnemies().get(currentEnemyIndex);
            System.out.println("A new opponent appeared: " + currentEnemy.getName());


           // startBattle(currentEnemy);


            if (currentEnemyIndex != 2) {
                boolean continueGame = askIfContinue();
                if (!continueGame) {
                    System.out.println("You left the game at layer " + (currentEnemyIndex + 1));
                    break;
                }
            }


            currentEnemyIndex++;
        }

        if (! "".isEmpty()){
            system
        }


        if (currentEnemyIndex == state.getEnemies().size()) {
            System.out.println("Congratulations! You've defeated all opponents!");
        }
    }



    public boolean getStarted() {
        return started;
    }

    private void printBattleStatus(CharacterDomain opponent) {
        System.out.println("---------- Battle Status ----------");
        System.out.println(state.getPlayer().getName() + " HP: " + state.getPlayer().getStats().getHealth() + "   Rage: " + state.getPlayer().getStats().getRage());
        System.out.println(opponent.getName() + " HP: " + opponent.getStats().getHealth());
        System.out.println("-----------------------------------");
    }

    private void printMenu() {
        System.out.println("Choose an action:");
        System.out.println("1. Attack");
        System.out.println("2. Special Attack");
        System.out.println("3. Use main.java.com.WarriorVSAlien.model.items.Item");
        System.out.println("4. Run");
    }

    private int getPlayerChoice() {
        int choice = -1;
        while (choice < 1 || choice > 4) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice. Please choose a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        return choice;
    }

    private void useItem() {
        System.out.println("Choose an item to use:");
        Inventory<Item> inventory = state.getPlayer().getInventory();
        for (int i = 0; i < inventory.getItems().size(); i++) {
            System.out.println((i + 1) + ". " + inventory.getItems().get(i).getName());
        }

        int itemChoice = -1;
        boolean validChoice = false;

        while (!validChoice) {
            try {
                System.out.print("Enter item number: ");
                itemChoice = scanner.nextInt();
                if (itemChoice < 1 || itemChoice > inventory.getItems().size()) {
                    System.out.println("Invalid choice, please try again.");
                } else {
                    Item selectedItem = inventory.getItems().get(itemChoice - 1);
                    if (selectedItem instanceof Weapon || selectedItem instanceof Armor) {
                        System.out.println("You have already equipped " + selectedItem.getName() + ". Please choose another item.");
                    } else {
                        selectedItem.effect(state.getPlayer());
                        inventory.removeItem(selectedItem);
                        validChoice = true;
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
                scanner.nextLine();
            }
        }
    }

    /*private void handlePlayerChoice(int choice, CharacterDomain opponent) {
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
                useItem();
                break;
            case 4:
                System.out.println("You ran away!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }*/

    private void opponentTurn(CharacterDomain opponent) {
        int randomAction = (int) (Math.random() * 3) + 1;

        if (randomAction == 3) {
            opponent.specialAttack(state.getPlayer());
        } else {
            opponent.attack(state.getPlayer());
        }
    }

    private boolean askIfContinue() {
        System.out.print("Do you want to continue? (yes/no): ");
        String choice = scanner.nextLine().toLowerCase();
        while (!choice.equals("yes") && !choice.equals("no")) {
            System.out.print("Invalid input. Please enter 'yes' or 'no': ");
            choice = scanner.nextLine().toLowerCase();
        }
        return choice.equals("yes");
    }

    public static void printAsciiArt() {
        System.out.println("         __.,,------.._");
        System.out.println("      ,\"   _      _   \"`.");
        System.out.println("     /.__, ._  -=- _\"`    Y");
        System.out.println("    (.____.-.`      \"`   j");
        System.out.println("     VvvvvvV`.Y,.    _.,-'       ,     ,     ,");
        System.out.println("        Y    ||,   '\"\\         ,/    ,/    ./");
        System.out.println("        |   ,'  ,     `-..,'_,'/___,'/   ,'/   ,");
        System.out.println("   ..  ,;,,',-'\\,'  ,  .     '     ' \"' '--,/    .. ..");
        System.out.println(" ,'. `.`---'     `, /  , Y -=-    ,'   ,   ,. .`-..||_|| ..");
        System.out.println("ff\\\\`. `._        /f ,'j j , ,' ,   , f ,  \\=\\ Y   || ||`||_..");
        System.out.println("l` \\` `.`.\"`-..,-' j  /./ /, , / , / /l \\   \\\\l   || `' || ||...");
        System.out.println(" `  `   `-._ `-.,-/ ,' /`\"/-/-/-/-\"'''\".`.  `'.\\--`'--..`'_`' || ,");
        System.out.println("            \"`-_,',  ,'  f    ,   /      `._    ``._     ,  `-.`'//         ,");
        System.out.println("          ,-'\"'' _.,-'    l_,-'_,,'          \"`-._ . \"`. /|     `.'\\ ,       |");
        System.out.println("        ,',.,-\"'          \\=) ,`-.         ,    `-'._`.V |       \\ // .. . /j");
        System.out.println("        |f\\\\               `._ )-.\"`.     /|         `.| |        `.`-||-\\\\/");
        System.out.println("        l` \\`                 \"`._   \"`--' j          j' j          `-`---'");
        System.out.println("         `  `                     \"`,-  ,'/       ,-\"'  /");

    }

    public boolean hasStarted() {
        return true;
    }

}
