package muscleGosup.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import muscleGosup.model.Exercise;
import muscleGosup.model.User;
import muscleGosup.model.WorkoutSession;
import muscleGosup.repository.UserRepository;
import muscleGosup.repository.WorkoutSessionRepository;

@Service
public class WorkoutSessionService {
    private final WorkoutSessionRepository workoutSessionRepository;
    private final UserRepository userRepository;
    public WorkoutSessionService(WorkoutSessionRepository workoutSessionRepository, UserRepository userRepository){
        this.workoutSessionRepository = workoutSessionRepository;
        this.userRepository = userRepository;
    }


    public WorkoutSession createWorkoutSession(String title, Long userId, Date duration, Date date){
        Optional<User> optUser = userRepository.findById(userId);
        WorkoutSession workoutSession = new WorkoutSession();
        workoutSession.setTitle(title);
        optUser.ifPresentOrElse((user -> workoutSession.setUser(user)), () ->{
            // #TODO : Check behavior when in controller for this
            throw new Error("An error occured while trying to find User by ID.");
        } );
        workoutSession.setDate(date);
        workoutSessionRepository.save(workoutSession);
        return workoutSession;
    }

    public WorkoutSession addExerciceWorkoutSession(Long workoutSessionId, Long userId, List<Exercise> exercises){

    }

}
