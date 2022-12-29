package br.com.xbrain.sales.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import br.com.xbrain.sales.api.model.Sale;
import br.com.xbrain.sales.api.services.sales.RegisterSalesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/sales")
public class SaleController {

  private final RegisterSalesUseCase registerSalesUseCase;

  @GetMapping
  public ResponseEntity<List<Sale>> index() {
    return ResponseEntity.ok(new ArrayList<>());
  }

  @PostMapping
  public ResponseEntity<Sale> create(@Valid @RequestBody Sale sale) {
    return ResponseEntity.ok(registerSalesUseCase.registerSale(sale));
  }
}
