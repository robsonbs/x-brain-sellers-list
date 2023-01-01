package br.com.xbrain.sales.api.service.sales;

import br.com.xbrain.sales.api.model.Sale;
import br.com.xbrain.sales.api.repository.SaleRepository;
import br.com.xbrain.sales.api.services.sales.RegisterSaleUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    Sale savedSale = registerSaleUseCase.registerSale(sale);
    Assertions.assertThat(savedSale).isNull();
  }
}
