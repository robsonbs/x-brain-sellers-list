package br.com.xbrain.sales.api.service.sales;

import java.time.LocalDateTime;

import br.com.xbrain.sales.api.exceptions.BusinessException;
import br.com.xbrain.sales.api.model.Sale;
import br.com.xbrain.sales.api.model.Seller;
import br.com.xbrain.sales.api.repository.SaleRepository;
import br.com.xbrain.sales.api.services.sales.RegisterSaleUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RegisterSaleUseCaseTest {

    RegisterSaleUseCase registerSaleUseCase;
    @Mock
    SaleRepository saleRepository;

    @BeforeEach
    void initUseCase() {
        MockitoAnnotations.openMocks(this);
        registerSaleUseCase = new RegisterSaleUseCase(saleRepository);
        when(saleRepository.save(any(Sale.class))).then(returnsFirstArg());
    }

    @Test
    void testNotSaveSaleWithoutSeller() {
        Sale sale = new Sale();
        BusinessException error =
            assertThrows(BusinessException.class, () -> registerSaleUseCase.registerSale(sale));
        assertEquals("Seller Not Valid!", error.getMessage());
    }

    @Test
    void testNotSaveSaleWithInvalidSeller() {
        Sale sale = new Sale();
        sale.setSeller(new Seller("R"));
        BusinessException error =
            assertThrows(BusinessException.class, () -> registerSaleUseCase.registerSale(sale));
        assertEquals("Seller Not Valid!", error.getMessage());

        sale.getSeller().setName("Robson");
        error = assertThrows(BusinessException.class, () -> registerSaleUseCase.registerSale(sale));
        assertEquals("Seller Not Valid!", error.getMessage());
    }

    @Test
    void testNotSaveSaleWithValueLessOrEqualThanZeroCents() {
        Sale sale = new Sale();
        sale.setSeller(getSeller());
        sale.setValue(0L);
        BusinessException error =
            assertThrows(BusinessException.class, () -> registerSaleUseCase.registerSale(sale));
        assertEquals("Value must be greater than zero!", error.getMessage());

        sale.setValue(-5L);
        error = assertThrows(BusinessException.class, () -> registerSaleUseCase.registerSale(sale));
        assertEquals("Value must be greater than zero!", error.getMessage());
    }

    @Test
    void testNotSaveSaleWithoutDateValid() {
        Sale sale = new Sale();
        sale.setValue(1L);
        sale.setSeller(getSeller());
        sale.getSeller().setId(1L);
        BusinessException error =
            assertThrows(BusinessException.class, () -> registerSaleUseCase.registerSale(sale));
        assertEquals("Date must be valid!", error.getMessage());
    }

    @Test
    void testNotSaveSaleWithoutId() {
        Sale sale = Sale.builder().seller(getSeller()).value(1000L).createdAt(getDate()).build();
        Sale savedSale = registerSaleUseCase.registerSale(sale);
        Assertions.assertThat(savedSale).isNull();
    }

    @Test
    void testSaveSaleValid() {
        Sale sale =
            Sale.builder().id(1L).seller(getSeller()).value(1000L).createdAt(getDate()).build();
        Sale savedSale = registerSaleUseCase.registerSale(sale);
        Assertions.assertThat(savedSale).isNotNull();
    }

    private LocalDateTime getDate() {
        return LocalDateTime.parse("2022-12-20T13:00:00");
    }

    private Seller getSeller() {
        return Seller.builder().name("Robson").id(1L).build();
    }

}
