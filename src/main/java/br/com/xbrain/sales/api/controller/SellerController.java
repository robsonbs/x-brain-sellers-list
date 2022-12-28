package br.com.xbrain.sales.api.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.xbrain.sales.api.model.Seller;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/sellers")
public class SellerController {

  @GetMapping
  public ResponseEntity<List<Seller>> index() {
    return ResponseEntity.ok(new ArrayList<>());
  }
}
