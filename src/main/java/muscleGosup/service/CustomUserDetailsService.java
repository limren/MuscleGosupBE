package muscleGosup.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import muscleGosup.model.CustomUserDetails;
import muscleGosup.model.User;
import muscleGosup.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
     @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
