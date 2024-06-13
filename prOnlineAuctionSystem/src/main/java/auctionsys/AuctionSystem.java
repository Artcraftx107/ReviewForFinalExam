/*
 * DISCLAIMER:
 * This is a mock project created for educational purposes only.
 * It does not implement real security measures and should not be used
 * as-is in a production environment. Passwords and other sensitive information
 * are handled in an insecure manner for demonstration purposes.
 */
package auctionsys;

import java.io.*;
import java.util.*;

public class AuctionSystem {
    private final List<User> users;
    private final List<Item> items;

    public AuctionSystem(){
        this.users=new ArrayList<>();
        this.items=new ArrayList<>();
    }

    //Getters


    public List<Item> getItems() {
        return items;
    }

    public List<User> getUsers() {
        return users;
    }

    //Management methods
    public void registerUser(String username, String password, String role){
        User newUser = new User(username, password, role);
        try{
            for (User user : users){
                if(user.getUsername().equals(username)&&user.getPassword().equals(password)){
                    throw new IllegalArgumentException("User "+username+" is already registered");
                }
            }
            users.add(newUser);
            System.out.println("User "+username+" registered successfully");
        }catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }

    public User login(String username, String password){
         User user = searchUserByUsername(username);
         if(user.getPassword().equals(password)){
             return user;
         }else {
             throw new IllegalArgumentException("Incorrect password");
         }
    }

    private User searchUserByUsername(String username){
        boolean found = false;
        int pos = 0;
        while(pos<users.size()&&!found){
            if(username.equals(users.get(pos).getUsername())){
                found=true;
            }else{
                pos++;
            }
        }
        if(!found){
            throw new NoSuchElementException("There is no user with the username: "+username);
        }
        return users.get(pos);
    }

    //Item management
    public void addItem(String itemName, String description, double startingPrice){
        Item item = new Item(itemName, description, startingPrice);
        items.add(item);
    }

    public void startAuction(Item item){
        item.setAuctionStatus(true);
        System.out.println("Auction for the item "+item.getItemName()+" has started at a price of $"+item.getStartingPrice());
    }

    public void placeBid(User user, Item item, double bidAmount){
        if (user == null) {
            System.out.println("User not logged in.");
            return;
        }
        if (!item.isAuctionStatus()) {
            System.out.println("Auction for this item is not active.");
            return;
        }
        if (bidAmount>item.getCurrentHighestBid()) {
            System.out.println(user.getUsername() + " placed a bid of $" + bidAmount + " on item " + item.getItemName());
            item.setCurrentHighestBid(bidAmount);
        } else {
            System.out.println("Failed to place bid for user " + user.getUsername() + " on item " + item.getItemName());
        }
    }

    public void closeAuction(Item item){
        item.setAuctionStatus(false);
        System.out.println("The auction for the item "+item.getItemName()+" is now closed");
    }


    public Item searchItemByName(String itemName) {
        boolean found = false;
        int pos = 0;
        while(pos<items.size()&&!found){
            if(itemName.equals(items.get(pos).getItemName())){
                found=true;
            }else{
                pos++;
            }
        }
        if(!found){
            throw new NoSuchElementException("There is no user with the username: "+itemName);
        }
        return items.get(pos);
    }
    //File saving and loading methods
    public void saveItemsToFile(String filename)throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            for(Item item : items){
                bw.write(item.toString());
                bw.newLine();
            }
        }
    }

    public void loadItemsFromFile(String filename)throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while((line=br.readLine())!=null){
                try {
                    String[] parts = line.split(",");
                    if(parts.length<3){
                        throw new InvalidPropertiesFormatException("There was an error reading this line");
                    }
                    try{
                        String itemname = parts[0];
                        String description=parts[1];
                        double startingPrice = Double.parseDouble(parts[2]);
                        Item item = new Item(itemname, description, startingPrice);
                        if(!items.contains(item)){
                            items.add(item);
                        }
                    }catch (NumberFormatException e){
                        System.err.println("Error processing the item's info in the current line");
                    }
                }catch (InvalidPropertiesFormatException e){
                    System.err.println(e.getMessage());
                }
            }
        }catch (IOException e){
            throw new FileNotFoundException("The file "+filename+" was not found");
        }
    }

    public void saveUsersToFile(String filename)throws IOException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            for(User user : users){
                bw.write(user.toString());
                bw.newLine();
            }
        }
    }

    public void loadUsersFromFile(String filename)throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while((line=br.readLine())!=null){
                try {
                    String[] parts = line.split(",");
                    if(parts.length<3){
                        throw new InvalidPropertiesFormatException("There was an error processing the current line");
                    }
                    users.add(new User(parts[0], parts[1], parts[2]));
                }catch (InvalidPropertiesFormatException e){
                    System.err.println(e.getMessage());
                }
            }
        }catch (IOException e){
            throw new FileNotFoundException("The file "+filename+" was not found");
        }
    }
}