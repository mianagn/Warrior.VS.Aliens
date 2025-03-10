import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Character player;
    private final List<Character> enemies;
    private final Scanner scanner;

    public Game(Character player) {
        this.player = player;
        this.scanner = new Scanner(System.in);


        this.enemies = new ArrayList<>();
        this.enemies.add(new Alien("Zorath the Conqueror"));
        this.enemies.add(new Alien("Xyloxis the Voidbringer"));
        this.enemies.add(new Alien("Kaldrix the Devourer"));
    }

    public void startGame() {
        int currentEnemyIndex = 0;

        while (currentEnemyIndex < enemies.size()) {
            Character currentEnemy = enemies.get(currentEnemyIndex);
            System.out.println("A new opponent appeared: " + currentEnemy.getName());


            startBattle(currentEnemy);


            if (currentEnemyIndex != 2) {
                boolean continueGame = askIfContinue();
                if (!continueGame) {
                    System.out.println("You left the game at layer " + (currentEnemyIndex + 1));
                    break;
                }
            }


            currentEnemyIndex++;
        }


        if (currentEnemyIndex == enemies.size()) {
            System.out.println("Congratulations! You've defeated all opponents!");
        }
    }


    private void startBattle(Character opponent) {
        while (player.getStats().getHealth() > 0 && opponent.getStats().getHealth() > 0) {
            printBattleStatus(opponent);
            printMenu();
            int choice = getPlayerChoice();
            handlePlayerChoice(choice, opponent);

            if (opponent.getStats().getHealth() > 0) {
                opponentTurn(opponent);
            }
        }


        if (player.getStats().getHealth() <= 0) {
            System.out.println(player.getName() + " has been defeated.");
            System.out.println("App.Game Over! You lost.");
            System.exit(0);
        } else {
            System.out.println(opponent.getName() + " has been defeated.");
        }
    }

    private void printBattleStatus(Character opponent) {
        System.out.println("---------- Battle Status ----------");
        System.out.println(player.getName() + " HP: " + player.getStats().getHealth() + "   Mana: " + player.getStats().getMana());
        System.out.println(opponent.getName() + " HP: " + opponent.getStats().getHealth());
        System.out.println("-----------------------------------");
    }

    private void printMenu() {
        System.out.println("Choose an action:");
        System.out.println("1. Attack");
        System.out.println("2. Special Attack");
        System.out.println("3. Use Domain.Item");
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
                scanner.nextLine(); //
            }
        }
        return choice;
    }

    private void useItem() {
        System.out.println("Choose an item to use:");
        Inventory<Item> inventory = player.getInventory();
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
                        selectedItem.effect(player);
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

    private void handlePlayerChoice(int choice, Character opponent) {
        switch (choice) {
            case 1:
                player.attack(opponent);
                break;
            case 2:
                if (player.getStats().getMana() >= 10) {
                    player.specialAttack(opponent);
                } else {
                    System.out.println("Not enough mana!");
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
    }

    private void opponentTurn(Character opponent) {
        opponent.attack(player);
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
}
