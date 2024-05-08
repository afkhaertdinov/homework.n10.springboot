import Service.AnimalRepositoryImpl;
import Service.ResultReader;
import Service.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;


public class HW_Spring {

    public static void main(String[] args) throws IOException {

        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class)) {

            AnimalRepositoryImpl animalRepository = (AnimalRepositoryImpl) context.
                    getBean("animalRepositoryImpl",15);

            System.out.println("Животные, которые родились в високосный год:");
            animalRepository.findLeapYearNames().entrySet().forEach(System.out::println);
            System.out.println();

            System.out.println("Животные, возраст которых старше 12 лет");
            animalRepository.findOlderAnimal(12).entrySet().forEach(System.out::println);
            System.out.println();

            // Печатаем средний возраст всех животных
            animalRepository.findAverageAge();
            System.out.println();

            // Печатаем список животных, возраст которых больше 5 лет
            // и стоимость которых больше средней стоимости всех животных:");
            animalRepository.findOldAndExpensive().forEach(System.out::println);
            System.out.println();

            System.out.println("Печатаем имена трёх животных с самой низкой ценой:");
            animalRepository.findMinConstAnimals().forEach(System.out::println);
            System.out.println();

            System.out.println();
            ResultReader.resultReader();

        }
    }
}
