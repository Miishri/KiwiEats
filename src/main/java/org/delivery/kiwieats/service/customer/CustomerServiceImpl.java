package org.delivery.kiwieats.service.customer;

import lombok.RequiredArgsConstructor;
import org.delivery.kiwieats.entities.customer.Customer;
import org.delivery.kiwieats.entities.UserDetails;
import org.delivery.kiwieats.repositories.CustomerRepository;
import org.delivery.kiwieats.repositories.UserDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserDetailsRepository detailsRepository;

    @Override
    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(UUID customerId) {
        return Optional.of(customerRepository.findById(customerId))
                .orElse(null);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> updateCustomerById(UUID customerId, Customer customer) {

        AtomicReference<Optional<Customer>> atomicReference = new AtomicReference<>();

        customerRepository.findById(customerId).ifPresentOrElse(customerFound -> {

            customerFound.setOrders(customer.getOrders());

            UserDetails customerFoundDetails = detailsRepository.findByUserId(customerFound.getId());
            UserDetails customerDetails = detailsRepository.findByUserId(customer.getId());

            customerFoundDetails.setCity(customerDetails.getCity());
            customerFoundDetails.setEmail(customerDetails.getCity());
            customerFoundDetails.setCareOf(customerDetails.getCareOf());
            customerFoundDetails.setPhone(customerDetails.getPhone());
            customerFoundDetails.setPassword(customerDetails.getPassword());
            customerFoundDetails.setStreet(customerDetails.getStreet());
            customerFoundDetails.setFirstName(customerDetails.getFirstName());
            customerFoundDetails.setLastName(customerDetails.getLastName());
            customerFoundDetails.setPostCode(customerDetails.getPostCode());
            customerFoundDetails.setCountry(customerDetails.getCountry());
            customerFoundDetails.setRegisteredDate(customerDetails.getRegisteredDate());

            detailsRepository.save(customerFoundDetails);

            atomicReference.set(Optional.of(customerRepository.save(customerFound)));

        }, () -> {
            atomicReference.set(Optional.empty());
        });


        return atomicReference.get();
    }

    @Override
    public Boolean deleteCustomerById(UUID customerId) {
            if (customerRepository.existsById(customerId)) {
                UserDetails customerDetails = detailsRepository.findByUserId(customerId);
                detailsRepository.delete(customerDetails);
                customerRepository.deleteById(customerId);
                return true;
            }
        return false;
    }
}
