import auctionsys.*;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AuctionSystem auctionSystem = new AuctionSystem();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n* DISCLAIMER: " +
                "\n* This is a mock project created for educational purposes only. " +
                "\n* It does not implement real security measures and should not be used " +
                "\n* as-is in a production environment. Passwords and other sensitive information " +
                "\n* are handled in an insecure manner for demonstration purposes."
        );
        // Load existing data
        try {
            auctionSystem.loadUsersFromFile("usersData.txt");
            auctionSystem.loadItemsFromFile("itemsData.txt");
        } catch (IOException e) {
            System.err.println("\nError loading data: " + e.getMessage());
        }

        while (true) {
            System.out.println("\nWelcome to the Auction System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Export User Info");
            System.out.println("4. Export Item Info");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter role (user/admin): ");
                    String role = scanner.nextLine();
                    try {
                        auctionSystem.registerUser(username, password, role);
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();
                    try {
                        User user = auctionSystem.login(username, password);
                        handleUserActions(auctionSystem, user);
                    } catch (NoSuchElementException | IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    exportUserInfo(auctionSystem);
                    break;
                case 4:
                    exportItemInfo(auctionSystem);
                    break;
                case 5:
                    // Save data before exiting
                    try {
                        auctionSystem.saveUsersToFile("usersData.txt");
                        auctionSystem.saveItemsToFile("itemsData.txt");
                    } catch (IOException e) {
                        System.err.println("Error saving data: " + e.getMessage());
                    }
                    System.out.println("Thank you for using the Auction System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleUserActions(AuctionSystem auctionSystem, User user) {
        Scanner scanner = new Scanner(System.in);
        boolean isAdmin = user.getRole().equalsIgnoreCase("admin");

        while (true) {
            System.out.println("\nWelcome, " + user.getUsername());
            System.out.println("1. View Items");
            System.out.println("2. Place Bid");
            if (isAdmin) {
                System.out.println("3. Add Item");
                System.out.println("4. Start Auction");
                System.out.println("5. Close Auction");
            }
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewItems(auctionSystem);
                    break;
                case 2:
                    placeBid(auctionSystem, user);
                    break;
                case 3:
                    if (isAdmin) {
                        addItem(auctionSystem);
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;
                case 4:
                    if (isAdmin) {
                        startAuction(auctionSystem);
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;
                case 5:
                    if (isAdmin) {
                        closeAuction(auctionSystem);
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewItems(AuctionSystem auctionSystem) {
        List<Item> items = auctionSystem.getItems();
        if (items.isEmpty()) {
            System.out.println("No items available.");
        } else {
            for (Item item : items) {
                System.out.println(item);
            }
        }
    }

    private static void placeBid(AuctionSystem auctionSystem, User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        try {
            Item item = auctionSystem.searchItemByName(itemName);
            System.out.print("Enter bid amount: ");
            double bidAmount = scanner.nextDouble();
            auctionSystem.placeBid(user, item, bidAmount);
        } catch (NoSuchElementException e) {
            System.out.println("Item not found.");
        }
    }

    private static void addItem(AuctionSystem auctionSystem) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter item description: ");
        String description = scanner.nextLine();
        System.out.print("Enter starting price: ");
        double startingPrice = scanner.nextDouble();
        auctionSystem.addItem(itemName, description, startingPrice);
        System.out.println("Item added successfully.");
    }

    private static void startAuction(AuctionSystem auctionSystem) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        try {
            Item item = auctionSystem.searchItemByName(itemName);
            auctionSystem.startAuction(item);
        } catch (NoSuchElementException e) {
            System.out.println("Item not found.");
        }
    }

    private static void closeAuction(AuctionSystem auctionSystem) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        try {
            Item item = auctionSystem.searchItemByName(itemName);
            auctionSystem.closeAuction(item);
        } catch (NoSuchElementException e) {
            System.out.println("Item not found.");
        }
    }

    private static void exportUserInfo(AuctionSystem auctionSystem) {
        try {
            auctionSystem.saveUsersToFile("usersData.txt");
            System.out.println("User information has been exported to usersData.txt");
        } catch (IOException e) {
            System.err.println("Error exporting user information: " + e.getMessage());
        }
    }

    private static void exportItemInfo(AuctionSystem auctionSystem) {
        try {
            auctionSystem.saveItemsToFile("itemsData.txt");
            System.out.println("Item information has been exported to itemsData.txt");
        } catch (IOException e) {
            System.err.println("Error exporting item information: " + e.getMessage());
        }
    }
}