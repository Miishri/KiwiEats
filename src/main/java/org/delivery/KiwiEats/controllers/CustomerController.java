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
}
