package com.workout.WorkoutTrackerBackend.domain.workouts;

import com.workout.WorkoutTrackerBackend.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Workouts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false) //
    private String name;

    @Column
    private int weights;

    @Column
    private int reps;

    @Column
    private int sets;

    @Builder
    public Workouts(String name, int weights, int reps, int sets) {
        this.name = name;
        this.weights = weights;
        this.reps = reps;
        this.sets = sets;
    }

    public void update(String name, int weights, int reps, int sets) {
        this.name = name;
        this.weights = weights;
        this.reps = reps;
        this.sets = sets;
    }
}
