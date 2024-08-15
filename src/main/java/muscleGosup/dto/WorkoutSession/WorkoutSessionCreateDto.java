package muscleGosup.dto.WorkoutSession;

import java.time.LocalDateTime;

public class WorkoutSessionCreateDto {
    private String title;
    private Long duration;
    private LocalDateTime date;
    
    
    public String getTitle(){
        return title;
    }
    public Long getDuration(){
        return duration;
    }
    public LocalDateTime getDate(){
        return date;
    }

    public void setTitle(String title){
        this.title = title;  
    }
    public void setDuration(Long duration){
        this.duration = duration;
    }
    public void setDate(LocalDateTime date){
        this.date = date;
    }
}

