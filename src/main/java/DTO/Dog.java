package DTO;

import Service.InitAnimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@SuppressWarnings("unused")
@Component
@Scope("prototype")
public class Dog extends AbstractAnimal {

    public Dog(String name, Double cost, String character, LocalDate birthDate, String secretInformation) {
        super(name, cost, character, birthDate, secretInformation);
    }

    @Autowired
    public Dog(InitAnimal initAnimal) {
        super(initAnimal);
    }
}
