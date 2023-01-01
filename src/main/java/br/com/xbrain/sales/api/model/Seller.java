package br.com.xbrain.sales.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@RequiredArgsConstructor
public class Seller {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 128)
    private String name;

    public Seller(String name) {
        this.name = name;
    }

    public boolean isValid() {
        if (getName().isBlank() || getName().length() < 3) {
            return false;
        }
        return true;
    }
}

