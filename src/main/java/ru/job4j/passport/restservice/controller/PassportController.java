package ru.job4j.passport.restservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.passport.restservice.model.Passport;
import ru.job4j.passport.restservice.service.PassportService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PassportController {

    private final PassportService passports;


    @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Passport> findAll() {
        return passports.findAll();
    }

}

