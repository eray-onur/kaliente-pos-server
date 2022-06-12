package com.kaliente.pos.domain.productaggregate;

import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductPrice extends BaseEntity {
    @Column
    private double amount;
}