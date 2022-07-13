package com.kaliente.pos.domain.currency;

import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity(name="currency_histories")
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_active = true")
public class CurrencyHistory extends BaseEntity {
    private String currencyTitle;
    private double baseCrossRate;
    private double currencyRate;
    private Date currencyDate;

}
