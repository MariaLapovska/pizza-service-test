package com.projects.pizzaservice;

import com.projects.pizzaservice.domain.*;
import com.projects.pizzaservice.repository.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Mariia Lapovska
 */
public class AppRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("repoContext.xml");

        AddressRepository addressRepository = context.getBean(AddressRepository.class);
        CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
        MemberCardRepository memberCardRepository = context.getBean(MemberCardRepository.class);
        PizzaRepository pizzaRepository = context.getBean(PizzaRepository.class);
        OrderRepository orderRepository = context.getBean(OrderRepository.class);

        //Address address = new Address(null, "Ukraine", "Kiev", "Fru", 12);
        //address = addressRepository.add(address);

        MemberCard memberCard = new MemberCard(null, LocalDate.now(), BigDecimal.TEN);

        Customer customer = new Customer(null, "Vav", memberCard);
        //customer = customerRepository.add(customer);

        /*Pizza pizza = new Pizza(null, "Hawaii", new BigDecimal(100), Pizza.Type.VEGETARIAN);
        pizza = pizzaRepository.add(pizza);

        Order order = new Order(null, customer, address);
        order.addPizza(pizza);
        order.addPizza(pizza);
        order.addPizza(pizza);
*/
        System.out.println(customerRepository.add(customer));
    }
}