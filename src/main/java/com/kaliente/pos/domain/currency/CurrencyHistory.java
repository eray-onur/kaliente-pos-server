package com.kaliente.pos.domain.currency;

import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity(name="currency_histories")
@Table
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyHistory extends BaseEntity {
    private String currencyTitle;
    private double baseCrossRate;
    private double currencyRate;
    private Date currencyDate;

}
