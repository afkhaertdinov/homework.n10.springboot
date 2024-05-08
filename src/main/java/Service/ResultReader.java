package Service;

import DTO.Animal;
import DTO.EntryAnimalInteger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface ResultReader {

    /**
     * Домашнее задание № 9 пункт 4.
     * Создать класс ResultReader, который будет выполнять действия:<br/>
     * a.	Начитывать результат из YOUR_PROJECT/recources/results/findOlderAnimals.json в объект
     * и выводить в консоль полученный объект. При этом строка secretInformation должна иметь
     * уже читаемый формат, а не зашифрованный.<br/> Сразу стоит учесть, что этот метод должен
     * вызываться строго после того, как был выполнен метод findOldedrAnimals.<br/>
     * b.	Считать количество строк в файле YOUR_PROJECT/recources/animals/logData.txt и выводить
     * значение на экран.
     *
     * @throws IOException исключение может произойти при чтении данных из файлов
     */
    static void resultReader() throws IOException {
        File inputJson = new File("src/main/resources/Results/findOlderAnimals.json");

        final SimpleModule module = new SimpleModule();
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        module.addDeserializer(Animal.class, new AnimalDeserializer());
        objectMapper.registerModule(module);

        List<EntryAnimalInteger> animalValue = objectMapper.readValue(inputJson, new TypeReference<>() {
        });

        System.out.println("\nВыводим на консоль результат десиарилизации файла src/main/resources/Results/findOlderAnimals.json");
        animalValue.forEach(e -> System.out.println("Animal: " + e.getAnimal() + "   Age: " + e.getAge()));

        Path path = Paths.get("src/main/resources/Animals/logData.txt");
        if (Files.exists(path))
            try (BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                System.out.println("\nВыводим на консоль файл src/main/resources/Animals/logData.txt:");
//                        bufferedReader.lines().count() + " строк:");
                AtomicInteger i = new AtomicInteger();
                bufferedReader.lines().peek(s -> i.getAndIncrement()).forEach(System.out::println);
                System.out.println("итого: " + i + " строк.");
            }

    }
}

