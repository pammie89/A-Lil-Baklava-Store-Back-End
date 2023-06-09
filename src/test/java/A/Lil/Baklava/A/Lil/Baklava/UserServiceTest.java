package A.Lil.Baklava.A.Lil.Baklava;

import A.Lil.Baklava.A.Lil.Baklava.model.request.LoginRequest;
import A.Lil.Baklava.A.Lil.Baklava.repository.UserRepository;
import A.Lil.Baklava.A.Lil.Baklava.security.MyUserDetails;
import A.Lil.Baklava.A.Lil.Baklava.security.UserContext;
import A.Lil.Baklava.A.Lil.Baklava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @MockBean
    LoginRequest loginRequest;

    @MockBean
    UserContext userContext;

    @MockBean
    MyUserDetails myUserDetails;

//    @Test
//    @DisplayName("Password must be converted to jwt token before saving")
//    public void passwordMustBeConvertedToJwtTokenBeforeSave() {
//        User user = new User("username", "pam@gmail.com", "123456");
//        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
//        User createUser = userService.createUser(user);
//        Assertions.assertNotEquals(createUser.getPassword(), "123456");
//        verify(userRepository, times(1)).save(Mockito.any(User.class));
//
//    }
//
//    @Test
//    @DisplayName("login user unsuccessfully when user account is inactive")
//    public void userAccountMustBeActiveToLogin() {
//        User inActiveUser = new User("tim", "tim@hotmail.com", "tim123");
//        inActiveUser.setActive(false);
//        LoginRequest loginRequest = new LoginRequest("tim@hotmail.com", "tim123");
//        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(inActiveUser));
//
//        Assertions.assertThrows(InformationNotFoundException.class, () -> userService.loginUser(loginRequest));
//    }
//
//    @Test
//    @DisplayName("Deactivate user account when user account is active")
//    public void testDeactivateUserAccountSuccessfully() {
//        User activeUser = new User("tim", "tim@hotmail.com", "tim123");
//        activeUser.setActive(true);
//        when(userContext.getCurrentLoggedInUser()).thenReturn(activeUser);
//
//        ResponseEntity<?> response = userService.setUserToInactive();
//        Assertions.assertEquals(200, response.getStatusCodeValue());
//        Assertions.assertFalse(activeUser.getActive());
//    }
//
//    @Test
//    @DisplayName("Deactivate user account throws an exception when user account is inactive")
//    public void testDeactivateUserAccountUnsuccessfully() {
//        User activeUser = new User("tim", "tim@hotmail.com", "tim123");
//        activeUser.setActive(false);
//        when(userContext.getCurrentLoggedInUser()).thenReturn(activeUser);
//
//        Assertions.assertThrows(InformationNotFoundException.class, () -> userService.setUserToInactive());
//    }
//
//    @Test
//    @DisplayName("check if user is not blank")
//    public void updateUserNameNotBlank() {
//        User currentLoggedUser = new User("tim", "tim@hotmail.com", "tim123");
//        when(userContext.getCurrentLoggedInUser()).thenReturn(currentLoggedUser);
//        User userObject = new User();
//        userObject.setName("mark");
//        when(userRepository.save(Mockito.any(User.class))).thenReturn(currentLoggedUser);
//        User returnUser = userService.updateUsername(userObject);
//
//        Assertions.assertEquals("mark", returnUser.getName());
//    }
//
//    @Test
//    @DisplayName("check if user is a valid username")
//    public void updateUserNameIsValid() {
//        User currentLoggedUser = new User("tim", "tim@hotmail.com", "tim123");
//        when(userContext.getCurrentLoggedInUser()).thenReturn(currentLoggedUser);
//        User userObject = new User();
//        userObject.setName("");
//        when(userRepository.save(Mockito.any(User.class))).thenReturn(currentLoggedUser);
//
//        Assertions.assertThrows(InformationInvalidException.class, () -> userService.updateUsername(userObject));
//    }
//
}
