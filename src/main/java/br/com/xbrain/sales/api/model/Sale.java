package br.com.xbrain.sales.api.model;

import java.time.LocalDateTime;

public class Sale {
  private Long id;
  private Long value;
  private Seller seller;
  private LocalDateTime createdAt;
}
