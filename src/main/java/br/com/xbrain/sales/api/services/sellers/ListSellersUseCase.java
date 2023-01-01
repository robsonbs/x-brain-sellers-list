package br.com.xbrain.sales.api.services.sellers;

import java.util.ArrayList;
import java.util.List;

import br.com.xbrain.sales.api.model.input.DateInterval;
import org.springframework.stereotype.Service;

@Service
public class ListSellersUseCase {

    public List<Seller> getList(DateInterval dateInterval) {
        return new ArrayList<>();
    }
}
