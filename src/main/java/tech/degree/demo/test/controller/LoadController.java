package tech.degree.demo.test.controller;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.degree.demo.test.model.Testv2;
import tech.degree.demo.test.service.Testv2Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/load")
public class LoadController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    private final Testv2Service testService;

    public LoadController(Testv2Service testService) {
        this.testService = testService;
    }

    @GetMapping
    public BatchStatus load() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        System.out.println("JobExecution: " + jobExecution.getStatus());

        System.out.println("Batch is running...");
        while(jobExecution.isRunning()) {
            System.out.println("...");
        }

        return jobExecution.getStatus();
    }

    @GetMapping(value = {"/all"})
    public ResponseEntity<List<Testv2>> getAllTests() {
        List<Testv2> tests = testService.findAllTests();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    // get last entry from api
    @GetMapping("/find/last")
    public ResponseEntity<Testv2> getLastById() {
        Testv2 test = testService.findLastById();
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

//    @DeleteMapping(value = {"/delete/{id}"})
//    public ResponseEntity<?> deleteTest(@PathVariable("id") Integer id) {
//        testService.deleteTest(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}