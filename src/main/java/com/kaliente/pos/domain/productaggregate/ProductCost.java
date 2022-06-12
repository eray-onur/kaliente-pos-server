package com.kaliente.pos.domain.productaggregate;

import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "product_cost")
@Table(name = "product_cost")
public class ProductCost extends BaseEntity {
    @Column
    private double amount;
}
