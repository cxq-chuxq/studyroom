package club.banyuan.studyroom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("club.banyuan.studyroom.mapper")
@SpringBootApplication
public class StudyroomApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyroomApplication.class, args);
    }
}
