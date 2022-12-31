package br.com.xbrain.sales.api.services.sellers;

import br.com.xbrain.sales.api.model.Seller;
import br.com.xbrain.sales.api.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterSellerUseCase {

  private final SellerRepository sellerRepository;

  public Seller registerSeller(Seller seller) {
    if (seller.getName() == null || seller.getName().strip().length() < 3) {return null;}

    return sellerRepository.save(seller);
  }
}
