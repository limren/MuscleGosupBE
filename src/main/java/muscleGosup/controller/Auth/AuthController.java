package muscleGosup.controller.Auth;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<Object> login(@RequestBody UserLoginDto userLoginDto,
    HttpServletRequest request,
    HttpServletResponse response){
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        User newUser = userRepository.findByUsername(userLoginDto.getUsername());
        if(newUser == null || !passwordEncoder.matches(userLoginDto.getPassword(), newUser.getPassword())){
            return new ResponseEntity<>("An error occurred while trying to log in : username or password is wrong.", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));

        context.setAuthentication(authentication);
        SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
        securityContextRepository.saveContext(context, request, response);
        return ResponseEntity.ok(Collections.singletonMap("message", "User is now authenticated."));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRegisterDto userRegisterDto)
    {
        HashMap<String, Object> responseContent = new HashMap<>();
        if(userRepository.existsByEmail(userRegisterDto.getEmail())){
            responseContent.put("error", "The email is already taken!");
            return ResponseEntity.badRequest().body(responseContent);
        }
        if(userRepository.existsByUsername(userRegisterDto.getUsername()))
        {
            responseContent.put("error", "The username is already used by another user!");
            return ResponseEntity.badRequest().body(responseContent);
        }

        User newUser = userService.createUser(userRegisterDto.getUsername(), userRegisterDto.getEmail(), userRegisterDto.getPassword());
        responseContent.put("user", newUser);
        responseContent.put("message", "User successfully created.");
        return new ResponseEntity<>(responseContent, HttpStatus.OK);
    }

    @PostMapping("/test")
    public List<User> test(){
        return userRepository.findAll();
    }
}
