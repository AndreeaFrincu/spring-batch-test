package tech.degree.demo.test.batch;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.degree.demo.test.model.Testv2;
import tech.degree.demo.test.repository.Testv2Repo;

import java.util.List;

@Component
public class Writer implements ItemWriter<Testv2> {

    @Autowired
    private Testv2Repo testRepo;

    @Override
    public void write(List<? extends Testv2> test_data) throws Exception{

        System.out.println("Data saved for temp and hum: " + test_data);
        testRepo.saveAll(test_data);
    }
}
