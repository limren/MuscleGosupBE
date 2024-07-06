package muscleGosup.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
    @Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"}),
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
    })
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;
    public String username;
    public String email;
    public String password;

    @OneToMany
    private List<WorkoutSession> workoutSessions;

    public User(){};

    public Long getId(){
        return this.id;
    }
    public String getUsername(){
        return this.username;
    }
    public String getEmail(){
        return this.email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<WorkoutSession> getWorkoutSessions(){
        return this.workoutSessions;
    }
    public void setWorkoutSessions(List<WorkoutSession> workoutSessions) {
        this.workoutSessions = workoutSessions;
    }
}
