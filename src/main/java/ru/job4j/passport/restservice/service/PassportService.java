package ru.job4j.passport.restservice.service;

import org.springframework.stereotype.Service;
import ru.job4j.passport.restservice.model.Passport;
import ru.job4j.passport.restservice.repository.PassportRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PassportService {

    private final PassportRepository passportRepository;

    public PassportService(PassportRepository passports) {
        this.passportRepository = passports;
    }

    public List<Passport> findAll() {
        return passportRepository.findAll();
    }

    public Passport save(Passport passport) {
        if (passportRepository.findByNumberAndSeries(passport.getNumber(), passport.getSeries()).isPresent()) {
            throw new IllegalArgumentException("passport with such a series and number already exists");
        }

        LocalDate birthDate = passport.getBirthDate();
        int years = LocalDate.now().getYear() - birthDate.getYear();
        LocalDate expireDate = LocalDate.now();
        if (years < 20) {
            expireDate = birthDate.plusYears(20);
        }
        if (years >= 20 && years < 45) {
            expireDate = birthDate.plusYears(45);
        }
        if (years >= 45) {
            expireDate = LocalDate.of(2200, 1, 1);
        }
        passport.setExpireDate(expireDate);
        return passportRepository.save(passport);
    }

    public Passport update(Passport passport) {
        Passport oldPassport;
        Optional<Passport> opt = passportRepository.findById(passport.getId());
        if (opt.isEmpty()) {
            throw new NoSuchElementException("passport not found by id");
        } else {
            oldPassport = opt.get();
        }
        oldPassport.setFirstname(passport.getFirstname());
        oldPassport.setLastname(passport.getLastname());
        oldPassport.setBirthDate(passport.getBirthDate());
        oldPassport.setSeries(passport.getSeries());
        oldPassport.setNumber(passport.getNumber());
        return passportRepository.save(oldPassport);
    }

    public void delete(Long id) {
        if (passportRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("passport not found by id");
        }
        passportRepository.deleteById(id);
    }

    public List<Passport> findBySeries(Integer series) {
        return passportRepository.findBySeries(series);
    }

    public List<Passport> expiredPassports() {
        return passportRepository.findByExpireDateIsLessThan(LocalDate.now());
    }

    public List<Passport> findReplaceablePassports() {
        return passportRepository.findByExpireDateGreaterThanAndExpireDateLessThan(LocalDate.now(), LocalDate.now().plusMonths(3));
    }
}
