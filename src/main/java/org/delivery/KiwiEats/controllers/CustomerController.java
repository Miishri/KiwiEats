package org.delivery.KiwiEats.controllers;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.KiwiEats.exception.NotFoundException;
import org.delivery.KiwiEats.models.CustomerDTO;
import org.delivery.KiwiEats.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController {

  public static final String CUSTOMER_PATH = "/kiwi/customer";
  public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

  private final CustomerService customerService;

  @PostMapping(CUSTOMER_PATH)
  public CustomerDTO addCustomer(@RequestBody CustomerDTO customerDTO) {
    return customerService.addCustomer(customerDTO);
  }

  @GetMapping(CUSTOMER_PATH_ID)
  public CustomerDTO getCustomerByUUID(@PathVariable("customerId") Long customerId) {
    log.debug("CONTROLLER - Get Customer by ID - Customer ID: " + customerId + " - CONTROLLER");

    return customerService.getCustomerById(customerId).orElseThrow(NotFoundException::new);
  }

  @PutMapping(CUSTOMER_PATH_ID)
  public CustomerDTO modifyCustomer(
      @PathVariable("customerId") Long customerId, @RequestBody CustomerDTO customerDTO) {
    Optional<CustomerDTO> updatedCustomer = customerService.modifyCustomer(customerId, customerDTO);

    if (updatedCustomer.isEmpty()) throw new NotFoundException();

    return updatedCustomer.get();
  }

  @DeleteMapping(CUSTOMER_PATH_ID)
  public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") Long customerId) {
    Boolean isDeleted = customerService.deleteCustomer(customerId);
    if (!isDeleted) throw new NotFoundException();
    return ResponseEntity.noContent().build();
  }
}
