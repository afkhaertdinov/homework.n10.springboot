package DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Класс Entry создан, для Сериализации/Десиарилизации Map<Animal,Integer>
 */
@Getter
public class EntryAnimalInteger {
    private final Animal animal;
    private final Integer age;

    public EntryAnimalInteger(@JsonProperty("animal") Animal animal, @JsonProperty("age") Integer age) {
        this.animal = animal;
        this.age = age;
    }
}
