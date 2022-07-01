package ru.job4j.passport.restservice.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendEmail(String message) {
        System.out.println(message);
    }
}
