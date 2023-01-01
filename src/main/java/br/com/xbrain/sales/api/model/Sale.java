package br.com.xbrain.sales.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.xbrain.sales.api.exceptions.BusinessException;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Sale {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Min(1)
    @Column(name = "value_in_cents")
    private Long value;
    @NotNull
    @ManyToOne
    private Seller seller;
    private LocalDateTime createdAt;

    public boolean isValid() {
        if (seller == null || !seller.isValid()) {
            throw new BusinessException("Seller Not Valid!");
        }
        if (value <= 0) {
            throw new BusinessException("Value must be greater than zero!");
        }
        if (createdAt == null) {
            throw new BusinessException("Date must be valid!");
        }
        return true;
    }
}
