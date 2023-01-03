package br.com.xbrain.sales.api.service.sellers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

import static org.mockito.ArgumentMatchers.any;
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

        Sale saleWithOtherValue = getSale();
        saleWithOtherValue.setValue(100L);

        when(saleRepository.findSalesByCreatedAtBetween(any(), any())).thenReturn(
                Arrays.asList(getSale(), saleWithOtherValue, otherSeller))
            .thenReturn(Arrays.asList(getSale(), getSale(), otherSeller))
            .thenReturn(Collections.singletonList(getSale()))
            .thenReturn(new ArrayList<>());
    }

    @Test
    void testListOfSellersWithSale() {
        DateInterval dateInterval =
            DateInterval.builder().startDate(getDateStart()).endDate(getDateEnd()).build();
        Assertions.assertThat(listSellersUseCase.getList(dateInterval)).hasSize(2);
        Assertions.assertThat(listSellersUseCase.getList(dateInterval)).hasSize(2);
        Assertions.assertThat(listSellersUseCase.getList(dateInterval)).hasSize(1);
        Assertions.assertThat(listSellersUseCase.getList(dateInterval)).hasSize(0);
    }

    @Test
    void testValuesOnSellersList() {
        DateInterval dateInterval =
            DateInterval.builder().startDate(getDateStart()).endDate(getDateEnd()).build();
        List<br.com.xbrain.sales.api.model.output.Seller> sellers =
            listSellersUseCase.getList(dateInterval);

        Assertions.assertThat(sellers).hasSize(2);

        org.junit.jupiter.api.Assertions.assertEquals(2, sellers.get(0).getTotalQuantity());
        org.junit.jupiter.api.Assertions.assertEquals(1100, sellers.get(0).getTotalValue());
        org.junit.jupiter.api.Assertions.assertEquals(550, sellers.get(0)
            .getSales()
            .get(0)
            .getAverage());

        org.junit.jupiter.api.Assertions.assertEquals(1, sellers.get(1).getTotalQuantity());
        org.junit.jupiter.api.Assertions.assertEquals(1000, sellers.get(1).getTotalValue());
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
