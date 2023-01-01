package br.com.xbrain.sales.api.service.sellers;

import br.com.xbrain.sales.api.model.Seller;
import br.com.xbrain.sales.api.repository.SellerRepository;
import br.com.xbrain.sales.api.services.sellers.RegisterSellerUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RegisterSellerUseCaseTest {

    @Mock
    private SellerRepository sellerRepository;
    private RegisterSellerUseCase registerSellerUseCase;

    @BeforeEach
    void initUseCase() {
        MockitoAnnotations.openMocks(this);
        registerSellerUseCase = new RegisterSellerUseCase(sellerRepository);
        when(sellerRepository.save(any(Seller.class))).then(returnsFirstArg());
    }

    @Test
    void testNotSaveSellersWithoutName() {
        Seller seller = new Seller();
        Seller savedSeller = registerSellerUseCase.registerSeller(seller);
        Assertions.assertThat(savedSeller).isNull();
    }

    @Test
    void testNotSaveSellerWithNameSizeSmallerThanThreeLetters() {
        String[] names = {"", "A", "Sa"};
        Seller seller;
        Seller savedSeller;
        for (String name : names) {
            seller = new Seller(name);
            savedSeller = registerSellerUseCase.registerSeller(seller);
            Assertions.assertThat(savedSeller).isNull();
        }
    }

    @Test
    void testSaveSeller() {
        String name = "Robson";
        Seller seller = new Seller(name);
        Seller savedSeller = registerSellerUseCase.registerSeller(seller);
        Assertions.assertThat(savedSeller).isNotNull();
    }
}
