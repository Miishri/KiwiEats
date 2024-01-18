package org.delivery.KiwiEats.controllers;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.KiwiEats.exceptions.NotFoundException;
import org.delivery.KiwiEats.models.SellerDTO;
import org.delivery.KiwiEats.services.SellerService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SellerController {
    public static final String SELLER_PATH = "/kiwi/seller";
    public static final String SELLER_PATH_ID = SELLER_PATH + "/{sellerId}";

    private final SellerService sellerService;
    private final SellerModelAssembler sellerModelAssembler;

    @PostMapping(SELLER_PATH)
    public ResponseEntity<?> registerSeller(@RequestBody SellerDTO sellerDTO) {
        SellerDTO createdSellerDTO = sellerService.createSeller(sellerDTO);

        EntityModel<SellerDTO> entityModel = sellerModelAssembler.toModel(sellerDTO);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                .toUri()).body(entityModel);
    }

    @GetMapping(SELLER_PATH_ID)
    public @NonNull EntityModel<SellerDTO> getSellerById(@PathVariable UUID sellerId) {
        log.debug("CONTROLLER - Get seller by ID - Seller ID: " + sellerId + " - CONTROLLER");

        SellerDTO sellerDTO = sellerService.getSellerById(sellerId).orElseThrow(NotFoundException::new);

        return sellerModelAssembler.toModel(sellerDTO);
    }

    @GetMapping(SELLER_PATH)
    public CollectionModel<EntityModel<SellerDTO>> getAllSellers() {

        List<EntityModel<SellerDTO>> sellers = sellerService.getAllSellers().stream().map(sellerModelAssembler::toModel).toList();

        return CollectionModel.of(sellers, linkTo(methodOn(SellerController.class).getAllSellers()).withSelfRel());
    }

    @PutMapping(SELLER_PATH_ID)
    public ResponseEntity<?> updateSellerById(@PathVariable UUID sellerId, @RequestBody SellerDTO sellerDTO) {

        Optional<SellerDTO> updatedProduct = sellerService.updateSeller(sellerId, sellerDTO);

        if (updatedProduct.isEmpty()) throw new NotFoundException("Seller was not found");

        EntityModel<SellerDTO> entityModel = sellerModelAssembler.toModel(updatedProduct.get());

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                .toUri()).body(entityModel);
    }

    @DeleteMapping(SELLER_PATH_ID)
    public ResponseEntity<?> deleteSellerById(@PathVariable UUID sellerId) {
        Boolean isDeleted = sellerService.deleteSellerById(sellerId);
        if (!isDeleted) throw new NotFoundException("Seller could not be deleted");
        return ResponseEntity.noContent().build();
    }
}
