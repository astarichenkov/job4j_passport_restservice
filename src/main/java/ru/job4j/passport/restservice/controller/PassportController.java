package ru.job4j.passport.restservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.passport.restservice.model.Passport;
import ru.job4j.passport.restservice.service.PassportService;

import java.util.List;

@RestController
@RequestMapping("/passport")
@RequiredArgsConstructor
public class PassportController {

    private final PassportService passportService;

    @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Passport>> findBySeries(@RequestParam(name = "series", required = false) Integer series) {
        if (series == null) {
            return new ResponseEntity<>(passportService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(passportService.findBySeries(series), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
        return new ResponseEntity<>(passportService.save(passport), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@RequestBody Passport passport, @PathVariable Long id) {
        passport.setId(id);
        this.passportService.update(passport);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
            passportService.delete(id);
            return new ResponseEntity<>(
                    HttpStatus.OK
            );
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<Passport>> findExpiredPassports() {
        return new ResponseEntity<>(passportService.expiredPassports(), HttpStatus.OK);
    }

    @GetMapping("/find-replaceable")
    public ResponseEntity<List<Passport>> findReplaceablePassports() {
        return new ResponseEntity<>(passportService.findReplaceablePassports(), HttpStatus.OK);
    }

}

