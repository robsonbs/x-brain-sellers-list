package br.com.xbrain.sales.api.services.sellers;

import br.com.xbrain.sales.api.model.Seller;
import br.com.xbrain.sales.api.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUseCase {

  private final SellerRepository sellerRepository;

  public Seller registerSeller(Seller seller) {
    if (seller.getName() == null) {return null;}
    return sellerRepository.save(seller);
  }
}
