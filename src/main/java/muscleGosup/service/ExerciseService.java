package muscleGosup.service;

import java.util.List;

import org.springframework.stereotype.Service;

import muscleGosup.exception.ElementNotFoundException;
import muscleGosup.model.Exercise;
import muscleGosup.model.WorkoutSession;
import muscleGosup.repository.ExerciseRepository;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final CommonService commonService;
    public ExerciseService(ExerciseRepository exerciseRepository, CommonService commonService){
        this.exerciseRepository = exerciseRepository;
        this.commonService = commonService;
    }

    public Exercise createExercise(Long workoutSessionId, String name){
        WorkoutSession workoutSession = commonService.getWorkoutSessionById(workoutSessionId);
        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setWorkoutSession(workoutSession);
        exerciseRepository.save(exercise);
        return exercise;
    }

    public Exercise createExercise(Long workoutSessionId, String name,  List<Integer> sets){
        WorkoutSession workoutSession = commonService.getWorkoutSessionById(workoutSessionId);
        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setSets(sets);
        exercise.setWorkoutSession(workoutSession);
        exerciseRepository.save(exercise);
        return exercise;
    }


    public Exercise addSetExercise(Long exerciseId, Integer set){
        Exercise exercise = this.getExerciseById(exerciseId);
        List<Integer> sets = exercise.getSets();
        sets.add(set);
        exercise.setSets(sets);
        exerciseRepository.save(exercise);
        return exercise;
    }

    public Exercise updateSetsExercise(Long exerciseId, List<Integer> sets){
        Exercise exercise = this.getExerciseById(exerciseId);
        exercise.setSets(sets);
        exerciseRepository.save(exercise);
        return exercise;
    }


    public boolean deleteExerciseById(Long exerciseId){
        exerciseRepository.deleteById(exerciseId);
        return true;

    }

    public Exercise updateNameExercise(Long exerciseId, String name){
        Exercise exercise = this.getExerciseById(exerciseId);
        exercise.setName(name);
        exerciseRepository.save(exercise);
        return exercise;
    }

    public Exercise getExerciseById(Long exerciseId){
        return exerciseRepository.findById(exerciseId).orElseThrow(() -> new ElementNotFoundException("Exercise with ID : " + exerciseId + "was not found."));
    }
}

