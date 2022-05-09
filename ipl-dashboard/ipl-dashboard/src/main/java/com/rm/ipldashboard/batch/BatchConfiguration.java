package com.rm.ipldashboard.batch;

import com.rm.ipldashboard.entity.Match;
import com.rm.ipldashboard.model.MatchInfo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<MatchInfo> reader() {
        return new FlatFileItemReaderBuilder<MatchInfo>()
                .name("matchInfoItemReader")
                .resource(new ClassPathResource("ipl-data-sample.csv"))
                .delimited()
                .names(new String[]{"id", "season", "city", "date", "team1", "team2", "tossWinner", "tossDecision", "result", "dlApplied", "winner", "winByRuns", "winByWickets", "playerOfMatch", "venue", "umpire1", "umpire2", "umpire3"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInfo>() {{
                    setTargetType(MatchInfo.class);
                }})
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO match (id,season,city,date,team1,team2,toss_winner,toss_decision,winner,win_by_runs,win_by_wickets,venue,umpire1,umpire2) VALUES (:id,:season,:city,:date,:team1,:team2,:tossWinner,:tossDecision,:winner,:winByRuns,:winByWickets,:venue,:umpire1,:umpire2)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public MatchProcessor processor() {
        return new MatchProcessor();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Match> writer) {
        return stepBuilderFactory.get("step1")
                .<MatchInfo, Match> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}
