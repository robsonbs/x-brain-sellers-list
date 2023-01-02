package br.com.xbrain.sales.api.model.output;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.xbrain.sales.api.model.input.DateInterval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

    private String name;
    private Long id;
    transient private Long totalQuantity;
    transient private Long totalValue;
    private DateInterval dateInterval;
    private List<DetailsSale> sales;

    public void init(br.com.xbrain.sales.api.model.@NotNull Seller seller,
                     @NotNull DateInterval dateInterval) {
        this.name = seller.getName();
        this.id = seller.getId();
        this.dateInterval = dateInterval;
        this.sales = new ArrayList<>(1);
        this.totalQuantity = 0L;
        this.totalValue = 0L;
    }
}
