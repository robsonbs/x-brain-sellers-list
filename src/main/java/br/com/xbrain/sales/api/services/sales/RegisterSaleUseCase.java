package br.com.xbrain.sales.api.services.sales;

import br.com.xbrain.sales.api.model.Sale;
import br.com.xbrain.sales.api.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterSaleUseCase {

  private final SaleRepository saleRepository;

  public Sale registerSale(Sale sale) {
    if (!sale.isValid()) {return null;}
    return saleRepository.save(sale);
  }
}
