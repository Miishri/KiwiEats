package org.delivery.KiwiEats.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.KiwiEats.entities.roles.User;
import org.delivery.KiwiEats.mapper.CustomerMapper;
import org.delivery.KiwiEats.models.CustomerDTO;
import org.delivery.KiwiEats.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Optional<CustomerDTO> getCustomerById(Long customerId) {
    log.debug("SERVICE - Get Customer by UUID - Customer ID: " + customerId + " - SERVICE");
    return Optional.ofNullable(
        customerMapper.customerToCustomerDTO(customerRepository.findById(customerId).orElse(null)));
  }

  @Override
  public CustomerDTO addCustomer(CustomerDTO customerDTO) {
    log.debug("SERVICE--Add new Customer from controller--SERVICE");

    User user = customerDTO.getUser();
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    return customerMapper.customerToCustomerDTO(
        customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO)));
  }

  @Override
  public Optional<CustomerDTO> modifyCustomer(Long customerId, CustomerDTO customerDTO) {
    log.debug("SERVICE - Update customer by existing customer and ID: " + customerId + " - SERVICE");

    AtomicReference<Optional<CustomerDTO>> customerReference = new AtomicReference<>();

    customerRepository
        .findById(customerId)
        .ifPresentOrElse(
            customerFound -> {
              User user = customerDTO.getUser();
              user.setPassword(passwordEncoder.encode(user.getPassword()));

              customerFound.setUser(user);
              customerFound.setCart(customerDTO.getCart());

              customerReference.set(
                  Optional.of(
                      customerMapper.customerToCustomerDTO(
                          customerRepository.save(customerFound))));
            },
            () -> {
              customerReference.set(Optional.empty());
            });

    return customerReference.get();
  }

  @Override
  public Boolean deleteCustomer(Long customerId) {
    if (customerRepository.existsById(customerId)) {
      customerRepository.deleteById(customerId);
      log.debug("--SERVICE-- Customer with UUID: " + customerId + " was deleted--");
      return true;
    }
    return false;
  }
}
