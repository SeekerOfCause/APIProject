package com.example.espnapimysql.statsrecord;

public record TeamDefenseStats(Integer totalTackles, Integer soloTackles, Integer assistTackles, TeamDPassingStats passing, TeamDRushingStats rushing) {
}
