package br.com.xbrain.sales.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import br.com.xbrain.sales.api.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findSalesByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
