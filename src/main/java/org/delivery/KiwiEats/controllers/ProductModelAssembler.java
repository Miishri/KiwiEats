package org.delivery.KiwiEats.controllers;

import org.delivery.KiwiEats.models.ProductDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<ProductDTO, EntityModel<ProductDTO>> {
    @Override
    @NonNull
    public EntityModel<ProductDTO> toModel(@NonNull ProductDTO productDTO) {
        return EntityModel.of(productDTO,
                linkTo(methodOn(ProductController.class).getProductById(productDTO.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).getAllProducts()).withRel("products"));
    }

}
