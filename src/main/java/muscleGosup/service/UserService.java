package muscleGosup.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import muscleGosup.exception.UserNotFoundException;
import muscleGosup.model.CustomUserDetails;
import muscleGosup.model.User;
import muscleGosup.repository.UserRepository;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User createUser(String username, String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    public User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof CustomUserDetails)){
            throw new UserNotFoundException("Couldn't get authenticated user");
        }
        Long userId = ((CustomUserDetails)principal).getId();
        return this.getUserById(userId).orElseThrow(() -> new UserNotFoundException("Couldn't get user from Id"));
    }

    public Map<String, Object> getAuthenticatedUserRestricted() {
        User authenticatedUser = this.getAuthenticatedUser();
        Map<String, Object> restrictedUser = new HashMap<String, Object>();
        restrictedUser.put("email", authenticatedUser.getEmail());
        restrictedUser.put("username", authenticatedUser.getUsername());
        return restrictedUser;
    }
}
