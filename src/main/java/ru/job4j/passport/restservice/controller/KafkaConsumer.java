package ru.job4j.passport.restservice.controller;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.passport.restservice.service.EmailService;

@EnableKafka
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "replacePassports")
    public void onApiCall(ConsumerRecord<Integer, String> input) {
        String message = input.value();
        emailService.sendEmail(message);
    }

}
