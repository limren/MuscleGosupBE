package muscleGosup.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.GenerationType;

@Entity
@Table(name= "workoutSessions")
public class WorkoutSession {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String title;
    @ManyToOne
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    private Date duration;

    @Temporal(TemporalType.DATE)
    private Date date;

    // One workout session have many exercises
    @OneToMany
    private List<Exercise> exercises = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser()
    {
        return this.user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public List<Exercise> getExercises(){
        return this.exercises;
    }
    
    public void setExercises(List<Exercise> exercises){
        this.exercises = exercises;
    }
}
