package muscleGosup.controller;

import java.util.Collections;
import java.util.HashMap;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import muscleGosup.dto.WorkoutSession.WorkoutSessionCreateDto;
import muscleGosup.model.WorkoutSession;
import muscleGosup.service.WorkoutSessionService;

@RequestMapping("/api/workoutSession")
@RestController
public class WorkoutSessionController {
    
    @Autowired
    private WorkoutSessionService workoutSessionService;

    @PostMapping("/create")
    public ResponseEntity<Object> createWorkoutSession(@RequestBody WorkoutSessionCreateDto workoutSessionCreateDto){
        HashMap<String, Object> responseContent = new HashMap<>();
        try{
            WorkoutSession workoutSession = workoutSessionService.createWorkoutSession(workoutSessionCreateDto.getTitle(), workoutSessionCreateDto.getDuration(), workoutSessionCreateDto.getDate());
            responseContent.put("workoutSession", workoutSession);
            responseContent.put("message", "Workout session has been successfully created");
            return new ResponseEntity<>(responseContent, HttpStatus.OK);
        } catch(RuntimeException runtimeException){
            return new ResponseEntity<>(runtimeException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Since workout sessions are related to one User, the convention makes that it will be retrieve by the authenticated user and so
    // related to the userId
    @GetMapping("/get/all")
    public ResponseEntity<Object> getWorkoutSessionsByUserId(){
        try{
            return ResponseEntity.ok(Collections.singletonMap("workoutSessions", workoutSessionService.getWorkoutSessionsByUserId()));
        } catch(IllegalAccessException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } 
        catch(RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } 
    }

}
