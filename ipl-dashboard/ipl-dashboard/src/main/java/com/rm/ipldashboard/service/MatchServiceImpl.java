package com.rm.ipldashboard.service;

import com.rm.ipldashboard.entity.Match;
import com.rm.ipldashboard.repo.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<Match> getAllMatchs() {
        return matchRepository.findAll();
    }

    @Override
    public List<Match> getAllMatchsInTheGivenSeason(String season) {
        return null;
    }

    @Override
    public List<Match> getAllMatchsInTheGivenSeasonByYear(String season, String year) {
        return null;
    }

    @Override
    public Map<String, String> countOfMatchsByCity(String city) {
        return null;
    }

    @Override
    public Map<String, String> countOfWinsByTeam(String city) {
        return null;
    }
}
