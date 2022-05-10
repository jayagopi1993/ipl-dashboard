package com.rm.ipldashboard.service;

import com.rm.ipldashboard.entity.Match;

import java.util.List;
import java.util.Map;

public interface MatchService {

    // Pagination & Sorting(year,city)...
    public List<Match> getAllMatchs();

    public List<Match> getAllMatchsInTheGivenSeason(String season);

    public List<Match> getAllMatchsInTheGivenSeasonByYear(String season,String year);

    public Map<String,String> countOfMatchsByCity(String city);

    public Map<String,String> countOfWinsByTeam(String city);

}
