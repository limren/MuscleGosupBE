package muscleGosup.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import muscleGosup.model.CustomUserDetails;
import muscleGosup.model.User;
import muscleGosup.repository.UserRepository;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CommonService commonService;
    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, CommonService commonService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.commonService = commonService;
    }

    public User createUser(String username, String email, String password)
    {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    public User getAuthenticatedUser() throws IllegalAccessException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        if(!(principal instanceof CustomUserDetails)){
            throw new IllegalAccessException("Couldn't retrieve authenticated user");
        }
        Long userId = ((CustomUserDetails)principal).getId();
        return commonService.getUserById(userId);  
    }

    public Map<String, Object> getAuthenticatedUserRestricted() throws IllegalAccessException {
        User authenticatedUser = this.getAuthenticatedUser();
        Map<String, Object> restrictedUser = new HashMap<String, Object>();
        restrictedUser.put("email", authenticatedUser.getEmail());
        restrictedUser.put("username", authenticatedUser.getUsername());
        restrictedUser.put("workoutSessions", authenticatedUser.getWorkoutSessions());
        return restrictedUser;
    }


}
