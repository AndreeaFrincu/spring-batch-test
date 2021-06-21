package tech.degree.demo.test.config;

import lombok.NoArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import tech.degree.demo.test.model.Testv2;

@Configuration
@EnableBatchProcessing
@NoArgsConstructor
public class SpringBatchConfig {

    @Bean
    public Job readCSVFileJob(JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilderFactory,
                              ItemReader<Testv2> itemReader,
                              ItemProcessor<Testv2, Testv2> itemProcessor,
                              ItemWriter<Testv2> itemWriter) {

        Step step = stepBuilderFactory.get("step-file-load")
                .<Testv2, Testv2>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory
                .get("readCSVFileJob")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public FlatFileItemReader<Testv2> reader(@Value("${inputFile}") Resource resource) {
        FlatFileItemReader<Testv2> itemReader = new FlatFileItemReader<Testv2>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(resource);
        itemReader.setName("CSV-Reader");
        return itemReader;
    }

//    @Bean
//    public Processor processor(){
//        Processor itemProcessor = new Processor();
//        return itemProcessor;
//    }
//
//    @Bean
//    public Writer writer(){
//        Writer itemWriter = new Writer();
//        return itemWriter;
//    }

    @Bean
    public LineMapper<Testv2> lineMapper() {

        DefaultLineMapper<Testv2> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "datee", "timee", "temperature", "humidity");

        BeanWrapperFieldSetMapper<Testv2> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Testv2.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }
}
