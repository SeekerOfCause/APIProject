package com.example.espnapimysql;

import com.example.espnapimysql.statsrecord.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("AssertBetweenInconvertibleTypes")
@JsonTest
public class TeamEntryJsonTest {

    @Autowired
    private JacksonTester<TeamRecord> json;

    @Test
    public void teamEntryJsonTest() throws IOException {
//        TeamOPassingStats GCOPassing = new TeamOPassingStats(78, 48,62, 8, 3, 7);
//        TeamORushingStats GCORushing = new TeamORushingStats(49, 27, 6, 4);
//        TeamDPassingStats GCDPassing = new TeamDPassingStats(16, 49);
//        TeamDRushingStats GCDRushing = new TeamDRushingStats(3, 16, 9, 9, 29);
//        TeamDefenseStats GCDefense = new TeamDefenseStats(13, 15, 2, GCDPassing, GCDRushing);
//        TeamOffenseStats GCOffense = new TeamOffenseStats(GCOPassing, GCORushing);
        TeamRecord GameCocks = new TeamRecord(2579,"South Carolina Gamecocks", 1, 4, 0);
        JsonContent<TeamRecord> testTeam = json.write(GameCocks);
        assertThat(testTeam).isStrictlyEqualToJson("expected.json");
        assertThat(testTeam).hasJsonPathNumberValue("@.id");
        assertThat(testTeam).extractingJsonPathNumberValue("@.id").isEqualTo(2579);
        assertThat(testTeam).hasJsonPathStringValue("@.name");
        assertThat(testTeam).extractingJsonPathStringValue("@.name").isEqualTo("South Carolina Gamecocks");
        assertThat(testTeam).hasJsonPathNumberValue("@.wins");
        assertThat(testTeam).extractingJsonPathNumberValue("@.wins").isEqualTo(1);
        assertThat(testTeam).hasJsonPathNumberValue("@.losses");
        assertThat(testTeam).extractingJsonPathNumberValue("@.losses").isEqualTo(4);
        assertThat(testTeam).hasJsonPathNumberValue("@.rank");
        assertThat(testTeam).extractingJsonPathNumberValue("@.rank").isEqualTo(0);
//        assertThat(testTeam).hasJsonPathValue("@.offense.passing");
//        assertThat(testTeam).extractingJsonPathValue("@.offense.passing.passingAttempts").isEqualTo(78);
//        assertThat(testTeam).extractingJsonPathValue("@.offense.passing.passingCompletions").isEqualTo(48);
//        assertThat(testTeam).extractingJsonPathValue("@.offense.passing.passingAvgGain").isEqualTo(62);
//        assertThat(testTeam).extractingJsonPathValue("@.offense.passing.passingTD").isEqualTo(8);
//        assertThat(testTeam).extractingJsonPathValue("@.offense.passing.passingTwoPtConv").isEqualTo(3);
//        assertThat(testTeam).extractingJsonPathValue("@.offense.passing.interceptions").isEqualTo(7);
//        assertThat(testTeam).hasJsonPathValue("@.offense.rushing");
//        assertThat(testTeam).extractingJsonPathValue("@.offense.rushing.rushingYards").isEqualTo(49);
//        assertThat(testTeam).extractingJsonPathValue("@.offense.rushing.rushingAttempts").isEqualTo(27);
//        assertThat(testTeam).extractingJsonPathValue("@.offense.rushing.rushingTD").isEqualTo(6);
//        assertThat(testTeam).extractingJsonPathValue("@.offense.rushing.rushingTwoPtConv").isEqualTo(4);
//        assertThat(testTeam).hasJsonPathValue("@.defense");
//        assertThat(testTeam).extractingJsonPathValue("@.defense.totalTackles").isEqualTo(13);
//        assertThat(testTeam).extractingJsonPathValue("@.defense.soloTackles").isEqualTo(15);
//        assertThat(testTeam).extractingJsonPathValue("@.defense.assistTackles").isEqualTo(2);
//        assertThat(testTeam).hasJsonPathValue("@.defense.passing");
//        assertThat(testTeam).hasJsonPathValue("@.defense.rushing");
//        assertThat(testTeam).extractingJsonPathValue("@.defense.passing.interceptions").isEqualTo(16);
//        assertThat(testTeam).extractingJsonPathValue("@.defense.passing.yardsFromInterceptions").isEqualTo(49);
//        assertThat(testTeam).extractingJsonPathValue("@.defense.rushing.kicksBlocked").isEqualTo(3);
//        assertThat(testTeam).extractingJsonPathValue("@.defense.rushing.hurries").isEqualTo(16);
//        assertThat(testTeam).extractingJsonPathValue("@.defense.rushing.safeties").isEqualTo(9);
//        assertThat(testTeam).extractingJsonPathValue("@.defense.rushing.sacks").isEqualTo(9);
//        assertThat(testTeam).extractingJsonPathValue("@.defense.rushing.yardsLostFromSack").isEqualTo(29);
    }
}
