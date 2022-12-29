package br.com.xbrain.sales.api.services.sales;

import br.com.xbrain.sales.api.model.Sale;
import br.com.xbrain.sales.api.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterSalesUseCase {

  private final SaleRepository saleRepository;

  public Sale registerSale(Sale sale) {
    return saleRepository.save(sale);
  }
}
