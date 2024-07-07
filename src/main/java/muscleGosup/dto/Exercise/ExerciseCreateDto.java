package muscleGosup.dto.Exercise;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExerciseCreateDto {
    private String name;
    private Long workoutSessionId;
    @JsonIgnore
    private List<Integer> sets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getSets() {
        return sets;
    }

    public void setSets(List<Integer> sets) {
        this.sets = sets;
    }

    public Long getWorkoutSessionId(){
        return workoutSessionId;
    }

    public void setWorkoutSessionId(Long workoutSessionId){
        this.workoutSessionId = workoutSessionId;
    }  
}
