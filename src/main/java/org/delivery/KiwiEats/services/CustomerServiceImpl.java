package org.delivery.KiwiEats.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.KiwiEats.mapper.CustomerMapper;
import org.delivery.KiwiEats.models.CustomerDTO;
import org.delivery.KiwiEats.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Optional<CustomerDTO> getCustomerByUUID(Long uuid) {
        log.debug("SERVICE - Get Customer by UUID - Customer UUID: " + uuid + " - SERVICE");
        return Optional.ofNullable(customerMapper.customerToCustomerDTO(customerRepository.findById(uuid).orElse(null)));
    }

    @Override
    public Optional<CustomerDTO> getCustomerByEmail(String emailId) {
        log.debug("SERVICE - Get Customer by email - Customer email: " + emailId + " - SERVICE");
        return Optional.ofNullable(customerMapper.customerToCustomerDTO(customerRepository.findCustomerByEmail(emailId)));
    }

    @Override
    public Optional<CustomerDTO> getCustomerByUsername(String username) {
        log.debug("SERVICE - Get Customer by username - Customer username: " + username + " - SERVICE");
        return Optional.ofNullable(customerMapper.customerToCustomerDTO(customerRepository.findCustomerByUsername(username)));
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        log.debug("SERVICE--Add new Customer from controller--SERVICE");
        return customerMapper.customerToCustomerDTO(customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO)));
    }

    @Override
    public Optional<CustomerDTO> modifyCustomer(Long uuid, CustomerDTO customerDTO) {
        log.debug("SERVICE - Update customer by existing customer and UUID: " + uuid + " - SERVICE");

        AtomicReference<Optional<CustomerDTO>> customerReference = new AtomicReference<>();

        customerRepository
                .findById(uuid)
                .ifPresentOrElse(
                        customerFound -> {
                            customerFound.setUser(customerDTO.getUser());
                            customerFound.setCart(customerDTO.getCart());

                            customerReference.set(Optional.of(customerMapper.customerToCustomerDTO(customerRepository.save(customerFound))));
                        },
                        () -> {
                            customerReference.set(Optional.empty());
                        });

        return customerReference.get();
    }

    @Override
    public Boolean deleteCustomer(Long uuid) {
        if (customerRepository.existsById(uuid)) {
            customerRepository.deleteById(uuid);
            log.debug("--SERVICE-- Customer with UUID: " + uuid + " was deleted--");
            return true;
        }
        return false;
    }
}
