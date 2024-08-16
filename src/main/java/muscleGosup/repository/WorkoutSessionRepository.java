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
    
    // @Query("SELECT ws FROM WorkoutSession ws " +
    //     "WHERE ws.user = :user " + 
    //     "GROUP BY ws.user, FUNCTION('TO_CHAR', ws.date, 'YYYY-MM-DD'), ws.id " +
    //     "ORDER BY ws.date ASC"
    // )
    // List<WorkoutSession> findByUserGroupByDate(
    //     @Param("user") User user
    // );
    // @Query("SELECT ws FROM WorkoutSession ws " +
    //         "WHERE ws.user = :user " + 
    //         "AND ws.date BETWEEN :startDate AND :endDate " +
    //         "GROUP BY ws.user, FUNCTION('TO_CHAR', ws.date, 'YYYY-MM-DD'), ws.id " +
    //         "ORDER BY ws.date ASC"
    // )
    // List<WorkoutSession> findByUserAndDateBetweenGroupByDate(
    //     @Param("user")User user,
    //     @Param("startDate") LocalDateTime startDate,
    //     @Param("endDate") LocalDateTime endDate
    // );
    
}
