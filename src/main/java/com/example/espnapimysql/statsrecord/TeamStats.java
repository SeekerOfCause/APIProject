package com.example.espnapimysql.statsrecord;

public record TeamStats(String team, String gamesPlayed, TeamOffenseStats offense, TeamDefenseStats defense) {
}
