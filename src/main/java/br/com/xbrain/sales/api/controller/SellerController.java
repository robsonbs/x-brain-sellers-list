package br.com.xbrain.sales.api.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.xbrain.sales.api.model.Seller;
import br.com.xbrain.sales.api.model.input.DateInterval;
import br.com.xbrain.sales.api.repository.SellerRepository;
import br.com.xbrain.sales.api.services.sellers.ListSellersUseCase;
import br.com.xbrain.sales.api.services.sellers.RegisterSellerUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/sellers")
public class SellerController {

    private final SellerRepository sellerRepository;
    private final RegisterSellerUseCase registerSellerUseCase;
    private final ListSellersUseCase listSellersUseCase;

    @PostMapping("/report")
    public ResponseEntity<List<br.com.xbrain.sales.api.model.output.Seller>> index(@RequestBody @NotNull DateInterval dates) {
        return ResponseEntity.ok(listSellersUseCase.getList(dates));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Seller adicionar(@Valid @RequestBody Seller seller) {

        return registerSellerUseCase.registerSeller(seller);
    }
}
