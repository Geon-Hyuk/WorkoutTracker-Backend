package com.workout.WorkoutTrackerBackend.web;

import com.workout.WorkoutTrackerBackend.service.WorkoutsService;
import com.workout.WorkoutTrackerBackend.web.dto.WorkoutsResponseDto;
import com.workout.WorkoutTrackerBackend.web.dto.WorkoutsSaveRequestDto;
import com.workout.WorkoutTrackerBackend.web.dto.WorkoutsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class WorkoutsApiController {

    private final WorkoutsService workoutsService;

    @PostMapping("/api/v1/workouts")
    public Long save(@RequestBody WorkoutsSaveRequestDto
                             requestDto) {
        return workoutsService.save(requestDto);
    }

    @PutMapping("/api/v1/workouts/{id}")
    public Long update(@PathVariable Long id, @RequestBody WorkoutsUpdateRequestDto requestDto) {
        return workoutsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/workouts/{id}")
    public WorkoutsResponseDto findById (@PathVariable Long id) {
        return workoutsService.findById(id);
    }
}
