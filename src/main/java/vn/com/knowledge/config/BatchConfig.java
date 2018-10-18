package vn.com.knowledge.config;

import com.mongodb.MongoClient;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import vn.com.knowledge.model.Data;
import vn.com.knowledge.process.CustomItemProcess;
import vn.com.knowledge.read.CustomItemReader;
import vn.com.knowledge.writer.CustomItemWriter;

import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration
public class BatchConfig {


    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSource dataSource;


    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Data, Data> chunk(1)
                .reader(new CustomItemReader())
                .processor(new CustomItemProcess())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemWriter<Data> writer() {
        MongoItemWriter<Data> writer = new MongoItemWriter<Data>();
        try {
            writer.setTemplate(mongoTemplate());
        } catch (Exception e) {
        }
        writer.setCollection("data");
        return writer;
    }
    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(), "batchdb");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }
}
