package tech.degree.demo.test.service;

import net.bytebuddy.asm.Advice;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tech.degree.demo.test.exceptions.TestNotFoundException;
import tech.degree.demo.test.model.Testv2;
import tech.degree.demo.test.repository.Testv2Repo;

import java.util.ArrayList;
import java.util.List;

@Service
public class Testv2Service {

    private final Testv2Repo testRepo;

    @Autowired
    public Testv2Service(Testv2Repo testRepo) {
        this.testRepo = testRepo;
    }

    public List<Testv2> findAllTests() {
        return testRepo.findAll();
    }

    public Testv2 findLastById() {

        List<Testv2> list = testRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        Testv2 last = list.get(0);
        return last;
    }
}