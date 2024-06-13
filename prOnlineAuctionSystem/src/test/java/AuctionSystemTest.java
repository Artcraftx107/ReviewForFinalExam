import auctionsys.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class AuctionSystemTest {
    private AuctionSystem auctionSystem;

    @BeforeEach
    public void setUp() {
        auctionSystem = new AuctionSystem();
    }

    @Test
    public void testRegisterUser() {
        auctionSystem.registerUser("testUser", "password", "user");
        assertEquals(1, auctionSystem.getUsers().size());
        assertEquals("testUser", auctionSystem.getUsers().get(0).getUsername());
    }

    @Test
    public void testLogin() {
        auctionSystem.registerUser("testUser", "password", "user");
        User user = auctionSystem.login("testUser", "password");
        assertEquals("testUser", user.getUsername());
    }

    @Test
    public void testLoginWithIncorrectPassword() {
        auctionSystem.registerUser("testUser", "password", "user");
        assertThrows(IllegalArgumentException.class, () -> {
            auctionSystem.login("testUser", "wrongPassword");
        });
    }

    @Test
    public void testLoginWithNonExistingUser() {
        assertThrows(NoSuchElementException.class, () -> {
            auctionSystem.login("nonExistingUser", "password");
        });
    }

    @Test
    public void testAddItem() {
        auctionSystem.addItem("item1", "description1", 10.0);
        assertEquals(1, auctionSystem.getItems().size());
        assertEquals("item1", auctionSystem.getItems().get(0).getItemName());
    }

    @Test
    public void testSearchItemByName() {
        auctionSystem.addItem("item1", "description1", 10.0);
        Item item = auctionSystem.searchItemByName("item1");
        assertEquals("item1", item.getItemName());
    }

    @Test
    public void testSearchItemByNameNotFound() {
        assertThrows(NoSuchElementException.class, () -> {
            auctionSystem.searchItemByName("nonExistingItem");
        });
    }

    @Test
    public void testStartAuction() {
        Item item = new Item("item1", "description1", 10.0);
        auctionSystem.addItem(item.getItemName(), item.getDescription(), item.getStartingPrice());
        auctionSystem.startAuction(item);
        assertTrue(item.isAuctionStatus());
    }

    @Test
    public void testPlaceBid() {
        auctionSystem.registerUser("testUser", "password", "user");
        User user = auctionSystem.login("testUser", "password");
        Item item = new Item("item1", "description1", 10.0);
        auctionSystem.addItem(item.getItemName(), item.getDescription(), item.getStartingPrice());
        auctionSystem.startAuction(item);
        auctionSystem.placeBid(user, item, 15.0);
        assertEquals(15.0, item.getCurrentHighestBid());
    }

    @Test
    public void testCloseAuction() {
        Item item = new Item("item1", "description1", 10.0);
        auctionSystem.addItem(item.getItemName(), item.getDescription(), item.getStartingPrice());
        auctionSystem.startAuction(item);
        auctionSystem.closeAuction(item);
        assertFalse(item.isAuctionStatus());
    }

    @Test
    public void testSaveAndLoadUsersToFile() throws IOException {
        auctionSystem.registerUser("testUser1", "password1", "user");
        auctionSystem.registerUser("testUser2", "password2", "admin");
        auctionSystem.saveUsersToFile("testUsersData.txt");

        AuctionSystem newAuctionSystem = new AuctionSystem();
        newAuctionSystem.loadUsersFromFile("testUsersData.txt");

        assertEquals(2, newAuctionSystem.getUsers().size());
        new File("testUsersData.txt").delete();  // Clean up
    }

    @Test
    public void testSaveAndLoadItemsToFile() throws IOException {
        auctionSystem.addItem("item1", "description1", 10.0);
        auctionSystem.addItem("item2", "description2", 20.0);
        auctionSystem.saveItemsToFile("testItemsData.txt");

        AuctionSystem newAuctionSystem = new AuctionSystem();
        newAuctionSystem.loadItemsFromFile("testItemsData.txt");

        assertEquals(2, newAuctionSystem.getItems().size());
        new File("testItemsData.txt").delete();  // Clean up
    }
}