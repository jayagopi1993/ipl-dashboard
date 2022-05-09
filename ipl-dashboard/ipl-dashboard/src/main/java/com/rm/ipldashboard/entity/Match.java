package com.rm.ipldashboard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "match")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Match {

    @Id
    private String id;
    private String season;
    private String city;
    private String date;
    private String team1;
    private String team2;
    private String tossWinner;
    private String tossDecision;
    private String winner;
    private String winByRuns;
    private String winByWickets;
    private String venue;
    private String umpire1;
    private String umpire2;

}
