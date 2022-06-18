package com.kaliente.pos.domain.orderaggregate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    Order findByOrderedBy_Id(UUID customerId);

}
