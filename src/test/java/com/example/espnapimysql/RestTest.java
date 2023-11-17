package com.example.espnapimysql;

import com.example.espnapimysql.database.TeamEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldAddTeamEntityToDatabase() {
        TeamEntity newTeam = new TeamEntity();
        newTeam.setId(13);
        newTeam.setName("test");
        newTeam.setWins(1);
        newTeam.setLosses(2);
        newTeam.setCur_rank(4);

        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/team/add", newTeam, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<String> getResponse = restTemplate.getForEntity("/team/13", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentGetContext = JsonPath.parse(getResponse.getBody());
        Number id = documentGetContext.read("$.id");
        assertThat(id).isEqualTo(13);
        String name = documentGetContext.read("$.name");
        assertThat(name).isEqualTo("test");
        Number wins = documentGetContext.read("$.wins");
        assertThat(wins).isEqualTo(1);
        Number losses = documentGetContext.read("$.losses");
        assertThat(losses).isEqualTo(2);
        Number rank = documentGetContext.read("$.cur_rank");
        assertThat(rank).isEqualTo(4);
    }

    @Test
    void shouldReturnTeamNameWinsAndLosses() {
        TeamEntity newTeam = new TeamEntity();
        newTeam.setId(2579);
        newTeam.setName("South Carolina GameCocks");
        newTeam.setWins(2);
        newTeam.setLosses(4);
        newTeam.setCur_rank(74);
        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/team/add", newTeam, Void.class);

        ResponseEntity<String> response = restTemplate.getForEntity("/team/2579", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(2579);
        String name = documentContext.read("$.name");
        assertThat(name).isEqualTo("South Carolina GameCocks");
        Number wins = documentContext.read("$.wins");
        assertThat(wins).isEqualTo(2);
        Number losses = documentContext.read("$.losses");
        assertThat(losses).isEqualTo(4);
        Number rank = documentContext.read("$.cur_rank");
        assertThat(rank).isEqualTo(74);
    }

    @Test
    void shouldUpdateTeamInRepository() {
        TeamEntity updatedTeam = new TeamEntity();
        updatedTeam.setId(13);
        updatedTeam.setName("test");
        updatedTeam.setWins(4);
        updatedTeam.setLosses(3);
        updatedTeam.setCur_rank(1);
        HttpEntity<TeamEntity> update = new HttpEntity<>(updatedTeam);
        ResponseEntity<Void> putResponse = restTemplate.exchange("/team/update/13", HttpMethod.PUT, update, Void.class);
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldNotReturnTeamInfoIfDoesNotExist() {
        ResponseEntity<String> response = restTemplate.getForEntity("/team/0000", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldNotUpdateTeamInfoIfTeamDoesNotExist() {
        TeamEntity newTeam = new TeamEntity();
        newTeam.setId(0000);
        newTeam.setName("anotherTest");
        newTeam.setWins(2);
        newTeam.setLosses(4);
        newTeam.setCur_rank(74);
        HttpEntity<TeamEntity> team = new HttpEntity<>(newTeam);
        ResponseEntity<Void> response = restTemplate.exchange("/team/update/0000", HttpMethod.PUT, team, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldDeleteTeamById() {
        TeamEntity newTeam = new TeamEntity();
        newTeam.setId(13);
        newTeam.setName("test");
        newTeam.setWins(1);
        newTeam.setLosses(2);
        newTeam.setCur_rank(4);

        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/team/add", newTeam, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<Void> response = restTemplate.exchange("/team/13", HttpMethod.DELETE, null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


    @Test
    void shouldNotDeleteTeamIfDoesNotExist() {
        ResponseEntity<String> response = restTemplate.exchange("/team/0000", HttpMethod.DELETE, null, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
