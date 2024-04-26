package Service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Setter @Getter
@Configuration
//@ConfigurationProperties(prefix = "init")
@ConfigurationPropertiesScan
public class InitConfig {
//    private final AnimalGenService genService;
//
//    public InitConfig(AnimalGenService genService) {
//        this.genService = genService;
//    }
//
//    public String getName() {
//        return genService.generateName();
//    }
//    public Double getCost() {
//        return genService.generateCost();
//    }

//
//    public String getName() {
//        String name = "no Name";
//        SecureRandom randomNum = new SecureRandom();
//        if ((names != null)&&(!names.isEmpty())) {
//            int num = randomNum.nextInt(names.size());
//            name = names.get(num);
//        }
//        return name;
//    }
//    public String getSecret() {
//        String secret = "no Secret";
//        SecureRandom randomNum = new SecureRandom();
//        if ((secrets != null)&&(!secrets.isEmpty())) {
//            int num = randomNum.nextInt(secrets.size());
//            secret = secrets.get(num);
//        }
//        return secret;
//    }
//    public String getCharacter() {
//        String character = "no Character";
//        SecureRandom randomNum = new SecureRandom();
//        if ((characters != null)&&(!characters.isEmpty())) {
//            int num = randomNum.nextInt(characters.size());
//            character = characters.get(num);
//        }
//        return character;
//    }

}
