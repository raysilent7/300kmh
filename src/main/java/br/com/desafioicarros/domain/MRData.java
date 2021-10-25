package br.com.desafioicarros.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MRData implements Serializable {

    private String url;
    private int limit;
    private int offset;
    private int total;

    @JsonProperty("xmlns")
    private String xmlns;

    @JsonProperty("series")
    private String series;

    @JsonProperty("SeasonTable")
    private SeasonTable seasonTable;

    @JsonProperty("RaceTable")
    private RaceTable raceTable;

    private WinnerTable winnerTable;
}
