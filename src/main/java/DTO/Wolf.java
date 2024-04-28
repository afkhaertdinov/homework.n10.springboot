package DTO;

import Service.InitAnimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
@Scope("prototype")
public class Wolf extends AbstractAnimal {

    @Autowired
    public Wolf(InitAnimal initAnimal) {
        super(initAnimal);
    }
}
