package muscleGosup.controller.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import muscleGosup.dto.Auth.UserLoginDto;
import muscleGosup.dto.Auth.UserRegisterDto;
import muscleGosup.exception.auth.EmailAlreadyTakenException;
import muscleGosup.exception.auth.UsernameAlreadyTakenException;
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
    public ResponseEntity<Object> login(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request,
                                        HttpServletResponse response) {
        User newUser = userRepository.findByUsername(userLoginDto.getUsername());
        if (newUser == null || !passwordEncoder.matches(userLoginDto.getPassword(), newUser.getPassword())) {
            return ResponseEntity.badRequest().body("An error occurred while trying to log in : username or password is wrong");
        }

        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));
        context.setAuthentication(authentication);

        SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
        securityContextRepository.saveContext(context, request, response);

        return ResponseEntity.ok().body(true);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRegisterDto userRegisterDto) {
        if (userRepository.existsByEmail(userRegisterDto.getEmail())) {
            throw new EmailAlreadyTakenException();
        }
        if (userRepository.existsByUsername(userRegisterDto.getUsername())) {
            throw new UsernameAlreadyTakenException();
        }

        return ResponseEntity.ok(userService.createUser(userRegisterDto.getUsername(), userRegisterDto.getEmail(), userRegisterDto.getPassword()));
    }

   @GetMapping("/isLoggedIn")
   public ResponseEntity<Object> isLoggedIn() {
    userService.getAuthenticatedUser();
    return ResponseEntity.ok().body(true);
   }
}
