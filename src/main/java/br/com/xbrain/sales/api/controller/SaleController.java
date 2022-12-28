package br.com.xbrain.sales.api.controller;

import br.com.xbrain.sales.api.model.Sale;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/sales")
public class SaleController {

  @GetMapping
  public ResponseEntity<List<Sale>> index() {
    return ResponseEntity.ok(new ArrayList<>());
  }
}
