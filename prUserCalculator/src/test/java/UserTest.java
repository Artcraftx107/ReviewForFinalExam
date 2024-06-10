import calculator.User;
import calculator.UserRepository;
import calculator.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
}