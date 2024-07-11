package muscleGosup.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import muscleGosup.dto.Set.SetCreateDto;
import muscleGosup.dto.Set.SetDeleteDto;
import muscleGosup.dto.Set.SetUpdateDto;
import muscleGosup.model.Set;
import muscleGosup.service.SetService;

@RequestMapping("/api/set")
@RestController
public class SetController {
    @Autowired
    public SetService setService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody SetCreateDto setCreateDto){
        try{
            HashMap<String, Object> responseContent = new HashMap<>();
            Set set = setService.create(setCreateDto);
            responseContent.put("set", set);
            responseContent.put("message", "Set has been created successfully.");
            return new ResponseEntity<>(responseContent, HttpStatus.CREATED);
        } catch(Exception ex){
            return new ResponseEntity<>("Something went wrong while creating Set.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody SetUpdateDto setUpdateDto){
        try 
        {
            HashMap<String, Object> responseContent = new HashMap<>();
            Set set = setService.updateSet(setUpdateDto);
            responseContent.put("set", set);
            responseContent.put("message", "Set has been created successfully.");
            return new ResponseEntity<>(responseContent, HttpStatus.OK);
        } catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestBody SetDeleteDto setDeleteDto){
        try {
            setService.deleteById(setDeleteDto);
            return new ResponseEntity<>("Set " + setDeleteDto.getSetId() + "has been successfully deleted.", HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
