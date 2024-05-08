package Service;

import DTO.*;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;


class AnimalRepositoryImplTest {
    static List<Animal> animals = new ArrayList<>();
    static AnimalRepositoryImpl animalRepository;

    @BeforeAll
    static void init() {
        animals.add(new Dog("Orange", 569.03, "Active",
                LocalDate.of(2004, 12, 17), "Lullaby"));
        animals.add(new Shark("Stalker", 413.23, "Treacherous",
                LocalDate.of(2014, 7, 17), "Halcyon"));
        animals.add(new Dog("Ariana", 118.94, "Compliant",
                LocalDate.of(2004, 7, 1), "Golden"));
        animals.add(new Wolf("Aegenwulf", 303.65, "Capricious",
                LocalDate.of(2004, 1, 31), "Marigold"));
        animals.add(new Shark("Kenobi", 227.63, "Active",
                LocalDate.of(2006, 5, 24), "Thrush"));
        animals.add(new Wolf("Shaw", 911.91, "Compliant",
                LocalDate.of(2003, 6, 29), "Halcyon"));
        animals.add(new Cat("Daisy", 924.27, "Tricky",
                LocalDate.of(2017, 2, 13), "Murmuring"));
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class)) {
            animalRepository = (AnimalRepositoryImpl) context.
                    getBean("animalRepositoryImpl", 1);
        }
    }

    @BeforeEach
    void setUp() {
        // заменяем список животных на тестовый
        animalRepository.setAnimals(animals);
    }

    /**
     * <h3>Тест метода AnimalRepositoryImpl.findLeapYearNames </h3>
     * Тест 1. Проверяем успешность отработки успешных данных, проверяем, что на выходе все животные рождённые в високосный год.<br>
     * Тест 2. Проверяем, что метод вернёт пустую коллекцию, когда нет верных значений.<br>
     */
    @Test
    @DisplayName("Тест метода findLeapYearNames()")
    void findLeapYearNames() {
        Map<String, LocalDate> etalonMap, testMap;

        //TODO Тест-1. Проверяем, что метод вернул только животных, которые родились в високосный год.
        {
            //создаём шаблон с верными значениями
            etalonMap = new HashMap<>();
            etalonMap.put("Dog Orange", LocalDate.parse("2004-12-17"));
            etalonMap.put("Dog Ariana", LocalDate.parse("2004-07-01"));
            etalonMap.put("Wolf Aegenwulf", LocalDate.parse("2004-01-31"));

            //вызываем AnimalRepositoryImpl.findLeapYearNames() и получаем в testMap данные для теста
            testMap = animalRepository.findLeapYearNames();

            assertIterableEquals(etalonMap.keySet(), testMap.keySet());
            assertIterableEquals(etalonMap.values(), testMap.values());
        }

        //TODO Тест-2. Проверяем, что метод вернёт пустую коллекцию, когда нет верных значений.
        {
            animalRepository.setAnimals(new ArrayList<>() { // Инициализируем животными с невисокосным годом
                {
                    add(animals.get(1));
                    add(animals.get(4));
                    add(animals.get(5));
                    add(animals.get(6));
                }
            });
            //вызываем AnimalRepositoryImpl.findLeapYearNames() и получаем в testMap данные для теста
            testMap = animalRepository.findLeapYearNames();

            Assertions.assertTrue(testMap.isEmpty());
        }
    }

    /**
     * <h3>Тест метода AnimalRepositoryImpl.findOlderAnimal </h3>
     * Тест 1. Проверяем успешность отработки успешных данных, когда есть животные старше указанного возраста.<br>
     * Тест 2. Проверяем успешность отработки неуспешных данных, когда нет животных старше указанного возраста.<br>
     * <li> нет смысла проверять передаваемый переметр на отрицательное значение, при переданном отрицательном параметре выведутся все животные, т.к. будут старше указанного возраста.</li>
     */
    @Test
    @DisplayName("Тест метода findOlderAnimal()")
    void findOlderAnimal() {
        Map<Animal, Integer> etalonMap, testMap;

        //TODO Тест-1. Проверяем, что четыре животных старше 18 лет.
        {
            //создаём шаблон с верными значениями
            etalonMap = new HashMap<>();
            etalonMap.put(new Dog("Orange", 569.03, "Active",
                            LocalDate.of(2004, 12, 17), "Lullaby"),
                    Period.between(LocalDate.of(2004, 12, 17), LocalDate.now()).getYears());
            etalonMap.put(new Dog("Ariana", 118.94, "Compliant",
                            LocalDate.of(2004, 7, 1), "Golden"),
                    Period.between(LocalDate.of(2004, 7, 1), LocalDate.now()).getYears());
            etalonMap.put(new Wolf("Aegenwulf", 303.65, "Capricious",
                            LocalDate.of(2004, 1, 31), "Marigold"),
                    Period.between(LocalDate.of(2004, 1, 31), LocalDate.now()).getYears());
            etalonMap.put(new Wolf("Shaw", 911.91, "Compliant",
                            LocalDate.of(2003, 6, 29), "Halcyon"),
                    Period.between(LocalDate.of(2003, 6, 29), LocalDate.now()).getYears());
            //вызываем AnimalRepositoryImpl.findOlderAnimal() и получаем в testMap данные для теста
            try {
                testMap = animalRepository.findOlderAnimal(18);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //проверяем на идеинтичность эталонную коллекцию etalonMap и тестовую коллекцию testMap
            assertIterableEquals(etalonMap.keySet(), testMap.keySet());
            assertIterableEquals(etalonMap.values(), testMap.values());
        }

        //TODO Тест-2. Проверяем, что нет животных старше 25 лет, и должны получить самое старшее животное.
        {
            //создаём шаблон с верными значениями
            etalonMap = new HashMap<>();
            etalonMap.put(new Wolf("Shaw", 911.91, "Compliant",
                            LocalDate.of(2003, 6, 29), "Halcyon"),
                    Period.between(LocalDate.of(2003, 6, 29), LocalDate.now()).getYears());
            try {
                //вызываем AnimalRepositoryImpl.findOlderAnimal() и получаем в testMap данные для теста
                testMap = animalRepository.findOlderAnimal(25);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //проверяем на идеинтичность эталонную коллекцию etalonMap и тестовую коллекцию testMap
            assertIterableEquals(etalonMap.keySet(), testMap.keySet());
            assertIterableEquals(etalonMap.values(), testMap.values());
        }
    }

    @Test
    @DisplayName("Тест метода findDuplicate()")
    void findDuplicate() {
        // наполняем тестовую карту
        Map<String, List<Animal>> etalonMap = new HashMap<>();
        List<Animal> list = new ArrayList<>();
        list.add(new Cat("Daisy", 924.27, "Tricky",
                LocalDate.of(2017, 2, 13), "Murmuring"));
        etalonMap.put("Cat", list);
        list = new ArrayList<>();
        list.add(new Dog("Orange", 569.03, "Active",
                LocalDate.of(2004, 12, 17), "Lullaby"));
        list.add(new Dog("Ariana", 118.94, "Compliant",
                LocalDate.of(2004, 7, 1), "Golden"));
        etalonMap.put("Dog", list);
        list = new ArrayList<>();
        list.add(new Shark("Stalker", 413.23, "Treacherous",
                LocalDate.of(2014, 7, 17), "Halcyon"));
        list.add(new Shark("Kenobi", 227.63, "Active",
                LocalDate.of(2006, 5, 24), "Thrush"));
        etalonMap.put("Shark", list);
        list = new ArrayList<>();
        list.add(new Wolf("Aegenwulf", 303.65, "Capricious",
                LocalDate.of(2004, 1, 31), "Marigold"));
        list.add(new Wolf("Shaw", 911.91, "Compliant",
                LocalDate.of(2003, 6, 29), "Halcyon"));
        etalonMap.put("Wolf", list);

        //вызываем AnimalRepositoryImpl.findDuplicate() и получаем в testMap данные для теста
        Map<String, List<Animal>> testMap = animalRepository.findDuplicate();

        // проверяем на идеинтичность эталонную коллекцию etalonMap и тестовую коллекцию testMap
        assertIterableEquals(etalonMap.keySet(), testMap.keySet());
        assertIterableEquals(etalonMap.values(), testMap.values());
    }

    @Test
    @DisplayName("Тест метода findOldAndExpensive()")
    void findOldAndExpensive() {
        // создаём эталонную коллекцию
        List<Animal> etalonList = new ArrayList<>();
        etalonList.add(new Wolf("Shaw", 911.91, "Compliant", LocalDate.of(2003, 6, 29), "Halcyon"));
        etalonList.add(new Dog("Orange", 569.03, "Active", LocalDate.of(2004, 12, 17), "Lullaby"));
        etalonList.add(new Cat("Daisy", 924.27, "Tricky", LocalDate.of(2017, 2, 13), "Murmuring"));

        //вызываем AnimalRepositoryImpl.findOldAndExpensive() и получаем в testMap данные для теста
        List<Animal> testList = animalRepository.findOldAndExpensive();

        // проверяем на идеинтичность эталонную коллекцию etalonMap и тестовую коллекцию testMap
        assertIterableEquals(etalonList, testList);
    }

    @Test
    @DisplayName("Тест метода findMinConstAnimals()")
    void findMinConstAnimals() {
        // создаём эталонную коллекцию
        List<String> etalonList = Stream.of("Aegenwulf", "Ariana", "Kenobi").toList();

        //вызываем AnimalRepositoryImpl.findOldAndExpensive() и получаем в testMap данные для теста
        List<String> testList = animalRepository.findMinConstAnimals();

        // проверяем на идеинтичность эталонную коллекцию etalonMap и тестовую коллекцию testMap
        assertIterableEquals(etalonList, testList);
    }
}