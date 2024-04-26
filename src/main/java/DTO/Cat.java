package DTO;

import Service.InitAnimal;
import org.springframework.stereotype.Component;

@Component
public class Cat extends AbstractAnimal {

    public Cat(InitAnimal initAnimal) {
        super(initAnimal);
        this.breed = "Cat";
    }


}
