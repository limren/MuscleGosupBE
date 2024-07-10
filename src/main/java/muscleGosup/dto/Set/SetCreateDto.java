package muscleGosup.dto.Set;

public class SetCreateDto {
    private Long exerciseId;
    private Integer reps;
    private Long weight;

    public Long getExerciseId(){
        return this.exerciseId;
    }
    public Integer getReps(){
        return this.reps;
    }
    public Long getWeight(){
        return this.weight;
    }

    public void setExerciseId(Long exerciseId){
        this.exerciseId = exerciseId;
    }
    public void setReps(Integer reps){
        this.reps = reps;
    }
    public void setWeight(Long weight){
        this.weight = weight;
    }
}
