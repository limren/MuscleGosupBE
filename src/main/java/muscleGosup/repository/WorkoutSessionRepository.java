package muscleGosup.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import muscleGosup.model.User;
import muscleGosup.model.WorkoutSession;

public interface WorkoutSessionRepository extends CrudRepository<WorkoutSession, Long> {
    List<WorkoutSession> findByUserId(Long userId);
    List<WorkoutSession> findByUserAndDateBetween(User user, LocalDateTime startDate, LocalDateTime endDate);
    @Query("SELECT count(id) FROM WorkoutSession ws " + 
           "WHERE ws.user = :user")
    Integer countWorkoutSessionsUser(
        @Param("user") User user
    );
    @Query("SELECT count(id) FROM WorkoutSession ws " + 
           "WHERE ws.user = :user " +
           "AND ws.date BETWEEN :startDate AND :endDate")
    Integer countWorkoutSessionsUserBetweenDate(
        @Param("user") User user,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
}
