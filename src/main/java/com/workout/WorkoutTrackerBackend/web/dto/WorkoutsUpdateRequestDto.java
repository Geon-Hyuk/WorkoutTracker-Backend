package com.workout.WorkoutTrackerBackend.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class WorkoutsUpdateRequestDto {

    private String name;
    private int weights;
    private int reps;
    private int sets;

    @Builder
    public WorkoutsUpdateRequestDto(String name, int weights, int reps, int sets) {
        this.name = name;
        this.weights = weights;
        this.reps = reps;
        this.sets = sets;
    }
}
