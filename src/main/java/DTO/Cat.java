package DTO;

import Service.InitAnimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@SuppressWarnings("unused")
@Component
@Scope("prototype")
public class Cat extends AbstractAnimal {

    @Autowired
    public Cat(InitAnimal initAnimal) {
        super(initAnimal);
    }

    public Cat(String name, Double cost, String character, LocalDate birthDate, String secretInformation) {
        super(name, cost, character, birthDate, secretInformation);
    }
}
