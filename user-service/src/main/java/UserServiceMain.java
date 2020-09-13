import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/18 22:07
 * @since JDK 1.8
 */
@SpringBootApplication(scanBasePackages = "com")
public class UserServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceMain.class,args);
    }
}