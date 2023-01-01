package br.com.xbrain.sales.api.service.sellers;

import java.time.LocalDateTime;

import br.com.xbrain.sales.api.model.input.DateInterval;
import br.com.xbrain.sales.api.services.sellers.ListSellersUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListSellersUseCaseTest {

    ListSellersUseCase listSellersUseCase;

    /* Retornar a lista de vendedores com os campos: nome, total de vendas do vendedor e média de
    vendas diária, conforme o período informado por parâmetro
     */
    @BeforeEach
    void initUseCase() {
        listSellersUseCase = new ListSellersUseCase();
    }

    @Test
    void testListOfSellersWithSale() {
        DateInterval dateInterval =
            DateInterval.builder().startDate(getDateStart()).endDate(getDateEnd()).build();
        Assertions.assertThat(listSellersUseCase.getList(dateInterval)).hasSize(0);
    }

    private LocalDateTime getDateStart() {
        return LocalDateTime.parse("2022-12-20T13:00:00");
    }

    private LocalDateTime getDateEnd() {
        return LocalDateTime.parse("2022-12-25T13:00:00");
    }
}
