package DTO;

import Service.InitAnimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Objects;


public abstract class AbstractAnimal implements Animal {

    @JsonProperty(value = "breed")
    protected String breed; // порода
    @JsonProperty(value = "name")
    protected String name; // имя
    @JsonProperty(value = "cost")
    protected Double cost; // цена в магазине
    @JsonProperty(value = "character")
    protected String character; // характер
    @JsonProperty(value = "birthDate") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    protected LocalDate birthDate; // День рождения животного в формате dd-MM-yyyy
    @JsonProperty(value = "secretInformation")
    protected String secretInformation; // секретное поле

    public AbstractAnimal(InitAnimal initAnimal) {
        this.breed = this.getClass().getSimpleName();
        this.name = initAnimal.getName(this.breed);
        this.secretInformation = initAnimal.getSecret();
        this.cost = initAnimal.getCost();
        this.character = initAnimal.getCharacter();
        this.birthDate = initAnimal.getBirthDate();
   }

    public AbstractAnimal(String name, Double cost, String character, LocalDate birthDate, String secretInformation) {
        this.breed = this.getClass().getSimpleName();
        this.name = name;
        this.cost = cost;
        this.character = character;
        this.birthDate = birthDate;
        this.secretInformation = secretInformation;
    }

    @Override
    public int compare(Animal first, Animal second) {
        return first.getBirthDate().compareTo(second.getBirthDate());
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

    @Override @JsonGetter("breed")
    public String getBreed() {
        return breed;
    }

    @Override @JsonGetter("name")
    public String getName() {
        return name;
    }

    @Override @JsonGetter("cost")
    public Double getCost() {
        return (double) Math.round(cost*100)/100;
    }

    @Override @JsonGetter("character")
    public String getCharacter() {
        return character;
    }
    @Override @JsonGetter("birthDate")
    public LocalDate getBirthDate() {
        return birthDate;
    }
    @Override
    public String getSecretInformation() {
        return secretInformation;
    }
    @SuppressWarnings("unused")
    @JsonGetter("secretInformation")
    public String getSI() {
        return Base64.getEncoder().encodeToString(secretInformation.getBytes());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        return Objects.equals(breed, that.breed) && Objects.equals(name, that.name) && Objects.equals(cost, that.cost) && Objects.equals(character, that.character) && Objects.equals(birthDate, that.birthDate) && Objects.equals(secretInformation, that.secretInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(breed, name, cost, character, birthDate, secretInformation);
    }

    @Override
    public Animal setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Animal setCost(Double cost) {
        this.cost = cost;
        return this;
    }

    @Override
    public Animal setCharacter(String character) {
        this.character = character;
        return this;
    }

    @Override
    public Animal setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }
    @Override
    public Animal setSecretInformation(String secretInformation) {
        this.secretInformation = secretInformation;
        return this;
    }
}
