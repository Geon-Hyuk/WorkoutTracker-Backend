package com.workout.WorkoutTrackerBackend.domain.workouts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WorkoutsRepositoryTest {
    @Autowired
    WorkoutsRepository workoutsRepository;

    @AfterEach //
    public void cleanup() {
        workoutsRepository.deleteAll();
    }

    @Test
    public void workoutSave_load() {
        // given
        String name = "Test Workout Name";
        int weights = 100;
        int reps = 10;
        int sets = 1;

        workoutsRepository.save(Workouts.builder()
                .name(name)
                .weights(weights)
                .reps(reps)
                .sets(sets)
                .build());

        //when
        List<Workouts> workoutsList = workoutsRepository.findAll(); //

        //then
        Workouts workouts = workoutsList.get(0);
        assertThat(workouts.getName()).isEqualTo(name);
        assertThat(workouts.getWeights()).isEqualTo(weights);
        assertThat(workouts.getReps()).isEqualTo(reps);
        assertThat(workouts.getSets()).isEqualTo(sets);
    }

    @Test
    public void BaseTimeEntity_post() {

        //given
        LocalDateTime now = LocalDateTime.of(2022,4,28,0,0,0);
        workoutsRepository.save(Workouts.builder()
                .name("Bench Press")
                .weights(200)
                .reps(10)
                .sets(2)
                .build());

        //when
        List<Workouts> workoutsList = workoutsRepository.findAll();

        //then
        Workouts workouts = workoutsList.get(0);

        System.out.println(">>>>>> createdDate="+workouts.getCreatedDate()+", modifiedDate="+workouts.getModifiedDate());

        assertThat(workouts.getCreatedDate()).isAfter(now);
        assertThat(workouts.getModifiedDate()).isAfter(now);
    }

}
