package DTO;

import java.time.LocalDate;


public interface Animal {
    @SuppressWarnings("unused")
    String getBreed();
    String getName();
    @SuppressWarnings("unused")
    Double getCost();
    @SuppressWarnings("unused")
    String getCharacter();
    LocalDate getBirthDate();
    String getSecretInformation();
    void setSecretInformation(String secretInformation);
}
