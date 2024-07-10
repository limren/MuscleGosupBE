package muscleGosup.service;

import org.springframework.stereotype.Service;

import muscleGosup.dto.Set.SetCreateDto;
import muscleGosup.dto.Set.SetDeleteDto;
import muscleGosup.dto.Set.SetUpdateDto;
import muscleGosup.mapper.SetMapper;
import muscleGosup.model.Exercise;
import muscleGosup.model.Set;
import muscleGosup.repository.SetRepository;

@Service
public class SetService {
    private final SetRepository setRepository;
    private final CommonService commonService;
    private final SetMapper setMapper;
    public SetService(SetRepository setRepository, CommonService commonService, SetMapper setMapper){
        this.setRepository = setRepository;
        this.commonService = commonService;
        this.setMapper = setMapper;
    }

    public Set create(SetCreateDto setCreateDto){
        Exercise exercise = commonService.getExerciseById(setCreateDto.getExerciseId());
        Set newSet = new Set();
        newSet.setReps(setCreateDto.getReps());
        newSet.setExercise(exercise);
        newSet.setWeight(setCreateDto.getWeight());
        setRepository.save(newSet);
        return newSet;
    }

    public Set updateSet(SetUpdateDto setUpdateDto){
        Set set = commonService.getSetById(setUpdateDto.getSetId());
        setMapper.updateSetFromDto(setUpdateDto, set);
        setRepository.save(set);
        return set;
    }

    public boolean deleteById(SetDeleteDto setDeleteDto){
        setRepository.deleteById(setDeleteDto.getSetId());
        return true;
    }
}
