package com.rm.ipldashboard.service;

import com.rm.ipldashboard.entity.ApplicationAccessLog;
import com.rm.ipldashboard.entity.Match;
import com.rm.ipldashboard.repo.LogRepository;
import com.rm.ipldashboard.repo.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private KafkaTemplate<String,ApplicationAccessLog> kafkaTemplate;

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<Match> getAllMatchs() {
        return matchRepository.findAll();
    }

    @Override
    public List<Match> getAllMatchsInTheGivenSeason(String season) {
        List<Match> filteredMatchs = matchRepository.findBySeason(season);
        ApplicationAccessLog applicationAccessLog = new ApplicationAccessLog();
        applicationAccessLog.setLogMessage("Total Number of Matchs in the given Seasion["+season+"] is : " + filteredMatchs.size());
        kafkaTemplate.send("basic-topic",applicationAccessLog);
        return filteredMatchs;
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
