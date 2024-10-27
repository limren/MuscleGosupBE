package muscleGosup.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/get/auth")
    public ResponseEntity<Object> getUser(){
        return ResponseEntity.ok().body(userService.getAuthenticatedUserRestricted());
    }
}
