package muscleGosup.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import muscleGosup.dto.Exercise.ExerciseCreateDto;
import muscleGosup.dto.Exercise.ExerciseDeleteDto;
import muscleGosup.exception.ElementNotFoundException;
import muscleGosup.model.Exercise;
import muscleGosup.service.ExerciseService;

@RequestMapping("/api/exercise")
@RestController
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;


    @PostMapping("/create")
    public ResponseEntity<Object> createExercise(@RequestBody ExerciseCreateDto exerciseCreateDto){
        try {
            HashMap<String, Object> responseContent = new HashMap<>();
            if(exerciseCreateDto.getSets() == null){
                Exercise exercise = exerciseService.createExercise(exerciseCreateDto.getWorkoutSessionId(), exerciseCreateDto.getName());
                responseContent.put("exercise", exercise);
                responseContent.put("message", "Exercise has been created successfully.");
                return new ResponseEntity<>(responseContent, HttpStatus.OK);
            } else {
                Exercise exercise = exerciseService.createExercise(exerciseCreateDto.getWorkoutSessionId(), exerciseCreateDto.getName());
                responseContent.put("exercise", exercise);
                responseContent.put("message", "Exercise has been created successfully.");
                return new ResponseEntity<>(responseContent, HttpStatus.OK);
            }
        } catch(ElementNotFoundException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException exception) {
            return new ResponseEntity<>("Something went wrong while creating Exercise.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> deleteExercise(@RequestBody ExerciseDeleteDto exerciseDeleteDto){
        try {
            exerciseService.deleteExerciseById(exerciseDeleteDto.getExerciseId());
            return new ResponseEntity<>("Exercise has been deleted successfully.", HttpStatus.OK);
        } catch(RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        } 
}

    // #TODO : for add sets/set check if it's possible only to check the Object that contains sets/set and do verification on it

}
