package com.workout.WorkoutTrackerBackend.web;

import com.workout.WorkoutTrackerBackend.domain.workouts.Workouts;
import com.workout.WorkoutTrackerBackend.domain.workouts.WorkoutsRepository;
import com.workout.WorkoutTrackerBackend.web.dto.WorkoutsSaveRequestDto;
import com.workout.WorkoutTrackerBackend.web.dto.WorkoutsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest
        .WebEnvironment.RANDOM_PORT)
public class WorkoutsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WorkoutsRepository workoutsRepository;

    @AfterEach
    public void tearDown() throws Exception {
        workoutsRepository.deleteAll();
    }

    @Test
    public void Workouts_post() throws Exception {
        // given
        String name = "name";
        int weights = 100;
        int reps = 10;
        int sets = 1;
        WorkoutsSaveRequestDto requestDto = WorkoutsSaveRequestDto.
                builder()
                .name(name)
                .weights(weights)
                .reps(reps)
                .sets(sets)
                .build();

        String url = "http://localhost:" + port + "/api/v1/" +
                "workouts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.
                postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).
                isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).
                isGreaterThan(0L);

        List<Workouts> all = workoutsRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getWeights()).isEqualTo(weights);
        assertThat(all.get(0).getReps()).isEqualTo(reps);
        assertThat(all.get(0).getSets()).isEqualTo(sets);
    }

    @Test
    public void Workouts_update() throws Exception {
        //given
        Workouts savedWorkouts = workoutsRepository.save(Workouts.builder()
                .name("name")
                .weights(100)
                .reps(10)
                .sets(1)
                .build());

        Long updateId = savedWorkouts.getId();
        String expectedName = "name2";
        int expectedWeights = 200;
        int expectedReps = 8;
        int expectedSets = 2;

        WorkoutsUpdateRequestDto requestDto =
                WorkoutsUpdateRequestDto.builder()
                        .name(expectedName)
                        .weights(expectedWeights)
                        .reps(expectedReps)
                        .sets(expectedSets)
                        .build();

        String url = "http://localhost:" + port + "/api/v1/" +
                "workouts/" + updateId;

        HttpEntity<WorkoutsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.
                exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).
                isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).
                isGreaterThan(0L);

        List<Workouts> all = workoutsRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(expectedName);
        assertThat(all.get(0).getWeights()).isEqualTo(expectedWeights);
        assertThat(all.get(0).getReps()).isEqualTo(expectedReps);
        assertThat(all.get(0).getSets()).isEqualTo(expectedSets);

    }
}
