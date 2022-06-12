package com.kaliente.pos.domain.orderaggregate;

import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "order_customers")
@Table
public class OrderCustomer extends BaseEntity {
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    private OrderCustomerType customerType;

    @OneToOne(mappedBy = "orderedBy")
    private Order order;
}
