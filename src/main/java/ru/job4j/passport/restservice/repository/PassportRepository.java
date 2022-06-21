package ru.job4j.passport.restservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.passport.restservice.model.Passport;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PassportRepository extends CrudRepository<Passport, Long> {
    Optional<Passport> findBySeries(Integer series);

    List<Passport> findByExpireDateIsLessThan(LocalDate expireDate);

    List<Passport> findByExpireDateGreaterThanAndExpireDateLessThan(LocalDate expireDate, LocalDate expireDate1);

}
