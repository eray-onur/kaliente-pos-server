package com.kaliente.pos.domain.orderaggregate;

import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "order_transactions")
@Table
public class OrderTransaction extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order belongingOrder;


    @Enumerated(EnumType.STRING)
    private OrderPaymentMethod paymentMethod;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "currencyTitle", column = @Column(name = "currency_title")),
            @AttributeOverride( name = "currencyDate", column = @Column(name = "currency_date")),
            @AttributeOverride( name = "baseCrossRate", column = @Column(name = "base_cross_rate"))
    })
    private OrderCurrency paymentCurrency;

    @Column(updatable = false)
    private double paidAmount;

}
