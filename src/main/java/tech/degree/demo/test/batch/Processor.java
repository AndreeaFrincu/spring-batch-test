package tech.degree.demo.test.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import tech.degree.demo.test.model.Testv2;

@Component
public class Processor implements ItemProcessor<Testv2, Testv2> {

    @Override
    public Testv2 process(Testv2 test) throws Exception {

        System.out.println("Inserting temp and hum data: " + test);
        return test;
    }
}
