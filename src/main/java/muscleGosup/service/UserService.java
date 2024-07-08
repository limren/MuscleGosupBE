package muscleGosup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import muscleGosup.model.CustomUserDetails;
import muscleGosup.model.User;
import muscleGosup.model.WorkoutSession;
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


    public List<WorkoutSession> getWorkoutSessions() throws IllegalAccessException {
        User user = this.getAuthenticatedUser();
        return user.getWorkoutSessions();

    }

    public User getAuthenticatedUser() throws IllegalAccessException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof CustomUserDetails)){
            throw new IllegalAccessException("Couldn't retrieve authenticated user");
        }
        Long userId = ((CustomUserDetails)principal).getId();
        return commonService.getUserById(userId);  
    }
}
