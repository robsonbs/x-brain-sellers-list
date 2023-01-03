package br.com.xbrain.sales.api.services.sellers;

import java.util.ArrayList;
import java.util.List;

import br.com.xbrain.sales.api.model.Sale;
import br.com.xbrain.sales.api.model.input.DateInterval;
import br.com.xbrain.sales.api.model.output.DetailsSale;
import br.com.xbrain.sales.api.model.output.Seller;
import br.com.xbrain.sales.api.repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListSellersUseCase {

    SaleRepository saleRepository;

    public List<Seller> getList(DateInterval dateInterval) {
        List<Sale> sales = saleRepository.findSalesByCreatedAtBetween(dateInterval.getStartDate()
            ,dateInterval.getEndDate());
        List<Seller> sellers = new ArrayList<>();
        sales.forEach(item -> {
            Seller findSeller = sellers.stream()
                .filter(seller -> item.getSeller().getId().equals(seller.getId()))
                .findFirst()
                .orElse(new Seller());
            if (findSeller.getId() == null) {
                findSeller.init(item.getSeller(), dateInterval);
                sellers.add(findSeller);
            }
            findSeller.setTotalValue(findSeller.getTotalValue() + item.getValue());
            findSeller.setTotalQuantity(findSeller.getTotalQuantity() + 1);
            DetailsSale detailsSale = findSeller.getSales()
                .stream()
                .filter(sale -> sale.getDate().isEqual(item.getCreatedAt().toLocalDate()))
                .findFirst()
                .orElse(new DetailsSale());
            if (detailsSale.getDate() == null) {
                detailsSale.init(item.getCreatedAt().toLocalDate());
                findSeller.getSales().add(detailsSale);
            }
            detailsSale.setQuantity(detailsSale.getQuantity() + 1);
            detailsSale.setSum(detailsSale.getSum() + item.getValue());
            detailsSale.calculateAverage();
        });

        return sellers;
    }
}
