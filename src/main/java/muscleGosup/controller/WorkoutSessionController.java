package muscleGosup.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
