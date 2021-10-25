package br.com.desafioicarros.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Result {

    private int number;
    private int position;
    private String positionText;
    private int points;
    private int grid;
    private int laps;
    private String status;

    @JsonProperty("Time")
    private Time time;

    @JsonProperty("FastestLap")
    private Lap fastestLap;

    @JsonProperty("Driver")
    private Driver driver;

    @JsonProperty("Constructor")
    private Constructor constructor;
}
