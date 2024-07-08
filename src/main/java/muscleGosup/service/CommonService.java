package muscleGosup.service;

import org.springframework.stereotype.Service;

import muscleGosup.exception.ElementNotFoundException;
import muscleGosup.model.Exercise;
import muscleGosup.model.User;
import muscleGosup.model.WorkoutSession;
import muscleGosup.repository.ExerciseRepository;
import muscleGosup.repository.UserRepository;
import muscleGosup.repository.WorkoutSessionRepository;


// This class defines services that have to be shared between services
// it prevents injections circles between beans
@Service
public class CommonService {
    
    private final UserRepository userRepository;
    private final WorkoutSessionRepository workoutSessionRepository;
    private final ExerciseRepository exerciseRepository;

    public CommonService(UserRepository userRepository, WorkoutSessionRepository workoutSessionRepository, ExerciseRepository exerciseRepository){
        this.userRepository = userRepository;
        this.workoutSessionRepository = workoutSessionRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new ElementNotFoundException("User with ID : " + userId + "was not found."));
    }
    public WorkoutSession getWorkoutSessionById(Long workoutSessionId) {
        return workoutSessionRepository.findById(workoutSessionId)
                .orElseThrow(() -> new ElementNotFoundException("WorkoutSession with ID : " + workoutSessionId + " was not found."));
    }

    public Exercise getExerciseById(Long exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ElementNotFoundException("Exercise with ID : " + exerciseId + " was not found."));
    }
}
