package com.example.espnapimysql;

import com.example.espnapimysql.database.TeamEntity;
import com.example.espnapimysql.database.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "/team")
public class MainController {
    @Autowired
    private UserRepository userRepository;
    private TeamRepository teamRepository;

    public MainController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addTeamByEspnId(@RequestBody TeamEntity team, UriComponentsBuilder ucb) {
        teamRepository.save(team);
        int id = team.getId();
        URI locationOfNewTeam = ucb
                .path("team/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(locationOfNewTeam).build();

//        try {
//            teamRepository.save(team);
//            URI locationOfNewTeam = ucb
//                    .path("team/{id}")
//                    .buildAndExpand(team.getId())
//                    .toUri();
//            return ResponseEntity.created(locationOfNewTeam).build();
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
    }

    @GetMapping(path = "/{requestedTeamId}")
    public ResponseEntity<Object> getTeamByEspnId(@PathVariable int requestedTeamId) {
        Optional<TeamEntity> reqTeam = teamRepository.findById(requestedTeamId);
        if (reqTeam.isPresent()) {
            return ResponseEntity.ok(reqTeam);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/update/{teamId}")
    public ResponseEntity<Object> updateTeamByEspnId(@PathVariable int teamId, @RequestBody TeamEntity updatedTeam) {
        Optional<TeamEntity> reqTeam = teamRepository.findById(teamId);
        if (reqTeam.isPresent()) {
            teamRepository.save(updatedTeam);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<TeamEntity> getAllUsers() {
        // This returns a JSON or XML with the users
        return teamRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Iterable<TeamEntity>> deleteTeamEntity(@PathVariable int id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }
}