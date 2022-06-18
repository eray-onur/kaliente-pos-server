package com.kaliente.pos.domain.orderaggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.MERGE}, mappedBy = "belongingOrder")
    private Set<OrderProduct> orderProducts = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.MERGE}, mappedBy = "belongingOrder")
    private Set<OrderTransaction> paymentTransactions = new HashSet<>();

}
