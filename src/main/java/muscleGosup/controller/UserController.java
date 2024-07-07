package muscleGosup.controller;


import muscleGosup.exception.ElementNotFoundException;
import muscleGosup.model.User;
import muscleGosup.repository.UserRepository;

public class UserController {
    private final UserRepository userRepository;


    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // #TODO: move it to service
    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new ElementNotFoundException("User with ID : " + userId + "was not found."));
    }
}
