package muscleGosup.repository;

import org.springframework.data.repository.CrudRepository;

import muscleGosup.model.Exercise;

public interface ExerciseRepository extends CrudRepository<Exercise, Long>  {
    
}
