package A.Lil.Baklava.A.Lil.Baklava.service;

import A.Lil.Baklava.A.Lil.Baklava.exception.InformationInvalidException;
import A.Lil.Baklava.A.Lil.Baklava.exception.InformationNotFoundException;
import A.Lil.Baklava.A.Lil.Baklava.model.User;
import A.Lil.Baklava.A.Lil.Baklava.model.request.LoginRequest;
import A.Lil.Baklava.A.Lil.Baklava.model.response.LoginResponse;
import A.Lil.Baklava.A.Lil.Baklava.repository.UserRepository;
import A.Lil.Baklava.A.Lil.Baklava.security.UserContext;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserContext userContext;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, UserContext userContext) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userContext = userContext;
    }

    public User createUser(User userObject) {
        if (userObject.getName().isBlank()) {
            throw new InformationInvalidException("The username cannot be empty or contain spaces.");
        }
        if (userObject.getEmail().isBlank()) {
            throw new InformationInvalidException("The email cannot be empty or contain spaces.");
        }
        if (userObject.getPassword().isBlank()) {
            throw new InformationInvalidException("The password cannot be empty or contain spaces.");
        }
        if (userObject.getPassword().length() < 6) {
            throw new InformationInvalidException("The password must contain at least 6 characters.");
        }

        userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));

        return userRepository.save(userObject);
    }

    public LoginResponse loginUser(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent() && !user.get().isActive()) {
            throw new InformationNotFoundException("The user account is inactive.");
        }

        try {
            // Perform authentication
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Create and return the login response
            return new LoginResponse("Login successful.");
        } catch (Exception e) {
            throw new InformationInvalidException("Error: user email or password is incorrect.");
        }
    }

    // Other methods
}

    public ResponseEntity<?> setUserToInactive() {
        User currentlyLoggedInUser = userContext.getCurrentLoggedInUser();
        if (currentlyLoggedInUser.isActive()) {
            currentlyLoggedInUser.setActive(false);
            userRepository.save(currentlyLoggedInUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new InformationNotFoundException("The user account is already inactive.");
        }
    }

    public User updateUsername(User userObject) {
        String updatedName = userObject.getName();
        if (!updatedName.isBlank()) {
            User currentlyLoggedInUser = userContext.getCurrentLoggedInUser();
            currentlyLoggedInUser.setName(updatedName);
            userRepository.save(currentlyLoggedInUser);
            return currentlyLoggedInUser;
        }
        throw new InformationInvalidException("User name cannot be blank.");
    }
}
