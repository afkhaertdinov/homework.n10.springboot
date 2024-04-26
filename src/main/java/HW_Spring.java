import DTO.Animal;
import DTO.Cat;
import Service.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HW_Spring {

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(InitAnimal.class);
//        InitAnimal contextBean = context.getBean(InitAnimal.class);
//        System.out.println(contextBean.getName());

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Animal cat = context.getBean(Cat.class);
        System.out.println();
        System.out.println(cat);


//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//
//        Animal animal = context.getBean("cat", Cat.class);
//        System.out.println("animal: " + animal);
//        Animal animal2 = context.getBean("cat", Cat.class);
//        Animal animal3 = context.getBean("cat", Cat.class);
//        System.out.println("animal2: " + animal2);
//        System.out.println("animal3: " + animal3);
//        System.out.println(animal2.equals(animal3));






        context.close();




    }
}
