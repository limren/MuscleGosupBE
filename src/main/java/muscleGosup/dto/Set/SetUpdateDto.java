package muscleGosup.dto.Set;

public class SetUpdateDto {
    private Long setId;
    private Integer reps;
    private Long weight;


    public Long getSetId(){
        return this.setId;
    }
    public void setSetId(Long setId){
        this.setId = setId;
    }

    public Integer getReps(){
        return this.reps;
    }

    public void setReps(Integer reps){
        this.reps = reps;
    }

    public Long getWeight(){
        return this.weight;
    }

    public void setWeight(Long weight){
        this.weight = weight;
    }
}

