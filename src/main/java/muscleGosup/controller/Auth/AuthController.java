package muscleGosup.controller.Auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import muscleGosup.dto.Auth.UserLoginDto;
import muscleGosup.dto.Auth.UserRegisterDto;
import muscleGosup.model.User;
import muscleGosup.repository.UserRepository;
import muscleGosup.service.UserService;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
    

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginDto userLoginDto){

        User newUser = userRepository.findByUsername(userLoginDto.getUsername());
        if(newUser == null || !passwordEncoder.matches(userLoginDto.getPassword(), newUser.getPassword())){
            return new ResponseEntity<>("An error occurred while trying to log in : username or password is wrong.", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("User is now authenticated " + SecurityContextHolder.getContext().getAuthentication(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRegisterDto userRegisterDto)
    {
        if(userRepository.existsByEmail(userRegisterDto.getEmail())){
            return new ResponseEntity<>("The email is already taken !", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByUsername(userRegisterDto.getUsername()))
        {
            return new ResponseEntity<>("The username is already used by another user !", HttpStatus.BAD_REQUEST);
        }

        User newUser = userService.createUser(userRegisterDto.getUsername(), userRegisterDto.getEmail(), userRegisterDto.getPassword());

        return new ResponseEntity<>("User "+ newUser.username + " successfully created - ", HttpStatus.OK);
    }

    @PostMapping("/test")
    public List<User> test(){
        return userRepository.findAll();
    }
}
