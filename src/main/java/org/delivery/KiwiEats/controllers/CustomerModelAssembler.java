package org.delivery.KiwiEats.controllers;

import org.delivery.KiwiEats.models.CustomerDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<CustomerDTO, EntityModel<CustomerDTO>> {

    @Override
    @NonNull
    public EntityModel<CustomerDTO> toModel(@NonNull CustomerDTO customerDTO) {
        return EntityModel.of(customerDTO,
                linkTo(methodOn(CustomerController.class).getCustomerByUUID(customerDTO.getCustomerId())).withSelfRel()
                );
    }
}
