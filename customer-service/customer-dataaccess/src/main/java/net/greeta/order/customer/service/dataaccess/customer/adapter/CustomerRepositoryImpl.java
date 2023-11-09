package net.greeta.order.customer.service.dataaccess.customer.adapter;

import net.greeta.order.customer.service.dataaccess.customer.mapper.CustomerDataAccessMapper;
import net.greeta.order.customer.service.dataaccess.customer.repository.CustomerJpaRepository;
import net.greeta.order.customer.service.domain.entity.Customer;
import net.greeta.order.customer.service.domain.ports.output.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    private final CustomerDataAccessMapper customerDataAccessMapper;

    public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository,
                                  CustomerDataAccessMapper customerDataAccessMapper) {
        this.customerJpaRepository = customerJpaRepository;
        this.customerDataAccessMapper = customerDataAccessMapper;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDataAccessMapper.customerEntityToCustomer(
                customerJpaRepository.save(customerDataAccessMapper.customerToCustomerEntity(customer)));
    }
}
