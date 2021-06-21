package tech.degree.demo.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.degree.demo.test.model.Testv2;

import java.util.List;
import java.util.Optional;

@Repository
public interface Testv2Repo extends JpaRepository<Testv2,Integer> {

//    Optional<Test> findTestByEntryDateEntryTime(String entry_date, String entry_time);
}
