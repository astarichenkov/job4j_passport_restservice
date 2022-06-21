package ru.job4j.passport.restservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.passport.restservice.model.Passport;

@Repository
public interface PassportRepository extends CrudRepository<Passport, Long> {

}
