import calculator.User;
import calculator.UserRepository;
import calculator.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserTest {
    private UserRepository userRepository;
    private UserService userService;
    private User user;

    @BeforeEach
    public void setUp(){
        userRepository=mock(UserRepository.class);
        user=new User(1, "John Doe", "john@example.com");
        userService=new UserService(userRepository);

    }

    @AfterEach
    public void tearDown(){
        user=null;
        userRepository=null;
        userService=null;
    }

    @Test
    void testGetUserById(){
        when(userRepository.findById(1)).thenReturn(user);
        User foundUser = userService.getUserById(1);
        assertNotNull(foundUser);
        assertEquals(user, foundUser);
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testSaveUser(){
        userService.saveUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUser(){
        userService.deleteUser(1);
        verify(userRepository, times(1)).delete(1);
    }

    @Test
    void testUpdateUser(){
        user.setName("John Doe");
        user.setEmail("jayden@gmail.com");
        userService.updateUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetNonExistingUserById(){
        when(userRepository.findById(2)).thenReturn(null);
        User foundUser = userService.getUserById(2);
        assertNull(foundUser);
        verify(userRepository, times(1)).findById(2);
    }

    @Test
    void testDeleteNonExistingUser(){
        doNothing().when(userRepository).delete(2);
        userService.deleteUser(2);
        verify(userRepository, times(1)).delete(2);
    }

    @Test
    void testSaveUserThrowsException(){
        doThrow(new RuntimeException("Database error")).when(userRepository).save(user);
        assertThrows(RuntimeException.class, ()-> userService.saveUser(user));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetAllUsers(){
        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);
        List<User> retrievedUsers = userService.getAllUsers();
        assertEquals(users, retrievedUsers);
        verify(userRepository, times(1)).findAll();
    }
}