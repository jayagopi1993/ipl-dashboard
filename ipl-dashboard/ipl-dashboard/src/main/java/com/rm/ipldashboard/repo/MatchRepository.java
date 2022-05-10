package com.rm.ipldashboard.repo;

import com.rm.ipldashboard.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match,String> {
}
