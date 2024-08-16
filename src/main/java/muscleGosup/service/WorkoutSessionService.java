package muscleGosup.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    public WorkoutSession createWorkoutSession(String title, Long duration, LocalDateTime date){
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

    public List<WorkoutSession> getThisWeekWorkoutSessions() throws IllegalAccessException {
        User user = userService.getAuthenticatedUser();
        LocalDateTime currentDate = LocalDateTime.now();
        int dayOfWeek = currentDate.getDayOfWeek().getValue();
        LocalDateTime startOfWeek = currentDate.minusDays(dayOfWeek - 1).with(LocalTime.MIN);
        LocalDateTime endOfWeek = currentDate.plusDays(6).with(LocalTime.of(23, 59, 59));

        return workoutSessionRepository.findByUserAndDateBetween(user, startOfWeek, endOfWeek);
    }

    // Learned about Stream's API, gave me really useful knowledge to play with data in Collections 
    public Map<Object, List<WorkoutSession>> getWorkoutSessionsGroupedByDate() throws IllegalAccessException {
        User user = userService.getAuthenticatedUser();
        List<WorkoutSession> sessions = workoutSessionRepository.findByUserId(user.getId());
        Map<Object, List<WorkoutSession>> sessionsGroupedByDate = sessions.parallelStream().collect(Collectors.groupingBy(
            session -> session.getDate().toLocalDate().toString()
        ));

        return sessionsGroupedByDate;
    }
}
