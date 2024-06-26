package Service;

import DTO.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalRepository {


    /**
     * <h3>При помощи цикла (любой по желанию) найти всех животных, которые родились в високосный год.</h3>
     * <br/><li> Возможны случаи когда во входном массиве окажется больше одного животного с одинаковым именем и типом и рождённым в високосный год. По указанию преподавателя игнорируем дубли.</li>
     * @return Метод должен возвращать {@code Map<String,LocalDate>}, которая содержит в качестве ключа тип
     * животного + имя,  а в значении дату рождения. Пример ключа: Cat Barsik, Dog Spot (русский язык
     * допускается в именах).
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * <h3>Метод ищет всех животных, возраст которых старше N лет</h3>где N аргумент метода.
     * <br/><i> - данный кейс необходимо покрыть кейсами в тестах.</i>
     * @param age Возраст, животных старше которого необходимо вернуть.
     * @return Метод должен возвращать {@code Map<Animal,Integer>}, которая содержит в качестве ключа животное, а в качестве значения возраст.
     * Если при заданном значении поиска (передаваемый аргумент возраста) не было найдено ни одного животного, то вернуть нужно самого старшего из возможных.
     */
    Map<Animal, Integer> findOlderAnimal(int age) throws Exception;

    /**
     * <h3>Метод группирует выводит на экран дубликаты животных.</h3>
     * @return Возвращает {@code Map<String,List<Animal>>}, которая содержит в качестве ключа строку с типом животного, а в значении List - список дубликатов.
     */
    Map<String, List<Animal>> findDuplicate();

    /**
     * <h3>Метод ищет при помощи Stream API средний возраст всех животных.</h3>
     * Результат выводит на экран.
     */
    @SuppressWarnings("unused") // Оставлен для совместимости с предыдущими ДЗ
    void findAverageAge();

    /**
     * <h3>Метод при помощи Stream API ищет животных</h3> возраст которых больше 5 лет и стоимость которых больше средней стоимости всех животных.
     * @return Возвращает {@code List<Animal>} отсортированный по дате рождения (по возрастанию) список.
     */
    List<Animal> findOldAndExpensive();

    /**
     * <h3>Метод при помощи Stream API ищет 3 животных с самой низкой ценой.</h3>
     * @return Возвращает {@code List<String>} список имен, отсортированный в обратном алфавитном порядке.
     */
    List<String> findMinConstAnimals();
}
