package DTO;

import java.time.LocalDate;
import java.util.Comparator;

public interface Animal extends Comparator<Animal> {
    @SuppressWarnings("unused")
    String getBreed();
    String getName();
    @SuppressWarnings("unused")
    Double getCost();
    @SuppressWarnings("unused")
    String getCharacter();
    LocalDate getBirthDate();
    String getSecretInformation();
    Animal setSecretInformation(String secretInformation);
    Animal setName(String name);
    Animal setCost(Double cost);
    Animal setCharacter(String character);
    Animal setBirthDate(LocalDate birthDate);
    }
