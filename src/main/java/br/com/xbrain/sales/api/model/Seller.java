package br.com.xbrain.sales.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Seller {

  @EqualsAndHashCode.Include
  @Id
  private Long id;
  private String name;
}
