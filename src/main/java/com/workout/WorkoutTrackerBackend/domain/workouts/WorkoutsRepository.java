package com.workout.WorkoutTrackerBackend.domain.workouts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutsRepository extends JpaRepository<Workouts, Long> {
}
