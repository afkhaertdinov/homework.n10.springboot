package Service;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;


@SuppressWarnings("unused")
@Configuration
@ComponentScan("DTO")
@SpringBootConfiguration
@EnableAutoConfiguration
public class SpringConfig {

    @Bean
    @ConditionalOnMissingBean
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalService();
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnBean(CreateAnimalService.class)
    public AnimalRepositoryImpl animalRepositoryImpl(int quantity) {
        return new AnimalRepositoryImpl(createAnimalService(), quantity);
    }

}
