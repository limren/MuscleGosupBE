package muscleGosup.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import muscleGosup.model.WorkoutSession;

public interface WorkoutSessionRepository extends CrudRepository<WorkoutSession, Long> {
    List<WorkoutSession> findByUserId(Long userId);
}
