package io.spring.springbatch.config.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {
    public static final int ADD_NEW_AGE = 10;

    @Override
    public Customer process(Customer customer) throws Exception {
        int newAge = customer.getAge() + ADD_NEW_AGE;
        final Customer transformedCustomer = Customer.builder()
                .id(customer.getId())
                .name(customer.getName())
                .age(newAge)
                .build();
        log.info("Change customer.age(" + customer.getAge() + ") to " + newAge);
        return transformedCustomer;
    }
}
