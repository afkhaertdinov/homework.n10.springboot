package Service;

import DTO.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Base64;

public class AnimalDeserializer extends StdDeserializer<Animal> {

    public AnimalDeserializer() {
        this(null);
    }

    public AnimalDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public Animal deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);

        String breed; // Получаем поле "breed"
        if (node.has("breed") && (node.get("breed").isTextual()))
            breed = node.get("breed").asText();
        else
            throw new IllegalStateException("Unexpected value: \"breed\" in " + node);

        String name; // Получаем поле "name"
        if (node.has("name") && (node.get("name").isTextual())) {
            name = node.get("name").asText();
            if (name.isEmpty()) {
                System.err.println("Пустое поле \"name\" в " + node);
                name = "no Name";
            }
        } else {
            System.err.println("Отсутствует или неверное поле \"name\" в " + node);
            name = "no Name";
        }

        double cost; // Получаем поле "cost"
        if (node.has("cost") && (node.get("cost").isDouble())) {
            cost = node.get("cost").doubleValue();
        } else {
            System.err.println("Отсутствует или неверное поле \"cost\" в " + node);
            cost = 0.;
        }

        String character; // Получаем поле "character"
        if (node.has("character") && (node.get("character").isTextual())) {
            character = node.get("character").asText();
            if (character.isEmpty()) {
                System.err.println("Пустое поле \"character\" в " + node);
                character = "no Character";
            }
        } else {
            System.err.println("Отсутствует или неверное поле \"character\" в " + node);
            character = "no Character";
        }

        LocalDate birthDate = null; // Получаем поле "birthDate"
        if (node.has("birthDate"))
            try {
                birthDate = LocalDate.parse(node.get("birthDate").asText(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } catch (DateTimeParseException exception) {
                System.err.println("Отсутствует или неверное поле \"birthDate\" в " + node);
            }

        String secretInformation; // Получаем поле "secretInformation"
        if (node.has("secretInformation") && (node.get("secretInformation").isTextual())) {
            String string = node.get("secretInformation").asText();
            if (string.isEmpty()) {
                System.err.println("Пустое поле \"secretInformation\" в " + node);
                secretInformation = "no SecretInformation";
            } else {
                byte[] decodedBytes = Base64.getDecoder().decode(string);
                secretInformation = new String(decodedBytes);
            }
        } else {
            System.err.println("Отсутствует или неверное поле \"secretInformation\" в " + node);
            secretInformation = "no SecretInformation";
        }

        Animal animal;
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class)) {
            animal = switch (breed) {
                case "Cat" -> context.getBean(Cat.class);
                case "Wolf" -> context.getBean(Wolf.class);
                case "Dog" -> context.getBean(Dog.class);
                case "Shark" -> context.getBean(Shark.class);
                default -> throw new IllegalStateException("Unexpected value: \"breed\" in " + node);
            };
            animal.setName(name)
                    .setCost(cost)
                    .setCharacter(character)
                    .setBirthDate(birthDate)
                    .setSecretInformation(secretInformation);
            return animal;
        }
    }
}
