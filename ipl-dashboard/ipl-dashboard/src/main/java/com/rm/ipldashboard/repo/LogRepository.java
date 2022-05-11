package com.rm.ipldashboard.repo;

import com.rm.ipldashboard.entity.ApplicationAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<ApplicationAccessLog,Long> {
}
