package br.com.desafioicarros.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lap {

    private int rank;
    private int lap;

    @JsonProperty("Time")
    private Time time;

    @JsonProperty("AverageSpeed")
    private AverageSpeed averageSpeed;
}
