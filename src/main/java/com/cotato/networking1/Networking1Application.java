package com.cotato.networking1;

import lombok.RequiredArgsConstructor;
import org.apache.poi.sl.usermodel.ObjectMetaData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@RequiredArgsConstructor
public class Networking1Application {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis(); // 시작 시간 기록
        SpringApplication.run(Networking1Application.class, args);
        long endTime = System.currentTimeMillis(); // 종료 시간 기록
        long executionTime = endTime - startTime; // 실행 시간 계산

        // 실행 시간 출력
        System.out.println("스프링 부트 애플리케이션 실행 시간: " + executionTime + "ms");

    }
}
