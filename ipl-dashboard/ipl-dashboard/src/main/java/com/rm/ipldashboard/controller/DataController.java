package com.rm.ipldashboard.controller;

import com.rm.ipldashboard.entity.Match;
import com.rm.ipldashboard.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    private MatchService matchService;

    @GetMapping("matchs/{season}")
    public List<Match> getMatchsBySeason(@PathVariable("season") String season){
        return matchService.getAllMatchsInTheGivenSeason(season);
    }
}
