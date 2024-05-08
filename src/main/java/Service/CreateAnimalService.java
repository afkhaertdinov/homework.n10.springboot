package Service;

import DTO.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreateAnimalService {
    /**
     * Метод создаёт случайных животных, результат возвращает и сохраняет в файл.
     *
     * @param num количество животных, которых необходимо создать в методе;
     * @return возвращает коллекцию созданных животных HashMap<String, List<Animal>>, с группировкой по ключу <Тип животных>;
     * @throws IOException необходимо обработать исключение работы с файлом "src/main/resources/Animals/logData.txt".
     */
    @SuppressWarnings("unused")
    public List<Animal> getAnimals(int num) throws IOException {
        List<Animal> animalList = new ArrayList<>();
        SecureRandom randomNum = new SecureRandom();
        Animal animal;
        Path path = Paths.get("src/main/resources/Animals/logData.txt");
        if (Files.exists(path)) // удаляем старый файл при его наличии
            Files.delete(path);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
             AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class)) {
            bufferedWriter.write("  N. | Breef | Name | Cost | BirthDate\n-----+-------+------+------+----------\n");
            for (int i = 0; i < num; i++) {
                animal = switch (randomNum.nextInt(4)) {
                    case 0 -> context.getBean(Cat.class);
                    case 1 -> context.getBean(Dog.class);
                    case 2 -> context.getBean(Wolf.class);
                    case 3 -> context.getBean(Shark.class);
                    default -> throw new IllegalStateException("Unexpected value: randomNum.nextInt()");
                };
                animalList.add(animal);
                bufferedWriter.write(String.format("%3d. | %s | %s | %.2f | %s\n", i + 1,
                        animal.getBreed(), animal.getName(), animal.getCost(), animal.getBirthDate()));
                bufferedWriter.flush();
            }
            return animalList;
        }
    }
}
