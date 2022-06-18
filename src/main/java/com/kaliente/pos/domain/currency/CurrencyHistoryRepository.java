package com.kaliente.pos.domain.currency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CurrencyHistoryRepository extends JpaRepository<CurrencyHistory, UUID> {

    @Query(value = "SELECT c FROM currency_histories c WHERE c.currencyTitle=?1 ORDER BY c.createdOn DESC")
    List<CurrencyHistory> findByCurrencyTitle(String currencyTitle);

}
