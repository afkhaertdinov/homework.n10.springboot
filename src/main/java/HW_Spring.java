import Service.AnimalRepositoryImpl;
import Service.CreateAnimalService;
import Service.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class HW_Spring {

    public static void main(String[] args) {

        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class)) {

            AnimalRepositoryImpl animalRepository1 = (AnimalRepositoryImpl) context.
                    getBean("animalRepositoryImpl",context.getBean(CreateAnimalService.class),15);
            animalRepository1.findLeapYearNames().entrySet().forEach(System.out::println);

            System.out.println();

            AnimalRepositoryImpl animalRepository2 = (AnimalRepositoryImpl) context.
                    getBean("animalRepositoryImpl",context.getBean(CreateAnimalService.class),23);
            animalRepository2.findLeapYearNames().entrySet().forEach(System.out::println);


        }
    }
}
