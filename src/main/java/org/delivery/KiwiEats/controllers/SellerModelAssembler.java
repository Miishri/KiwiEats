package org.delivery.KiwiEats.controllers;

import lombok.NonNull;
import org.delivery.KiwiEats.models.SellerDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SellerModelAssembler implements RepresentationModelAssembler<SellerDTO, EntityModel<SellerDTO>> {
    @Override
    @NonNull
    public EntityModel<SellerDTO> toModel(@NonNull SellerDTO sellerDTO) {
        return EntityModel.of(sellerDTO, linkTo(methodOn(SellerController.class).getSellerById(sellerDTO.getId())).withSelfRel());
    }
}
