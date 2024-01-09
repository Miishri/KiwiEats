package org.delivery.KiwiEats.controllers;

import jdk.incubator.foreign.CLinker;
import lombok.NonNull;
import org.delivery.KiwiEats.entities.Seller;
import org.delivery.KiwiEats.models.SellerDTO;
import org.hibernate.boot.jaxb.hbm.internal.RepresentationModeConverter;
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
        return EntityModel.of(sellerDTO,
                linkTo(methodOn(SellerController.class)));
    }
}
