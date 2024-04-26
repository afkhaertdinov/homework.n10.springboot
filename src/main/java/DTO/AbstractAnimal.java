package DTO;

import Service.InitAnimal;
import lombok.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Setter @Getter
public abstract class AbstractAnimal implements Animal {

    protected String breed; // порода
    protected String name; // имя
    protected Double cost; // цена в магазине
    protected String character; // характер
    protected LocalDate birthDate; // День рождения животного в формате dd-MM-yyyy
    protected String secretInformation; // секретное поле

    public AbstractAnimal(InitAnimal initAnimal) {
        this.secretInformation = initAnimal.getSecret();
        this.name = initAnimal.getName();
        this.cost = initAnimal.getCost();
        this.character = initAnimal.getCharacter();
        this.birthDate = initAnimal.getBirthDate();
   }

    public Double getCost() {
        return (double) Math.round(cost*100)/100;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                " {name='" + name + '\'' +
                ", cost= $" + String.format("%.2f",cost) + '\'' +
                ", character='" + character + '\'' +
                ", secret='" + secretInformation + '\'' +
                ", birthDate='" + (birthDate == null?"null":birthDate.
                        format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))) +
                '}';
    }

}
