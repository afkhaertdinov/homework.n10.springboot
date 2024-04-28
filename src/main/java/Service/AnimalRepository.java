package Service;

import DTO.Animal;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalRepository {


    /**
     * При помощи цикла (любой по желанию) найти всех животных, которые родились в високосный год.
     * <br/><li> Возможны случаи когда во входном массиве окажется больше одного животного с одинаковым именем и типом и рождённым в високосный год. По указанию преподавателя игнорируем дубли.</li>
     * @return Метод должен возвращать {@code Map<String,LocalDate>}, которая содержит в качестве ключа тип
     * животного + имя,  а в значении дату рождения. Пример ключа: Cat Barsik, Dog Spot (русский язык
     * допускается в именах).
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * При помощи цикла for найти всех животных, возраст которых старше N лет, где N аргумент метода.
     * <br/><i> - данный кейс необходимо покрыть кейсами в тестах.</i>
     * @param animals На вход подается массив животных {@code List<Animal>}.
     * @param age Возраст, животных старше которого необходимо вернуть.
     * @return Метод должен возвращать {@code Map<Animal,Integer>}, которая содержит в качестве ключа животное, а в качестве значения возраст.
     * Если при заданном значении поиска (передаваемый аргумент возраста) не было найдено ни одного животного, то вернуть нужно самого старшего из возможных.
     */
    Map<Animal, Integer> findOlderAnimal(@NonNull List<Animal> animals, int age) throws Exception;

    /**
     * Метод выводит на экран дубликаты животных.
     * @param animals На вход подается массив животных {@code List<Animal>}.
     * @return Возвращает {@code Map<String,List<Animal>>}, которая содержит в качестве ключа строку с типом животного, а в значении List - список дубликатов.
     */
    Map<String, List<Animal>> findDuplicate(@NonNull List<Animal> animals);

    /**
     * Метод ищет при помощи Stream API средний возраст всех животных. Результат выводит на экран.
     * @param animals На вход подается массив животных {@code List<Animal>}.
     */
    @SuppressWarnings("unused") // Оставлен для совместимости с предыдущими ДЗ
    void findAverageAge(@NonNull List<Animal> animals);

    /**
     * Метод при помощи Stream API ищет животных, возраст которых больше 5 лет и стоимость которых больше средней стоимости всех животных.
     * @param animals На вход подается массив животных {@code List<Animal>}.
     * @return Возвращает {@code List<Animal>} отсортированный по дате рождения (по возрастанию) список.
     */
    List<Animal> findOldAndExpensive(@NonNull List<Animal> animals);

    /**
     * Метод при помощи Stream API ищет 3 животных с самой низкой ценой.
     * @param animals На вход подается массив животных {@code List<Animal>}.
     * @return Возвращает {@code List<String>} список имен, отсортированный в обратном алфавитном порядке.
     */
    List<String> findMinConstAnimals(@NonNull List<Animal> animals);
}
