package com.rm.ipldashboard.batch;

import com.rm.ipldashboard.entity.Match;
import com.rm.ipldashboard.model.MatchInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class MatchProcessor implements ItemProcessor<MatchInfo, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchProcessor.class);

    @Override
    public Match process(MatchInfo matchInfo) throws Exception {
        return transform(new Match(),matchInfo);
    }

    private Match transform(Match match, MatchInfo matchInfo) {
        match.setId(matchInfo.getId());
        match.setCity(matchInfo.getCity().toUpperCase());
        match.setSeason(matchInfo.getSeason());
        match.setDate(matchInfo.getDate());

        if ("bat".equalsIgnoreCase(matchInfo.getTossDecision())) {
            match.setTeam1(matchInfo.getTossWinner());
            match.setTeam2(matchInfo.getTeam1() != matchInfo.getTossWinner() ? matchInfo.getTeam1() : matchInfo.getTeam2());
        } else {
            match.setTeam1(matchInfo.getTeam1() != matchInfo.getTossWinner() ? matchInfo.getTeam1() : matchInfo.getTeam2());
            match.setTeam2(matchInfo.getTossWinner());
        }
        match.setVenue(matchInfo.getVenue().toUpperCase());
        match.setWinner(matchInfo.getWinner());
        match.setWinByRuns(matchInfo.getWinByRuns());
        match.setWinByWickets(matchInfo.getWinByWickets());
        match.setTossDecision(matchInfo.getTossDecision());
        match.setTossWinner(matchInfo.getTossWinner());
        match.setUmpire1(matchInfo.getUmpire1());
        match.setUmpire2(matchInfo.getUmpire2());
        log.info("Converting (" + matchInfo + ") into (" + match + ")");
        return match;
    }
}
