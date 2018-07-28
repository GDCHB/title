package com.chb.title;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan("com.chb.title")
public class TitleApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(TitleApplication.class, args);
//        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler //www.jb51.net");

    }
}
