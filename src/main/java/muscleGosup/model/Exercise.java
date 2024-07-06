package muscleGosup.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private List<Integer> sets;

    @ManyToOne
    private WorkoutSession workoutSession;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public List<Integer> getSets(){
        return this.sets;
    }

    public void setSets(List<Integer> sets){
        this.sets = sets;
    }
}
