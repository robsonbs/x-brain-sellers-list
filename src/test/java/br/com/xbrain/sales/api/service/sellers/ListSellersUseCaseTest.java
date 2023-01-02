package br.com.xbrain.sales.api.service.sellers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import br.com.xbrain.sales.api.model.Sale;
import br.com.xbrain.sales.api.model.Seller;
import br.com.xbrain.sales.api.model.input.DateInterval;
import br.com.xbrain.sales.api.repository.SaleRepository;
import br.com.xbrain.sales.api.services.sellers.ListSellersUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class ListSellersUseCaseTest {

    ListSellersUseCase listSellersUseCase;
    @Mock
    SaleRepository saleRepository;

    /* Retornar a lista de vendedores com os campos:
     * nome, total de vendas do vendedor e média de
     * vendas diária, conforme o período informado por parâmetro
     */
    @BeforeEach
    void initUseCase() {
        MockitoAnnotations.openMocks(this);

        listSellersUseCase = new ListSellersUseCase(saleRepository);

        Sale otherSeller = getSale();
        otherSeller.setSeller(Seller.builder().name("Ricardo").id(5L).build());

        when(saleRepository.findAll()).thenReturn(new ArrayList<>())
            .thenReturn(Collections.singletonList(getSale()))
            .thenReturn(Arrays.asList(getSale(), getSale(), otherSeller));
    }

    @Test
    void testListOfSellersWithSale() {
        DateInterval dateInterval =
            DateInterval.builder().startDate(getDateStart()).endDate(getDateEnd()).build();
        Assertions.assertThat(listSellersUseCase.getList(dateInterval)).hasSize(0);
        Assertions.assertThat(listSellersUseCase.getList(dateInterval)).hasSize(1);
        Assertions.assertThat(listSellersUseCase.getList(dateInterval)).hasSize(2);
    }

    private Sale getSale() {
        return Sale.builder()
            .id(1L)
            .seller(getSeller())
            .value(1000L)
            .createdAt(getDateStart())
            .build();
    }

    private Seller getSeller() {
        return Seller.builder().name("Robson").id(1L).build();
    }

    private LocalDateTime getDateStart() {
        return LocalDateTime.parse("2022-12-20T13:00:00");
    }

    private LocalDateTime getDateEnd() {
        return LocalDateTime.parse("2022-12-25T13:00:00");
    }
}
