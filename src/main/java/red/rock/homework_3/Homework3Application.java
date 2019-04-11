package red.rock.homework_3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("red.rock.homework_3.Mapper")
@SpringBootApplication
public class Homework3Application {

    public static void main(String[] args) {

        SpringApplication.run(Homework3Application.class, args);
    }

}
