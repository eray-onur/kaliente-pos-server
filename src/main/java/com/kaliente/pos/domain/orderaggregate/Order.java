package com.kaliente.pos.domain.orderaggregate;

import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "orders")
@Table
public class Order extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "ordered_by", referencedColumnName = "id")
    private OrderCustomer orderedBy;

    @Column(name = "ordering_date", updatable = false)
    private Date orderingDate;

    @Column(name = "completion_date")
    private Date completionDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "belongingOrder")
    private Set<OrderProduct> orderProducts = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "belongingOrder")
    private Set<OrderTransaction> paymentTransactions = new HashSet<>();

}
