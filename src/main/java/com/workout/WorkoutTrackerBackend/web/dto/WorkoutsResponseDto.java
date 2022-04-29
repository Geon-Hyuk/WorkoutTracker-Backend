package com.workout.WorkoutTrackerBackend.web.dto;

import com.workout.WorkoutTrackerBackend.domain.workouts.Workouts;
import lombok.Getter;


@Getter
public class WorkoutsResponseDto {

    private Long id;
    private String name;
    private int weights;
    private int reps;
    private int sets;

    public WorkoutsResponseDto(Workouts entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.weights = entity.getWeights();
        this.reps = entity.getReps();
        this.sets = entity.getSets();
    }
}
