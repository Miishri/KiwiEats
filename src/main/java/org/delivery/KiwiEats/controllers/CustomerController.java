package org.delivery.KiwiEats.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.KiwiEats.exceptions.NotFoundException;
import org.delivery.KiwiEats.models.CustomerDTO;
import org.delivery.KiwiEats.services.CustomerService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController {

    public static final String CUSTOMER_PATH = "/kiwi/customer";
    public static final String CUSTOMER_PATH_UUID = CUSTOMER_PATH + "/{uuid}";

    private final CustomerService customerService;
    private final CustomerModelAssembler customerModelAssembler;

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomerDTO = customerService.addCustomer(customerDTO);

        EntityModel<CustomerDTO> entityModel = customerModelAssembler.toModel(createdCustomerDTO);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                        .toUri()).body(entityModel);
    }

    @GetMapping(CUSTOMER_PATH_UUID)
    public EntityModel<CustomerDTO> getCustomerByUUID(@PathVariable("uuid") Long uuid) {
        log.debug("CONTROLLER - Get Customer by UUID - Customer UUID: " + uuid + " - CONTROLLER");

        CustomerDTO customerDTO = customerService.getCustomerByUUID(uuid).orElseThrow(NotFoundException::new);

        return customerModelAssembler.toModel(customerDTO);
    }

    @GetMapping(CUSTOMER_PATH + "/{email}")
    public EntityModel<CustomerDTO> getCustomerByEmail(@PathVariable("email") String email) {
        log.debug("CONTROLLER - Get Customer by UUID - Customer Email: " + email + " - CONTROLLER");

        CustomerDTO customerDTO = customerService.getCustomerByEmail(email).orElseThrow(NotFoundException::new);

        return customerModelAssembler.toModel(customerDTO);
    }

    @GetMapping(CUSTOMER_PATH + "/{username}")
    public EntityModel<CustomerDTO> getCustomerByUsername(@PathVariable("username") String username) {
        log.debug("CONTROLLER - Get Customer by UUID - Customer Username: " + username + " - CONTROLLER");

        CustomerDTO customerDTO = customerService.getCustomerByUsername(username).orElseThrow(NotFoundException::new);

        return customerModelAssembler.toModel(customerDTO);
    }

    @PutMapping(CUSTOMER_PATH_UUID)
    public ResponseEntity<?> modifyCustomer(@PathVariable("uuid") Long uuid, @RequestBody CustomerDTO customerDTO) {
        Optional<CustomerDTO> updatedCustomer = customerService.modifyCustomer(uuid, customerDTO);

        if (updatedCustomer.isEmpty()) throw new NotFoundException();

        EntityModel<CustomerDTO> entityModel = customerModelAssembler.toModel(updatedCustomer.get());

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                        .toUri()).body(entityModel);
    }

    @DeleteMapping(CUSTOMER_PATH_UUID)
    public ResponseEntity<?> deleteCustomer(@PathVariable("uuid") Long uuid) {
        Boolean isDeleted = customerService.deleteCustomer(uuid);
        if (!isDeleted) throw new NotFoundException();
        return ResponseEntity.noContent().build();
    }
}
