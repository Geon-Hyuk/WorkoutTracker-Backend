package com.workout.WorkoutTrackerBackend.service;

import com.workout.WorkoutTrackerBackend.domain.workouts.Workouts;
import com.workout.WorkoutTrackerBackend.domain.workouts.WorkoutsRepository;
import com.workout.WorkoutTrackerBackend.web.dto.WorkoutsResponseDto;
import com.workout.WorkoutTrackerBackend.web.dto.WorkoutsSaveRequestDto;
import com.workout.WorkoutTrackerBackend.web.dto.WorkoutsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class WorkoutsService {
    private final WorkoutsRepository workoutsRepository;

    @Transactional
    public Long save(WorkoutsSaveRequestDto requestDto) {
        return workoutsRepository.save(requestDto.toEntity()).
                getId();
    }

    @Transactional
    public Long update(Long id, WorkoutsUpdateRequestDto requestDto) {
        Workouts workouts = workoutsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("Found no such workouts. id=" + id));
        workouts.update(requestDto.getName(), requestDto.getWeights(), requestDto.getReps(), requestDto.getSets());
        return id;
    }

    public WorkoutsResponseDto findById (Long id) {
        Workouts entity = workoutsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("Found no such workouts. id=" + id));
        return new WorkoutsResponseDto(entity);
    }
}
