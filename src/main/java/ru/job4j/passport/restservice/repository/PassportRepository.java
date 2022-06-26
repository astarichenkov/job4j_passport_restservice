package ru.job4j.passport.restservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.passport.restservice.model.Passport;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PassportRepository extends CrudRepository<Passport, Long> {
    List<Passport> findBySeries(Integer series);

    List<Passport> findByExpireDateIsLessThan(LocalDate expireDate);

    List<Passport> findByExpireDateGreaterThanAndExpireDateLessThan(LocalDate expireDate, LocalDate expireDate1);

}
