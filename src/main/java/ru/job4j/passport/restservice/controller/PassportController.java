package ru.job4j.passport.restservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.passport.restservice.model.Passport;
import ru.job4j.passport.restservice.service.PassportService;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PassportController {

    private final PassportService passports;

    @Transactional
    @Transient
    @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Passport>> findBySeries(@RequestParam(name = "series", required = false) Integer series) {
        if (series == null) {
            return new ResponseEntity<>(passports.findAll(), HttpStatus.OK);
        }
        var opt = passports.findBySeries(series);
        if (opt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(List.of(opt.get()), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
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

        return new ResponseEntity<>(passports.save(passport), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@RequestBody Passport passport) {
        this.passports.update(passport);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            passports.delete(id);
            return new ResponseEntity<>(
                    HttpStatus.OK
            );
        } catch (Throwable e) {
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<Passport>> findExpiredPassports() {
        return new ResponseEntity<>(passports.expiredPassports(), HttpStatus.OK);
    }

    @GetMapping("/find-replaceable")
    public ResponseEntity<List<Passport>> findReplaceablePassports() {
        return new ResponseEntity<>(passports.findReplaceablePassports(), HttpStatus.OK);
    }

}

