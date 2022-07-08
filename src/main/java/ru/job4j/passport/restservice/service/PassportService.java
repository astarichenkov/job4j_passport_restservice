package ru.job4j.passport.restservice.service;

import org.springframework.stereotype.Service;
import ru.job4j.passport.restservice.model.Passport;
import ru.job4j.passport.restservice.repository.PassportRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PassportService {

    private final PassportRepository passportRepository;

    public PassportService(PassportRepository passports) {
        this.passportRepository = passports;
    }

    public List<Passport> findAll() {
        List<Passport> rsl = new ArrayList<>();
        passportRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    public Passport save(Passport passport) {
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
        Passport oldPassport = passportRepository.findById(passport.getId()).get();
        oldPassport.setFirstname(passport.getFirstname());
        oldPassport.setLastname(passport.getLastname());
        oldPassport.setBirthDate(passport.getBirthDate());
        oldPassport.setSeries(passport.getSeries());
        oldPassport.setNumber(passport.getNumber());
        return passportRepository.save(oldPassport);
    }

    public void delete(Long id) {
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
