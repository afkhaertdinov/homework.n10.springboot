package Service;

import DTO.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;


@Configuration
@ComponentScan("DTO")
@ConditionalOnBean(InitConfig.class)
public class SpringConfig {

//    @Bean
//    @ConditionalOnMissingBean
//    public InitAnimal initAnimal() {
//        return new InitAnimal();
//    }

    @Bean
    @ConditionalOnBean(InitAnimal.class)
    @Scope("prototype")
    public Cat cat(InitAnimal initAnimal ) {
        return new Cat(initAnimal);
    }

}
