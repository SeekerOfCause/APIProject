package com.example.espnapimysql.statsrecord;

import jakarta.persistence.Id;

public record TeamRecord(Integer id, String name, Integer wins, Integer losses, Integer rank) {
}
