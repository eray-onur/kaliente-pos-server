package com.kaliente.pos.domain.orderaggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;
import org.hibernate.FetchMode;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "orders")
@Table
public class Order extends BaseEntity {

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private OrderCustomer orderedBy;

    @Column(name = "ordering_date")
    private Date orderingDate;

    @Column(name = "completion_date")
    private Date completionDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "currencyTitle", column = @Column(name = "currency_title")),
            @AttributeOverride( name = "currencyDate", column = @Column(name = "currency_date")),
            @AttributeOverride( name = "baseCrossRate", column = @Column(name = "base_cross_rate")),
            @AttributeOverride( name = "currencyRate", column = @Column(name = "currency_rate"))
    })
    private OrderCurrency currency;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "belongingOrder")
    private Set<OrderProduct> orderProducts = new HashSet<OrderProduct>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "belongingOrder")
    private Set<OrderTransaction> paymentTransactions = new HashSet<OrderTransaction>();

    public double getTotalPrice() {
        return orderProducts.stream().mapToDouble(OrderProduct::getPrice).sum();
    }

}
