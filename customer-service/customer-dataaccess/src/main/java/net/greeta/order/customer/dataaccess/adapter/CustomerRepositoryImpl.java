package net.greeta.order.customer.dataaccess.adapter;

import net.greeta.order.customer.dataaccess.mapper.CustomerDataAccessMapper;
import net.greeta.order.customer.dataaccess.repository.CustomerJpaRepository;
import net.greeta.order.customer.domain.ports.output.repository.CustomerRepository;
import net.greeta.order.customer.domain.entity.Customer;
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
