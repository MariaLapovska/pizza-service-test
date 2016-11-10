package com.projects.pizzaservice;

import com.projects.pizzaservice.config.RepoConfig;
import com.projects.pizzaservice.config.ServiceConfig;
import com.projects.pizzaservice.domain.*;
import com.projects.pizzaservice.repository.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Mariia Lapovska
 */
public class AppRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(RepoConfig.class, ServiceConfig.class);


    }
}