//package io.spring.springbatch.config.jdbc;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.context.annotation.Bean;
//
//@Slf4j
//public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {
//
//    public static final int ADD_NEW_AGE = 10;
//
//    @Override
//    public Customer process(Customer customer) throws Exception {
//        System.out.println("=======================");
//        System.out.println(customer);
//        System.out.println("=======================");
//        int newAge = customer.getAge() + ADD_NEW_AGE;
//        final Customer transformedCustomer = new Customer(customer.getId(), customer.getName(), newAge);
//
//        log.info("Change customer.age(" + customer.getAge() + ") to " + newAge);
//        return transformedCustomer;
//    }
//}
