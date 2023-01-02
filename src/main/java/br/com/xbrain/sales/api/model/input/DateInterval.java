package br.com.xbrain.sales.api.model.input;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateInterval {

    LocalDateTime startDate;
    LocalDateTime endDate;
}
