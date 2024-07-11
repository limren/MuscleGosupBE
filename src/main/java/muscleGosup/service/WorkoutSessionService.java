package muscleGosup.service;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import muscleGosup.exception.ElementNotFoundException;
import muscleGosup.model.CustomUserDetails;
import muscleGosup.model.Exercise;
import muscleGosup.model.Set;
import muscleGosup.model.User;
import muscleGosup.model.WorkoutSession;
import muscleGosup.repository.WorkoutSessionRepository;

@Service
public class WorkoutSessionService {
    private final WorkoutSessionRepository workoutSessionRepository;
    private final CommonService commonService;
    private final ExerciseService exerciseService;
    private final UserService userService;
    public WorkoutSessionService(WorkoutSessionRepository workoutSessionRepository, CommonService commonService, ExerciseService exerciseService, UserService userService){
        this.workoutSessionRepository = workoutSessionRepository;
        this.commonService = commonService;
        this.exerciseService = exerciseService;
        this.userService = userService;
    }


    public WorkoutSession createWorkoutSession(String title, Long duration, Date date){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        System.out.println(principal.getClass());
        if(principal instanceof CustomUserDetails){
            Long userId = ((CustomUserDetails)principal).getId();
            User user = commonService.getUserById(userId);        
            WorkoutSession workoutSession = new WorkoutSession();
            workoutSession.setTitle(title);
            workoutSession.setUser(user);
            workoutSession.setDate(date);
            workoutSessionRepository.save(workoutSession);
            return workoutSession;
        } else {
            throw new IllegalStateException("Principal is not an instance of CustomUserDetails");
        }
    }

    public WorkoutSession addExerciseWorkoutSession(Long workoutSessionId, Long userId, String name){
        WorkoutSession workoutSession = this.getWorkoutSessionById(workoutSessionId);
        Exercise exercise = exerciseService.createExercise(workoutSessionId, name);
        workoutSession.getExercises().add(exercise);
        workoutSessionRepository.save(workoutSession);
        return workoutSession;

    }

    public WorkoutSession addExerciseWorkoutSession(Long workoutSessionId, Long userId, String name, List<Set> sets){
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

    public List<WorkoutSession> getWorkoutSessionsByUserId() throws IllegalAccessException {
        User user = userService.getAuthenticatedUser();
        return workoutSessionRepository.findByUserId(user.getId());
    }
}
