package ru.job4j.passport.restservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "passports")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer series;
    private Long num;
    private Calendar releaseDate;
    private String firstname;
    private String lastname;

    @OneToMany
    private List<Address> registrationAddress;

    @Override
    public String toString() {
        return "Passport{"
                + "id=" + id
                + ", series=" + series
                + ", number=" + num
                + ", releaseDate=" + releaseDate
                + ", firstname='" + firstname + '\''
                + ", lastname='" + lastname + '\''
                + ", registrationAddress='" + registrationAddress + '\''
                + ", addresses=" + registrationAddress
                + '}';
    }
}
