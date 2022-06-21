package ru.job4j.passport.restservice.service;

import org.springframework.stereotype.Service;
import ru.job4j.passport.restservice.model.Passport;
import ru.job4j.passport.restservice.repository.PassportRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassportService {

    private final PassportRepository passports;

    public PassportService(PassportRepository passports) {
        this.passports = passports;
    }

    public List<Passport> findAll() {
        List<Passport> rsl = new ArrayList<>();
        passports.findAll().forEach(rsl::add);
        return rsl;
    }

    public Passport save(Passport passport) {
        return passports.save(passport);
    }

    public Passport update(Passport passport) {
        Passport oldPassport = passports.findById(passport.getId()).get();
        oldPassport.setRegistrationAddress(passport.getRegistrationAddress());
        return passports.save(oldPassport);
    }


}
