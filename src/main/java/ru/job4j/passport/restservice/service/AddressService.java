package ru.job4j.passport.restservice.service;

import org.springframework.stereotype.Service;
import ru.job4j.passport.restservice.repository.AddressRepository;

@Service
public class AddressService {

    private final AddressRepository addresses;

    public AddressService(AddressRepository addresses) {
        this.addresses = addresses;
    }
}
