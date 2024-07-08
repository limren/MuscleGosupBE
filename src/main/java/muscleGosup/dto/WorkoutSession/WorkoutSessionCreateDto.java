package muscleGosup.dto.WorkoutSession;

import java.util.Date;

public class WorkoutSessionCreateDto {
    private String title;
    private Long duration;
    private Date date;
    
    
    public String getTitle(){
        return title;
    }
    public Long getDuration(){
        return duration;
    }
    public Date getDate(){
        return date;
    }

    public void setTitle(String title){
        this.title = title;  
    }
    public void setDuration(Long duration){
        this.duration = duration;
    }
    public void setDate(Date date){
        this.date = date;
    }
}

