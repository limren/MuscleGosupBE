package muscleGosup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import muscleGosup.service.UserService;


@RequestMapping("/api/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/getWorkoutSessions")
    public ResponseEntity<Object> getWorkoutSessions(){
        try {
            return new ResponseEntity<>(userService.getWorkoutSessions(), HttpStatus.OK);
        } catch(Exception ex){
            // #TODO: change request status
            return new ResponseEntity<>("Something went wrong while trying to get workout sessions.", HttpStatus.BAD_REQUEST);
        }
        
        
    }
}
