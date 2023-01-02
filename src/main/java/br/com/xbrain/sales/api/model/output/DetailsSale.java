package br.com.xbrain.sales.api.model.output;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DetailsSale {

    transient private LocalDate date;
    transient private Long quantity ;
    transient private Long sum;
    transient private Long average;

    public void init(LocalDate localDate) {
        this.date = localDate;
        this.quantity = 0L;
        this.sum = 0L;
        this.average = 0L;
    }

    public void calculateAverage() {
        this.average = (this.quantity != 0) ? this.sum / this.quantity : 0L;
    }
}
