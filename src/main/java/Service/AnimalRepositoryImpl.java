package Service;

import DTO.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AnimalRepositoryImpl implements AnimalRepository {
    /**
     * При помощи Stream API найти всех животных, которые родились в високосный год.
     * <br/><li> Возможны случаи когда во входном массиве окажется больше одного животного с одинаковым именем и типом и рождённым в високосный год. По указанию преподавателя игнорируем дубли.</li>
     * @param animals На вход подается массив животных {@code List<Animal>}.
     * @return Метод должен возвращать {@code Map<String,LocalDate>}, которая содержит в качестве ключа тип
     * животного + имя, а в значении дату рождения. Пример ключа: Cat Barsik, Dog Spot (русский язык
     * допускается в именах).
     */
    @Override
    public Map<String, LocalDate> findLeapYearNames(@NonNull List<Animal> animals) {
        Set<String> items = new HashSet<>(); //По указанию Макса удаляю возможные дубли, для этого использую Set.add()
        return animals.stream().filter(animal -> animal.getBirthDate().isLeapYear()).
                filter(e -> items.add(e.getBreed() + " " + e.getName())).collect(Collectors.
                        toMap(animal -> animal.getBreed() + " " + animal.getName(), Animal::getBirthDate));
    }


    /**
     * При помощи Stream API найти всех животных, возраст которых старше N лет, где N аргумент метода.<br/>
     * Записывать результат в json файл YOUR_PROJECT/recources/results/findOlderAnimals.json
     * При этом строка secretInformation при записи в файл должна быть сохранена в формате Base64.
     * @param animals На вход подается массив животных {@code List<Animal>}.
     * @param age Возраст, животных старше которого необходимо вернуть.
     * @return Метод должен возвращать {@code Map<Animal,Integer>}, которая содержит в качестве ключа животное, а в качестве значения возраст.
     * Если при заданном значении поиска (передаваемый аргумент возраста) не было найдено ни одного животного, то вернуть нужно самого старшего из возможных.
     */
    @Override
    public Map<Animal, Integer> findOlderAnimal(@NonNull List<Animal> animals, int age) throws IOException {
        Set<Map.Entry<Animal, Integer>> entry = animals.stream().collect(Collectors.toMap(animal -> animal,
                animal ->Period.between(animal.getBirthDate(), LocalDate.now()).getYears())).entrySet();

        Map<Animal, Integer> animalIntegerMap = entry.stream().filter(e -> e.getValue() > age).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (animalIntegerMap.isEmpty()) // Если нет животных старше <int age>, то возвращаем самого старшего
            animalIntegerMap = entry.stream().max(Map.Entry.comparingByValue()).stream().
                    collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

//                    .stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Записываем результат в findOlderAnimals.json, согласно домашнего задания №9
        List<EntryAnimalInteger> mops = // переводим Map в List<Entry> для последующей Сериализации
                animalIntegerMap.entrySet().stream().
                        map(e -> new EntryAnimalInteger(e.getKey(), e.getValue())).collect(Collectors.toList());

        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        mapper.writeValue(new File("src/main/java/HomeWork/Recources/Results/findOlderAnimals.json"), mops);
        return animalIntegerMap;
    }


    /**
     * Метод выводит на экран дубликаты животных.
     * @param animals На вход подается массив животных {@code List<Animal>}.
     * @return Возвращает {@code Map<String,List<Animal>>}, которая содержит в качестве ключа строку с типом животного, а в значении List - список дубликатов.
     */
    @Override
    public Map<String, List<Animal>> findDuplicate(@NonNull List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::getBreed,Collectors.mapping(animal ->animal,Collectors.toList())));
    }


    /**
     * Метод ищет при помощи Stream API средний возраст всех животных. <br/>Результат выводит на экран.
     * @param animals На вход подается массив животных {@code List<Animal>}.
     */
    @Override
    public void findAverageAge(@NonNull List<Animal> animals){
        System.out.println("Средний возраст всех животных: " + animals.stream().map(animal ->Period.between(animal.getBirthDate(), LocalDate.now()).getYears()).flatMapToInt(IntStream::of).summaryStatistics().getAverage());
    }


    /**
     * Метод при помощи Stream API ищет животных, возраст которых больше 5 лет и стоимость которых больше средней стоимости всех животных.
     * @param animals На вход подается массив животных {@code List<Animal>}.
     * @return Возвращает {@code List<Animal>} отсортированный по дате рождения (по возрастанию) список.
     */
    @Override
    public List<Animal> findOldAndExpensive(@NonNull List<Animal> animals){
        double aDouble = animals.stream().mapToDouble(Animal::getCost).summaryStatistics().getAverage();
        System.out.printf("Список животных, возраст которых больше 5 лет и стоимость которых больше средней стоимости всех животных = %02.02f%n",aDouble);
        return animals.stream().filter(animal -> (5 < Period.between(animal.getBirthDate(), LocalDate.now()).getYears())&&(aDouble < animal.getCost())).sorted(Comparator.comparing(Animal::getBirthDate)).collect(Collectors.toList());
    }


    /**
     * Метод при помощи Stream API ищет 3 животных с самой низкой ценой.
     * @param animals На вход подается массив животных {@code List<Animal>}.
     * @return Возвращает {@code List<String>} список имен, отсортированный в обратном алфавитном порядке.
     */
    @Override
    public List<String> findMinConstAnimals(@NonNull List<Animal> animals){
        return animals.stream().sorted(Comparator.comparingDouble(Animal::getCost)).limit(3).map(Animal::getName).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

}
