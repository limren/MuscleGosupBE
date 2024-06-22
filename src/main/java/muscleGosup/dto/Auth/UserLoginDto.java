package muscleGosup.dto.Auth;

public class UserLoginDto {
    private String username;
    private String password;

    // Getters and setters
    // When a request will be sent through an endpoint, the body from the HTTP request will be processed and use setters to create an UserLoginDto

    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
