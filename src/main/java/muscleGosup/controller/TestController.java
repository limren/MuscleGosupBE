package muscleGosup.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class TestController {
    
    @PostMapping("/test")
    public String test()
    {
        return "hello world !";
    }
}
