package br.com.desafioicarros.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AverageSpeed {

    private String units;
    private BigDecimal speed;
}
