package muscleGosup.repository;

import org.springframework.data.repository.CrudRepository;

import muscleGosup.model.WorkoutSession;

public interface WorkoutSessionRepository extends CrudRepository<WorkoutSession, Long> {
    
}
