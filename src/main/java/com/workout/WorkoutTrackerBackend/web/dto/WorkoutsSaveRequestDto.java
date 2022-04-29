package com.workout.WorkoutTrackerBackend.web.dto;

import com.workout.WorkoutTrackerBackend.domain.workouts.Workouts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WorkoutsSaveRequestDto {
    private String name;
    private int weights;
    private int reps;
    private int sets;
    @Builder
    public WorkoutsSaveRequestDto(String name, int weights, int reps, int sets) {
        this.name = name;
        this.weights = weights;
        this.reps = reps;
        this.sets = sets;
    }

    public Workouts toEntity() {
        return Workouts.builder()
                .name(name)
                .weights(weights)
                .reps(reps)
                .sets(sets)
                .build();
    }
}
