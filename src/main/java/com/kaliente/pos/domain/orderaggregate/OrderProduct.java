package com.kaliente.pos.domain.orderaggregate;

import com.kaliente.pos.domain.productaggregate.Product;
import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "order_products")
@Table
public class OrderProduct extends BaseEntity {

    @Column(nullable = false, updatable = false)
    private UUID orderedProductId;

    @Column(nullable = false, updatable = false)
    private String orderedProductName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order belongingOrder;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "belongingProduct")
    private OrderProductPrice price;

    @Column(nullable = true, updatable = true)
    private String invoicePath;

}
