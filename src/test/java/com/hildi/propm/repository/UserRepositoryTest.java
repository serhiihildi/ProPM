import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hildi.propm.model.User;
import com.hildi.propm.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder().id(1L).firstName("John").lastName("Doe").build();
    }

    @Test
    @DisplayName("Test save user")
    void testSaveUser() {
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertEquals(savedUser, user);
    }

    @Test
    @DisplayName("Test find all users")
    void testFindAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        List<User> foundUsers = userRepository.findAll();
        assertNotNull(foundUsers);
        assertEquals(foundUsers, userList);
    }

    @Test
    @DisplayName("Test find user by id")
    void testFindUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> foundUser = userRepository.findById(1L);
        assertNotNull(foundUser);
        assertTrue(foundUser.isPresent());
        assertEquals(foundUser.get(), user);
    }

    @Test
    @DisplayName("Test delete user")
    void testDeleteUser() {
        userRepository.deleteById(1L);
        assertFalse(userRepository.findById(1L).isPresent());
    }

}
