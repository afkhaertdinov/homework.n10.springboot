package Service;

import java.time.LocalDate;
import java.time.ZoneId;

public final class ConstsClass {
    public static final int CONSTNUM = 9; // Количество значений для проверки метода CreateAnimalServiceImpl()
    public static final int CONSTAGE = 16; // Возраст, животных старше которого необходимо найти методе AnimalRepositoryImpl.findOlderAnimal()
    public static final long STARTDATE = LocalDate.of(2000,1,1).atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant().getEpochSecond();
    public static final long ENDDATE = LocalDate.now().atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant().getEpochSecond();
}
