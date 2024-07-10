package muscleGosup.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "exercises", uniqueConstraints = {
    @UniqueConstraint(columnNames = "id")
})
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Set> sets = new ArrayList<>();

    @ManyToOne
    private WorkoutSession workoutSession;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public List<Set> getSets(){
        return this.sets;
    }

    public void setSets(List<Set> sets){
        this.sets = sets;
    }

    public WorkoutSession getWorkoutSession(){
        return this.workoutSession;
    }
    
    public void setWorkoutSession(WorkoutSession workoutSession){
        this.workoutSession = workoutSession;
    }
}
