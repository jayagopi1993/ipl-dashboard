package com.rm.ipldashboard.service;

import com.rm.ipldashboard.entity.ApplicationAccessLog;
import com.rm.ipldashboard.repo.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    @KafkaListener(topics = "basic-topic",groupId = "log-message",containerFactory = "concurrentKafkaListenerContainerFactory")
    public void display(ApplicationAccessLog applicationAccessLog){
        System.out.println(applicationAccessLog);
        logRepository.save(applicationAccessLog);
    }

}
