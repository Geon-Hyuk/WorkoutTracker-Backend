package com.workout.WorkoutTrackerBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //
@SpringBootApplication
public class WorkoutTrackerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkoutTrackerBackendApplication.class, args);
	}

}
