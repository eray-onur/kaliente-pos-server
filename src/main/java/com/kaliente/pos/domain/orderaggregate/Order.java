package com.kaliente.pos.domain.orderaggregate;

import com.kaliente.pos.domain.seedwork.AggregateRoot;
import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "orders")
@Table
@SQLDelete(sql = "update orders set is_deleted = true where id =?")
@Where(clause = "is_deleted = false")
public class Order extends BaseEntity implements AggregateRoot {

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


    public void addNewTransactions(Set<OrderTransaction> transactionsToProcess) {

        double totalProductPrice = orderProducts.stream().mapToDouble(
                op -> (op.getCurrency().getCurrencyRate() == 1 // Checking if the given currency object is the main currency.
                        ? op.getPrice()
                        : op.getPrice() * op.getCurrency().getCurrencyRate()
                        * op.getOrderedProductQuantity()
                        )
        ).sum();

        paymentTransactions.addAll(transactionsToProcess);

        double newTransactionPrice = 0.0;

        // Total cost for the order giver.
        double paymentPrice = paymentTransactions.stream()
                .filter(pt -> pt.getTransactionType().equals(TransactionType.PAYMENT))
                .mapToDouble(
                    op -> (op.getTransactionCurrency().getCurrencyRate() == 1 // Checking if the given currency object is the main currency.
                        ? op.getPaidAmount()
                        : op.getPaidAmount() * op.getTransactionCurrency().getCurrencyRate())
                ).sum();

        // Total cost for order provider.
        double backpaymentCost = paymentTransactions.stream()
                .filter(pt -> pt.getTransactionType().equals(TransactionType.BACKPAYMENT))
                .mapToDouble(
                        op -> (op.getTransactionCurrency().getCurrencyRate() == 1 // Checking if the given currency object is the main currency.
                                ? op.getPaidAmount()
                                : op.getPaidAmount() * op.getTransactionCurrency().getCurrencyRate())
                ).sum();

        newTransactionPrice = paymentPrice - backpaymentCost;

        if(newTransactionPrice > totalProductPrice) {

            setStatus(OrderStatus.AWAITING_PAYMENT);

            OrderTransaction backPayment = OrderTransaction.builder()
                    .belongingOrder(this)
                    .transactionCurrency(getCurrency())
                    .paidAmount(newTransactionPrice - totalProductPrice)
                    .transactionType(TransactionType.BACKPAYMENT)
                    .transactionMethod(TransactionMethod.CASH)
                    .build();

            paymentTransactions.add(backPayment);

        } else {
            setStatus(OrderStatus.COMPLETED);
        }

    }

    public double getTotalPrice() {
        return orderProducts.stream().mapToDouble(op -> op.getOrderedProductQuantity() * op.getPrice()).sum();
    }

    public static class OrderBuilder {
        public OrderBuilder newTransactions(Set<OrderTransaction> transactionsToProcess) {
            return this;
        }
    }

}
