package br.com.xbrain.sales.api.model.input;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public class DateInterval {

    LocalDateTime startDate;
    LocalDateTime endDate;
}
