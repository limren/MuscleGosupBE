package muscleGosup.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import muscleGosup.controller.UserController;
import muscleGosup.exception.ElementNotFoundException;
import muscleGosup.model.Exercise;
import muscleGosup.model.User;
import muscleGosup.model.WorkoutSession;
import muscleGosup.repository.WorkoutSessionRepository;

@Service
public class WorkoutSessionService {
    private final WorkoutSessionRepository workoutSessionRepository;
    private final UserController userController;
    private final ExerciseService exerciseService;
    public WorkoutSessionService(WorkoutSessionRepository workoutSessionRepository, UserController userController, ExerciseService exerciseService){
        this.workoutSessionRepository = workoutSessionRepository;
        this.userController = userController;
        this.exerciseService = exerciseService;
    }


    public WorkoutSession createWorkoutSession(String title, Long userId, Date duration, Date date){
        User user = userController.getUserById(userId);        
        WorkoutSession workoutSession = new WorkoutSession();
        workoutSession.setTitle(title);
        workoutSession.setUser(user);
        workoutSession.setDate(date);
        workoutSessionRepository.save(workoutSession);
        return workoutSession;
    }

    public WorkoutSession addExerciseWorkoutSession(Long workoutSessionId, Long userId, String name){
        WorkoutSession workoutSession = this.getWorkoutSessionById(workoutSessionId);
        Exercise exercise = exerciseService.createExercise(workoutSessionId, name);
        workoutSession.getExercises().add(exercise);
        workoutSessionRepository.save(workoutSession);
        return workoutSession;

    }

    public WorkoutSession addExerciseWorkoutSession(Long workoutSessionId, Long userId, String name, List<Integer> sets){
        WorkoutSession workoutSession = this.getWorkoutSessionById(workoutSessionId);
        Exercise exercise = exerciseService.createExercise(workoutSessionId, name, sets);
        workoutSession.getExercises().add(exercise);
        workoutSessionRepository.save(workoutSession);
        return workoutSession;

    }
    // #TODO : check behavior with controller 
    public boolean deleteWorkoutSessionById(Long workoutSessionId){
        workoutSessionRepository.deleteById(workoutSessionId);
        return true;
    }

    public WorkoutSession getWorkoutSessionById(Long workoutSessionId){
        return workoutSessionRepository.findById(workoutSessionId).orElseThrow(() -> new ElementNotFoundException("Workout session with ID : " + workoutSessionId + "was not found."));
    }
}
