package br.com.xbrain.sales.api.service.sellers;

import br.com.xbrain.sales.api.model.Seller;
import br.com.xbrain.sales.api.repository.SellerRepository;
import br.com.xbrain.sales.api.services.sellers.RegisterUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RegisterUseCaseTest {

  private final SellerRepository sellerRepository = Mockito.mock(SellerRepository.class);

  private RegisterUseCase registerUseCase;

  @BeforeEach
  void initUseCase() {
    registerUseCase = new RegisterUseCase(sellerRepository);
    when(sellerRepository.save(any(Seller.class))).then(returnsFirstArg());
  }

  @Test
  void testNotSaveSellersWithoutName() {
    Seller seller = new Seller();
    Seller savedSeller = registerUseCase.registerSeller(seller);
    Assertions.assertThat(savedSeller).isNull();
  }
}