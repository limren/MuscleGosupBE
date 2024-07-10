package muscleGosup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "sets", uniqueConstraints = {
    @UniqueConstraint(columnNames = "id")
})
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Integer reps;
    
    private Long weight;

    @ManyToOne
    private Exercise exercise;

    public Set(){}

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
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

    public Exercise getExercise(){
        return this.exercise;
    }

    public void setExercise(Exercise exercise){
        this.exercise = exercise;
    }
}

