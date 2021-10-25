package br.com.desafioicarros.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RaceTable implements Serializable {

    private Integer season;

    @JsonProperty("Races")
    private List<Race> raceList;
}
