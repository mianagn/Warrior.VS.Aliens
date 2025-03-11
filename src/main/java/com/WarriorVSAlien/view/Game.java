package main.java.com.WarriorVSAlien.view;

import main.java.com.WarriorVSAlien.controller.GameControllerImpl;
import main.java.com.WarriorVSAlien.controller.GameState;
import main.java.com.WarriorVSAlien.model.*;
import main.java.com.WarriorVSAlien.model.characters.CharacterDomain;
import main.java.com.WarriorVSAlien.model.items.Item;
import main.java.com.WarriorVSAlien.model.items.potions.HealthPotion;
import main.java.com.WarriorVSAlien.model.items.potions.RagePotion;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Game {
    private GameControllerImpl gameController;
    private  Scanner scanner;
    private GameState state;
    public Game() {}
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
        this.gameController = new GameControllerImpl(this);
        scanner=new Scanner(System.in);
        String playerName = getValidPlayerName();
        this.state= new GameState(playerName);
        gameController.setState(this.state);
    }
    private String getValidPlayerName() {
        System.out.println("What is your name, warrior ?");
        String name = scanner.nextLine().trim();
        while (true) {
            if(gameController.isValidPlayerName(name)){
                return name;
            }
            System.out.println("Invalid name. Please don't include numbers and/or special characters.");
            name=scanner.nextLine().trim();
        }
    }
    public void startNewGame() {
        gameController.startNewGame();
    }
    public void printBattleStatus(CharacterDomain opponent) {
        System.out.println("---------- Battle Status ----------");
        System.out.println(state.getPlayer().getName() + " HP: " + state.getPlayer().getStats().getHealth() + "   Rage: " + state.getPlayer().getStats().getRage());
        System.out.println(opponent.getName() + " HP: " + opponent.getStats().getHealth());
        System.out.println("-----------------------------------");
    }
    public void printMenu() {
        System.out.println("Choose an action:");
        System.out.println("1. Attack");
        System.out.println("2. Special Attack");
        System.out.println("3. Use Item");
        System.out.println("4. Run");
    }
    public int getPlayerChoice() {
        int choice = -1;
        while (choice < 1 || choice > 4) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();  // Get the player's choice
                scanner.nextLine(); // Consume the leftover newline
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice. Please choose a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        return choice;
    }
    public void useItem() {
        // Check if the player has any potions before allowing them to use the inventory
        Inventory<Item> inventory = state.getPlayer().getInventory();
        boolean hasPotion = false;

        for (Item item : inventory.getItems()) {
            if (item instanceof HealthPotion || item instanceof RagePotion) {  // Assuming your potions are a subclass of Item
                hasPotion = true;
                break;
            }
        }

        if (!hasPotion) {
            System.out.println("You are out of potions and may not access the inventory.");
            return;  // Exit the method to avoid showing the inventory
        }

        System.out.println("Choose an item to use:");

        for (int i = 0; i < inventory.getItems().size(); i++) {
            System.out.println((i + 1) + ". " + inventory.getItems().get(i).getName());
        }

        int itemChoice =getPlayerChoice(); // Get valid item choice from user
        gameController.useItemLogic(itemChoice); // Pass the choice to the controller for processing
    }
    public int getItemChoiceForInventory(int inventorySize) {
        int itemChoice = -1;
        while (itemChoice < 1 || itemChoice > inventorySize) {
            try {
                System.out.print("Enter item number: ");
                itemChoice = scanner.nextInt(); // Read the item choice
                scanner.nextLine(); // Consume the leftover newline after nextInt()
                System.out.println("You selected item number: " + itemChoice); // Debug line

                if (itemChoice < 1 || itemChoice > inventorySize) {
                    System.out.println("Invalid choice. Please select a valid item.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        return itemChoice;
    }
    public boolean askIfContinue(int currentEnemyIndex, int totalEnemies) {
        // If we're on the last enemy, don't ask the player if they want to continue
        if (currentEnemyIndex >= totalEnemies - 1) {
            return false; // Game ends after defeating the last enemy
        }

        String response;
        while (true) {
            System.out.println("Do you want to continue? yes/no");
            response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                return true;
            } else if (response.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
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


    public GameState getState() {
        return this.state;
    }
}
