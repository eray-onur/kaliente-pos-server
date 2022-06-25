package com.kaliente.pos.domain.orderaggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "order_customers")
@Table
@SQLDelete(sql = "update order_customers set isActive = 0 where id =?")
@Where(clause = "isActive = 1")
public class OrderCustomer extends BaseEntity {
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    private OrderCustomerType customerType;

    @OneToOne(mappedBy = "orderedBy", fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;
}
